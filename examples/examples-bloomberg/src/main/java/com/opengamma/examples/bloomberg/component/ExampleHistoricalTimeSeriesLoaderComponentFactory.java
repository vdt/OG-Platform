/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.examples.bloomberg.component;

import java.util.LinkedHashMap;
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

import com.opengamma.bbg.BloombergIdentifierProvider;
import com.opengamma.bbg.loader.hts.BloombergHistoricalTimeSeriesLoader;
import com.opengamma.bbg.referencedata.ReferenceDataProvider;
import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesLoader;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.provider.historicaltimeseries.HistoricalTimeSeriesProvider;

/**
 * Component factory that instantiates the time-series loader.
 */
@BeanDefinition
public class ExampleHistoricalTimeSeriesLoaderComponentFactory extends AbstractComponentFactory {

  /**
   * The configuration URI.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The historical timeSeries source.
   */
  @PropertyDefinition(validate = "notNull")
  private HistoricalTimeSeriesProvider _historicalTimeSeriesProvider;
  /**
   * The historical timeSeries master.
   */
  @PropertyDefinition(validate = "notNull")
  private HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  /**
   * The reference data provider.
   */
  @PropertyDefinition(validate = "notNull")
  private ReferenceDataProvider _referenceDataProvider;

  //-------------------------------------------------------------------------
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    BloombergHistoricalTimeSeriesLoader htsLoader = new BloombergHistoricalTimeSeriesLoader(
        getHistoricalTimeSeriesMaster(), getHistoricalTimeSeriesProvider(), new BloombergIdentifierProvider(getReferenceDataProvider()));
    
