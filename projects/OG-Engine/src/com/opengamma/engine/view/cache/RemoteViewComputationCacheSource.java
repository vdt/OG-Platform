/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.view.cache;

import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;

import org.fudgemsg.FudgeContext;
import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.FudgeMsgEnvelope;
import org.fudgemsg.mapping.FudgeDeserializationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.engine.view.cache.msg.CacheMessage;
import com.opengamma.engine.view.cache.msg.CacheMessageVisitor;
import com.opengamma.engine.view.cache.msg.FindMessage;
import com.opengamma.engine.view.cache.msg.ReleaseCacheMessage;
import com.opengamma.transport.FudgeMessageReceiver;

/**
 * Caching client for {@link ViewComputationCacheServer}.
 */
public class RemoteViewComputationCacheSource extends DefaultViewComputationCacheSource implements FudgeMessageReceiver {

  private static final Logger s_logger = LoggerFactory.getLogger(RemoteViewComputationCacheSource.class);

  public RemoteViewComputationCacheSource(final RemoteCacheClient client,
      final FudgeMessageStoreFactory privateDataStoreFactory, final CacheManager cacheManager) {
    this(client, privateDataStoreFactory, client.getFudgeContext(), cacheManager);
  }

  public RemoteViewComputationCacheSource(final RemoteCacheClient client,
      final FudgeMessageStoreFactory privateDataStoreFactory, final CacheManager cacheManager,
      final int maxLocalCachedElements) {
    this(client, privateDataStoreFactory, client.getFudgeContext(), cacheManager, maxLocalCachedElements);
  }

  /**
   * @param client the connection to a {@link ViewComputationCacheServer}
   * @param privateDataStoreFactory the private data store - the shared data store will be the remote one
   * @param fudgeContext the Fudge context the {@link DefaultViewComputationCache} will use for object encoding. This may be the same as the
   *                     one attached to the client's transport or different.
   * @param cacheManager the EH cache manager to use for the remote binary data store
   */
  public RemoteViewComputationCacheSource(final RemoteCacheClient client,
      final FudgeMessageStoreFactory privateDataStoreFactory, final FudgeContext fudgeContext,
      final CacheManager cacheManager) {
    super(createIdentifierMap(client), fudgeContext, privateDataStoreFactory, createFudgeMessageStoreFactory(client,
        cacheManager, -1));
    client.setAsynchronousMessageReceiver(this);
  }

  public RemoteViewComputationCacheSource(final RemoteCacheClient client,
      final FudgeMessageStoreFactory privateDataStoreFactory, final FudgeContext fudgeContext,
      final CacheManager cacheManager, final int maxLocalCachedElements) {
    super(createIdentifierMap(client), fudgeContext, privateDataStoreFactory, createFudgeMessageStoreFactory(client,
        cacheManager, maxLocalCachedElements));
    client.setAsynchronousMessageReceiver(this);
  }

  private static IdentifierMap createIdentifierMap(final RemoteCacheClient client) {
    return new CachingIdentifierMap(new RemoteIdentifierMap(client));
  }

  private static FudgeMessageStoreFactory createFudgeMessageStoreFactory(final RemoteCacheClient client,
      final CacheManager cacheManager, final int maxLocalCachedElements) {
    final RemoteFudgeMessageStoreFactory remote = new RemoteFudgeMessageStoreFactory(client);
    if (maxLocalCachedElements >= 0) {
      return new CachingFudgeMessageStoreFactory(remote, cacheManager, maxLocalCachedElements);
    } else {
      return new CachingFudgeMessageStoreFactory(remote, cacheManager);
    }
  }

  private final CacheMessageVisitor _messageReceiver = new CacheMessageVisitor() {

    @Override
    protected CacheMessage visitReleaseCacheMessage(final ReleaseCacheMessage message) {
      s_logger.debug("Releasing cache {}/{}", message.getViewProcessId(), message.getTimestamp());
      // [ENG-256] make sure we don't cause a cascade of messages if e.g. release called on a client, must cause release on server, which must send release to other clients but these must not generate
      // further messages
      releaseCaches(message.getViewProcessId(), message.getTimestamp());
      return null;
    }

    @Override
    protected CacheMessage visitFindMessage(final FindMessage message) {
      final DefaultViewComputationCache cache = findCache(message.getViewProcessId(), message
          .getCalculationConfigurationName(), message.getSnapshotTimestamp());
      if (cache != null) {
        final List<Long> identifiers = message.getIdentifier();
        s_logger.debug("Searching for {} identifiers to send to shared cache", identifiers.size());
        if (identifiers.size() == 1) {
          final long identifier = identifiers.get(0);
          final FudgeFieldContainer data = cache.getPrivateDataStore().get(identifier);
          if (data != null) {
            s_logger.debug("Found identifier {} in private cache", identifier);
            cache.getSharedDataStore().put(identifier, data);
          }
        } else {
          final Map<Long, FudgeFieldContainer> data = cache.getPrivateDataStore().get(identifiers);
          if (data.size() == 1) {
            s_logger.debug("Found 1 of {} identifiers in private cache", identifiers.size());
            final Map.Entry<Long, FudgeFieldContainer> entry = data.entrySet().iterator().next();
            cache.getSharedDataStore().put(entry.getKey(), entry.getValue());
          } else if (data.size() > 1) {
            s_logger.debug("Found {} of {} identifiers in private cache", data.size(), identifiers.size());
            cache.getSharedDataStore().put(data);
          }
        }
      }
      return null;
    }

    @Override
    protected <T extends CacheMessage> T visitUnexpectedMessage(final CacheMessage message) {
      s_logger.warn("Unexpected message {}", message);
      return null;
    }

  };

  // [ENG-256] Override, or register callback handler for releaseCaches so that if it is called by user code we propogate the message to the server and other clients, noting the warning about cascade
  // above

  @Override
  public void messageReceived(final FudgeContext fudgeContext, final FudgeMsgEnvelope msgEnvelope) {
    final FudgeDeserializationContext dctx = new FudgeDeserializationContext(fudgeContext);
    final CacheMessage message = dctx.fudgeMsgToObject(CacheMessage.class, msgEnvelope.getMessage());
    message.accept(_messageReceiver);
  }

}
