/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;

/**
 * Convention for exchange-traded futures and future options.
 */
@BeanDefinition
public class ExchangeTradedFutureAndOptionConvention extends Convention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The expiry convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _expiryConvention;

  /**
   * The exchange calendar.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _exchangeCalendar;

  /**
   * For the builder.
   */
  protected ExchangeTradedFutureAndOptionConvention() {
    super();
  }

  /**
   * @param name The convention name, not null
   * @param externalIdBundle The external identifiers for this convention, not null
   * @param expiryConvention The expiry convention, not null
   * @param exchangeCalendar The exchange calendar, not null
   */
  public ExchangeTradedFutureAndOptionConvention(final String name, final ExternalIdBundle externalIdBundle, final ExternalId expiryConvention,
      final ExternalId exchangeCalendar) {
    super(name, externalIdBundle);
    setExpiryConvention(expiryConvention);
    setExchangeCalendar(exchangeCalendar);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExchangeTradedFutureAndOptionConvention}.
   * @return the meta-bean, not null
   */
  public static ExchangeTradedFutureAndOptionConvention.Meta meta() {
    return ExchangeTradedFutureAndOptionConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ExchangeTradedFutureAndOptionConvention.Meta.INSTANCE);
  }

  @Override
  public ExchangeTradedFutureAndOptionConvention.Meta metaBean() {
    return ExchangeTradedFutureAndOptionConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2143523076:  // expiryConvention
        return getExpiryConvention();
      case 29366913:  // exchangeCalendar
        return getExchangeCalendar();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 2143523076:  // expiryConvention
        setExpiryConvention((ExternalId) newValue);
        return;
      case 29366913:  // exchangeCalendar
        setExchangeCalendar((ExternalId) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_expiryConvention, "expiryConvention");
    JodaBeanUtils.notNull(_exchangeCalendar, "exchangeCalendar");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExchangeTradedFutureAndOptionConvention other = (ExchangeTradedFutureAndOptionConvention) obj;
      return JodaBeanUtils.equal(getExpiryConvention(), other.getExpiryConvention()) &&
          JodaBeanUtils.equal(getExchangeCalendar(), other.getExchangeCalendar()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getExpiryConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExchangeCalendar());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry convention.
   * @return the value of the property, not null
   */
  public ExternalId getExpiryConvention() {
    return _expiryConvention;
  }

  /**
   * Sets the expiry convention.
   * @param expiryConvention  the new value of the property, not null
   */
  public void setExpiryConvention(ExternalId expiryConvention) {
    JodaBeanUtils.notNull(expiryConvention, "expiryConvention");
    this._expiryConvention = expiryConvention;
  }

  /**
   * Gets the the {@code expiryConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> expiryConvention() {
    return metaBean().expiryConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchange calendar.
   * @return the value of the property, not null
   */
  public ExternalId getExchangeCalendar() {
    return _exchangeCalendar;
  }

  /**
   * Sets the exchange calendar.
   * @param exchangeCalendar  the new value of the property, not null
   */
  public void setExchangeCalendar(ExternalId exchangeCalendar) {
    JodaBeanUtils.notNull(exchangeCalendar, "exchangeCalendar");
    this._exchangeCalendar = exchangeCalendar;
  }

  /**
   * Gets the the {@code exchangeCalendar} property.
   * @return the property, not null
   */
  public final Property<ExternalId> exchangeCalendar() {
    return metaBean().exchangeCalendar().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExchangeTradedFutureAndOptionConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code expiryConvention} property.
     */
    private final MetaProperty<ExternalId> _expiryConvention = DirectMetaProperty.ofReadWrite(
        this, "expiryConvention", ExchangeTradedFutureAndOptionConvention.class, ExternalId.class);
    /**
     * The meta-property for the {@code exchangeCalendar} property.
     */
    private final MetaProperty<ExternalId> _exchangeCalendar = DirectMetaProperty.ofReadWrite(
        this, "exchangeCalendar", ExchangeTradedFutureAndOptionConvention.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "expiryConvention",
        "exchangeCalendar");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2143523076:  // expiryConvention
          return _expiryConvention;
        case 29366913:  // exchangeCalendar
          return _exchangeCalendar;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExchangeTradedFutureAndOptionConvention> builder() {
      return new DirectBeanBuilder<ExchangeTradedFutureAndOptionConvention>(new ExchangeTradedFutureAndOptionConvention());
    }

    @Override
    public Class<? extends ExchangeTradedFutureAndOptionConvention> beanType() {
      return ExchangeTradedFutureAndOptionConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code expiryConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> expiryConvention() {
      return _expiryConvention;
    }

    /**
     * The meta-property for the {@code exchangeCalendar} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> exchangeCalendar() {
      return _exchangeCalendar;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