    ComponentInfo info = new ComponentInfo(HistoricalTimeSeriesLoader.class, getClassifier());
    repo.registerComponent(info, htsLoader);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExampleHistoricalTimeSeriesLoaderComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ExampleHistoricalTimeSeriesLoaderComponentFactory.Meta meta() {
    return ExampleHistoricalTimeSeriesLoaderComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ExampleHistoricalTimeSeriesLoaderComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ExampleHistoricalTimeSeriesLoaderComponentFactory.Meta metaBean() {
    return ExampleHistoricalTimeSeriesLoaderComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -1592479713:  // historicalTimeSeriesProvider
        return getHistoricalTimeSeriesProvider();
      case 173967376:  // historicalTimeSeriesMaster
        return getHistoricalTimeSeriesMaster();
      case -1788671322:  // referenceDataProvider
        return getReferenceDataProvider();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -1592479713:  // historicalTimeSeriesProvider
        setHistoricalTimeSeriesProvider((HistoricalTimeSeriesProvider) newValue);
        return;
      case 173967376:  // historicalTimeSeriesMaster
        setHistoricalTimeSeriesMaster((HistoricalTimeSeriesMaster) newValue);
        return;
      case -1788671322:  // referenceDataProvider
        setReferenceDataProvider((ReferenceDataProvider) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_historicalTimeSeriesProvider, "historicalTimeSeriesProvider");
    JodaBeanUtils.notNull(_historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    JodaBeanUtils.notNull(_referenceDataProvider, "referenceDataProvider");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExampleHistoricalTimeSeriesLoaderComponentFactory other = (ExampleHistoricalTimeSeriesLoaderComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getHistoricalTimeSeriesProvider(), other.getHistoricalTimeSeriesProvider()) &&
          JodaBeanUtils.equal(getHistoricalTimeSeriesMaster(), other.getHistoricalTimeSeriesMaster()) &&
          JodaBeanUtils.equal(getReferenceDataProvider(), other.getReferenceDataProvider()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(getHistoricalTimeSeriesProvider());
    hash += hash * 31 + JodaBeanUtils.hashCode(getHistoricalTimeSeriesMaster());
    hash += hash * 31 + JodaBeanUtils.hashCode(getReferenceDataProvider());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the configuration URI.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the configuration URI.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the historical timeSeries source.
   * @return the value of the property, not null
   */
  public HistoricalTimeSeriesProvider getHistoricalTimeSeriesProvider() {
    return _historicalTimeSeriesProvider;
  }

  /**
   * Sets the historical timeSeries source.
   * @param historicalTimeSeriesProvider  the new value of the property, not null
   */
  public void setHistoricalTimeSeriesProvider(HistoricalTimeSeriesProvider historicalTimeSeriesProvider) {
    JodaBeanUtils.notNull(historicalTimeSeriesProvider, "historicalTimeSeriesProvider");
    this._historicalTimeSeriesProvider = historicalTimeSeriesProvider;
  }

  /**
   * Gets the the {@code historicalTimeSeriesProvider} property.
   * @return the property, not null
   */
  public final Property<HistoricalTimeSeriesProvider> historicalTimeSeriesProvider() {
    return metaBean().historicalTimeSeriesProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the historical timeSeries master.
   * @return the value of the property, not null
   */
  public HistoricalTimeSeriesMaster getHistoricalTimeSeriesMaster() {
    return _historicalTimeSeriesMaster;
  }

  /**
   * Sets the historical timeSeries master.
   * @param historicalTimeSeriesMaster  the new value of the property, not null
   */
  public void setHistoricalTimeSeriesMaster(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    JodaBeanUtils.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    this._historicalTimeSeriesMaster = historicalTimeSeriesMaster;
  }

  /**
   * Gets the the {@code historicalTimeSeriesMaster} property.
   * @return the property, not null
   */
  public final Property<HistoricalTimeSeriesMaster> historicalTimeSeriesMaster() {
    return metaBean().historicalTimeSeriesMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference data provider.
   * @return the value of the property, not null
   */
  public ReferenceDataProvider getReferenceDataProvider() {
    return _referenceDataProvider;
  }

  /**
   * Sets the reference data provider.
   * @param referenceDataProvider  the new value of the property, not null
   */
  public void setReferenceDataProvider(ReferenceDataProvider referenceDataProvider) {
    JodaBeanUtils.notNull(referenceDataProvider, "referenceDataProvider");
    this._referenceDataProvider = referenceDataProvider;
  }

  /**
   * Gets the the {@code referenceDataProvider} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> referenceDataProvider() {
    return metaBean().referenceDataProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExampleHistoricalTimeSeriesLoaderComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", ExampleHistoricalTimeSeriesLoaderComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code historicalTimeSeriesProvider} property.
     */
    private final MetaProperty<HistoricalTimeSeriesProvider> _historicalTimeSeriesProvider = DirectMetaProperty.ofReadWrite(
        this, "historicalTimeSeriesProvider", ExampleHistoricalTimeSeriesLoaderComponentFactory.class, HistoricalTimeSeriesProvider.class);
    /**
     * The meta-property for the {@code historicalTimeSeriesMaster} property.
     */
    private final MetaProperty<HistoricalTimeSeriesMaster> _historicalTimeSeriesMaster = DirectMetaProperty.ofReadWrite(
        this, "historicalTimeSeriesMaster", ExampleHistoricalTimeSeriesLoaderComponentFactory.class, HistoricalTimeSeriesMaster.class);
    /**
     * The meta-property for the {@code referenceDataProvider} property.
     */
    private final MetaProperty<ReferenceDataProvider> _referenceDataProvider = DirectMetaProperty.ofReadWrite(
        this, "referenceDataProvider", ExampleHistoricalTimeSeriesLoaderComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "historicalTimeSeriesProvider",
        "historicalTimeSeriesMaster",
        "referenceDataProvider");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -1592479713:  // historicalTimeSeriesProvider
          return _historicalTimeSeriesProvider;
        case 173967376:  // historicalTimeSeriesMaster
          return _historicalTimeSeriesMaster;
        case -1788671322:  // referenceDataProvider
          return _referenceDataProvider;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExampleHistoricalTimeSeriesLoaderComponentFactory> builder() {
      return new DirectBeanBuilder<ExampleHistoricalTimeSeriesLoaderComponentFactory>(new ExampleHistoricalTimeSeriesLoaderComponentFactory());
    }

    @Override
    public Class<? extends ExampleHistoricalTimeSeriesLoaderComponentFactory> beanType() {
      return ExampleHistoricalTimeSeriesLoaderComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code historicalTimeSeriesProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HistoricalTimeSeriesProvider> historicalTimeSeriesProvider() {
      return _historicalTimeSeriesProvider;
    }

    /**
     * The meta-property for the {@code historicalTimeSeriesMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HistoricalTimeSeriesMaster> historicalTimeSeriesMaster() {
      return _historicalTimeSeriesMaster;
    }

    /**
     * The meta-property for the {@code referenceDataProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> referenceDataProvider() {
      return _referenceDataProvider;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
