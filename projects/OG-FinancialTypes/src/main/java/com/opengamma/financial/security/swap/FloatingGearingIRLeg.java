/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.security.swap;

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

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.id.ExternalId;

/**
 * The floating gearing interest rate swap leg.
 */
@BeanDefinition
public class FloatingGearingIRLeg extends FloatingInterestRateLeg {
  
  /** Serialization version. */
  private static final long serialVersionUID = 1L;
  
  /**
   * The gearing.
   */
  @PropertyDefinition
  private double _gearing;
  
  /**
   * Creates an instance.
   */
  FloatingGearingIRLeg() { //For builder
  }

  /**
   * Creates an instance.
   * 
   * @param dayCount  the day count, not null
   * @param frequency  the frequency, not null
   * @param regionIdentifier  the region, not null
   * @param businessDayConvention  the business day convention, not null
   * @param notional  the notional, not null
   * @param eom  whether this is EOM
   * @param floatingReferenceRateId  the reference rate, not null
   * @param floatingRateType  the floating rate type, not null
   * @param gearing the gearing
   */
  public FloatingGearingIRLeg(DayCount dayCount, Frequency frequency, ExternalId regionIdentifier, BusinessDayConvention businessDayConvention,
      Notional notional, boolean eom, ExternalId floatingReferenceRateId, FloatingRateType floatingRateType, double gearing) {
    super(dayCount, frequency, regionIdentifier, businessDayConvention, notional, eom, floatingReferenceRateId, floatingRateType);
    setGearing(gearing);
  }
  
  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(SwapLegVisitor<T> visitor) {
    return visitor.visitFloatingGearingIRLeg(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FloatingGearingIRLeg}.
   * @return the meta-bean, not null
   */
  public static FloatingGearingIRLeg.Meta meta() {
    return FloatingGearingIRLeg.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FloatingGearingIRLeg.Meta.INSTANCE);
  }

  @Override
  public FloatingGearingIRLeg.Meta metaBean() {
    return FloatingGearingIRLeg.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -91774989:  // gearing
        return getGearing();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -91774989:  // gearing
        setGearing((Double) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FloatingGearingIRLeg other = (FloatingGearingIRLeg) obj;
      return JodaBeanUtils.equal(getGearing(), other.getGearing()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getGearing());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the gearing.
   * @return the value of the property
   */
  public double getGearing() {
    return _gearing;
  }

  /**
   * Sets the gearing.
   * @param gearing  the new value of the property
   */
  public void setGearing(double gearing) {
    this._gearing = gearing;
  }

  /**
   * Gets the the {@code gearing} property.
   * @return the property, not null
   */
  public final Property<Double> gearing() {
    return metaBean().gearing().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FloatingGearingIRLeg}.
   */
  public static class Meta extends FloatingInterestRateLeg.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code gearing} property.
     */
    private final MetaProperty<Double> _gearing = DirectMetaProperty.ofReadWrite(
        this, "gearing", FloatingGearingIRLeg.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "gearing");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -91774989:  // gearing
          return _gearing;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FloatingGearingIRLeg> builder() {
      return new DirectBeanBuilder<FloatingGearingIRLeg>(new FloatingGearingIRLeg());
    }

    @Override
    public Class<? extends FloatingGearingIRLeg> beanType() {
      return FloatingGearingIRLeg.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code gearing} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> gearing() {
      return _gearing;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
