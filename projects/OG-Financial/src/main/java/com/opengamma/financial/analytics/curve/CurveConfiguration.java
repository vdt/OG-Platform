/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import java.io.Serializable;

import org.joda.beans.BeanDefinition;
import org.joda.beans.impl.direct.DirectBean;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * 
 */
@BeanDefinition
public abstract class CurveConfiguration extends DirectBean implements Serializable {

  /**
   * 
   */
  public CurveConfiguration() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CurveConfiguration}.
   * @return the meta-bean, not null
   */
  public static CurveConfiguration.Meta meta() {
    return CurveConfiguration.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(CurveConfiguration.Meta.INSTANCE);
  }

  @Override
  public CurveConfiguration.Meta metaBean() {
    return CurveConfiguration.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CurveConfiguration}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends CurveConfiguration> builder() {
      throw new UnsupportedOperationException("CurveConfiguration is an abstract class");
    }

    @Override
    public Class<? extends CurveConfiguration> beanType() {
      return CurveConfiguration.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}