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

import com.opengamma.analytics.financial.interestrate.CompoundingType;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.time.Tenor;

/**
 * Conventions for a compounding ibor swap leg.
 */
@BeanDefinition
public class CompoundingIborLegConvention extends Convention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The ibor index convention.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _iborIndexConvention;

  /**
   * The payment tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _paymentTenor;

  /**
   * The compounding type.
   */
  @PropertyDefinition(validate = "notNull")
  private CompoundingType _compoundingType;

  /**
   * The composition tenor, i.e. the tenor of the sub-periods compounded into the payment tenor.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _compositionTenor;

  /**
   * The stub type.
   */
  @PropertyDefinition(validate = "notNull")
  private StubType _stubTypeCompound;

  /**
   * Is the notional exchanged.
   */
  @PropertyDefinition
  private boolean _isExchangeNotional;

  /**
   * The number of settlement days.
   */
  @PropertyDefinition
  private int _settlementDays;

  /**
   * Is the schedule end-of-month.
   */
  @PropertyDefinition
  private boolean _isEOM;

  /**
   * The stub type.
   */
  @PropertyDefinition(validate = "notNull")
  private StubType _stubTypeLeg;

  /**
   * The payment lag in days.
   */
  @PropertyDefinition
  private int _paymentLag;
  
  /**
   * For the builder
   */
  /* package */ CompoundingIborLegConvention() {
  }

  /**
   * @param name The name of the convention, not null
   * @param externalIdBundle The external identifiers for this convention, not null
   * @param iborIndexConvention The id of the underlying ibor index convention, not null
   * @param paymentTenor The payment tenor, not null
   * @param compoundingType The compounding type, not null
   * @param compositionTenor The composition tenor. Not null.
   * @param stubTypeCompound The stub type used in each coupon for the compounding. Not null.
   * @param settlementDays The number of settlement days
   * @param isEOM True if dates follow the end-of-month rule
   * @param stubTypeLeg The stub type used in the leg for the different coupons. Not null
   * @param isExchangeNotional True if notional is to be exchanged
   * @param paymentLag The payment lag in days
   */
  public CompoundingIborLegConvention(final String name, final ExternalIdBundle externalIdBundle, final ExternalId iborIndexConvention, final Tenor paymentTenor,
      final CompoundingType compoundingType, final Tenor compositionTenor, final StubType stubTypeCompound, final int settlementDays, final boolean isEOM, 
      final StubType stubTypeLeg, final boolean isExchangeNotional, final int paymentLag) {
    super(name, externalIdBundle);
    setIborIndexConvention(iborIndexConvention);
    setPaymentTenor(paymentTenor);
    setCompoundingType(compoundingType);
    setCompositionTenor(compositionTenor);
    setStubTypeCompound(stubTypeCompound);
    setSettlementDays(settlementDays);
    setIsEOM(isEOM);
    setStubTypeLeg(stubTypeLeg);
    setIsExchangeNotional(isExchangeNotional);
    setPaymentLag(paymentLag);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CompoundingIborLegConvention}.
   * @return the meta-bean, not null
   */
  public static CompoundingIborLegConvention.Meta meta() {
    return CompoundingIborLegConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CompoundingIborLegConvention.Meta.INSTANCE);
  }

  @Override
  public CompoundingIborLegConvention.Meta metaBean() {
    return CompoundingIborLegConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1542426233:  // iborIndexConvention
        return getIborIndexConvention();
      case -507548582:  // paymentTenor
        return getPaymentTenor();
      case -1936397775:  // compoundingType
        return getCompoundingType();
      case -1679443146:  // compositionTenor
        return getCompositionTenor();
      case -1172460845:  // stubTypeCompound
        return getStubTypeCompound();
      case 348962765:  // isExchangeNotional
        return isIsExchangeNotional();
      case -295948000:  // settlementDays
        return getSettlementDays();
      case 100464505:  // isEOM
        return isIsEOM();
      case 1272752102:  // stubTypeLeg
        return getStubTypeLeg();
      case 1612870060:  // paymentLag
        return getPaymentLag();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1542426233:  // iborIndexConvention
        setIborIndexConvention((ExternalId) newValue);
        return;
      case -507548582:  // paymentTenor
        setPaymentTenor((Tenor) newValue);
        return;
      case -1936397775:  // compoundingType
        setCompoundingType((CompoundingType) newValue);
        return;
      case -1679443146:  // compositionTenor
        setCompositionTenor((Tenor) newValue);
        return;
      case -1172460845:  // stubTypeCompound
        setStubTypeCompound((StubType) newValue);
        return;
      case 348962765:  // isExchangeNotional
        setIsExchangeNotional((Boolean) newValue);
        return;
      case -295948000:  // settlementDays
        setSettlementDays((Integer) newValue);
        return;
      case 100464505:  // isEOM
        setIsEOM((Boolean) newValue);
        return;
      case 1272752102:  // stubTypeLeg
        setStubTypeLeg((StubType) newValue);
        return;
      case 1612870060:  // paymentLag
        setPaymentLag((Integer) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_iborIndexConvention, "iborIndexConvention");
    JodaBeanUtils.notNull(_paymentTenor, "paymentTenor");
    JodaBeanUtils.notNull(_compoundingType, "compoundingType");
    JodaBeanUtils.notNull(_compositionTenor, "compositionTenor");
    JodaBeanUtils.notNull(_stubTypeCompound, "stubTypeCompound");
    JodaBeanUtils.notNull(_stubTypeLeg, "stubTypeLeg");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CompoundingIborLegConvention other = (CompoundingIborLegConvention) obj;
      return JodaBeanUtils.equal(getIborIndexConvention(), other.getIborIndexConvention()) &&
          JodaBeanUtils.equal(getPaymentTenor(), other.getPaymentTenor()) &&
          JodaBeanUtils.equal(getCompoundingType(), other.getCompoundingType()) &&
          JodaBeanUtils.equal(getCompositionTenor(), other.getCompositionTenor()) &&
          JodaBeanUtils.equal(getStubTypeCompound(), other.getStubTypeCompound()) &&
          JodaBeanUtils.equal(isIsExchangeNotional(), other.isIsExchangeNotional()) &&
          JodaBeanUtils.equal(getSettlementDays(), other.getSettlementDays()) &&
          JodaBeanUtils.equal(isIsEOM(), other.isIsEOM()) &&
          JodaBeanUtils.equal(getStubTypeLeg(), other.getStubTypeLeg()) &&
          JodaBeanUtils.equal(getPaymentLag(), other.getPaymentLag()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getIborIndexConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPaymentTenor());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCompoundingType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCompositionTenor());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStubTypeCompound());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIsExchangeNotional());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementDays());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIsEOM());
    hash += hash * 31 + JodaBeanUtils.hashCode(getStubTypeLeg());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPaymentLag());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the ibor index convention.
   * @return the value of the property, not null
   */
  public ExternalId getIborIndexConvention() {
    return _iborIndexConvention;
  }

  /**
   * Sets the ibor index convention.
   * @param iborIndexConvention  the new value of the property, not null
   */
  public void setIborIndexConvention(ExternalId iborIndexConvention) {
    JodaBeanUtils.notNull(iborIndexConvention, "iborIndexConvention");
    this._iborIndexConvention = iborIndexConvention;
  }

  /**
   * Gets the the {@code iborIndexConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> iborIndexConvention() {
    return metaBean().iborIndexConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment tenor.
   * @return the value of the property, not null
   */
  public Tenor getPaymentTenor() {
    return _paymentTenor;
  }

  /**
   * Sets the payment tenor.
   * @param paymentTenor  the new value of the property, not null
   */
  public void setPaymentTenor(Tenor paymentTenor) {
    JodaBeanUtils.notNull(paymentTenor, "paymentTenor");
    this._paymentTenor = paymentTenor;
  }

  /**
   * Gets the the {@code paymentTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> paymentTenor() {
    return metaBean().paymentTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the compounding type.
   * @return the value of the property, not null
   */
  public CompoundingType getCompoundingType() {
    return _compoundingType;
  }

  /**
   * Sets the compounding type.
   * @param compoundingType  the new value of the property, not null
   */
  public void setCompoundingType(CompoundingType compoundingType) {
    JodaBeanUtils.notNull(compoundingType, "compoundingType");
    this._compoundingType = compoundingType;
  }

  /**
   * Gets the the {@code compoundingType} property.
   * @return the property, not null
   */
  public final Property<CompoundingType> compoundingType() {
    return metaBean().compoundingType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the composition tenor, i.e. the tenor of the sub-periods compounded into the payment tenor.
   * @return the value of the property, not null
   */
  public Tenor getCompositionTenor() {
    return _compositionTenor;
  }

  /**
   * Sets the composition tenor, i.e. the tenor of the sub-periods compounded into the payment tenor.
   * @param compositionTenor  the new value of the property, not null
   */
  public void setCompositionTenor(Tenor compositionTenor) {
    JodaBeanUtils.notNull(compositionTenor, "compositionTenor");
    this._compositionTenor = compositionTenor;
  }

  /**
   * Gets the the {@code compositionTenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> compositionTenor() {
    return metaBean().compositionTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stub type.
   * @return the value of the property, not null
   */
  public StubType getStubTypeCompound() {
    return _stubTypeCompound;
  }

  /**
   * Sets the stub type.
   * @param stubTypeCompound  the new value of the property, not null
   */
  public void setStubTypeCompound(StubType stubTypeCompound) {
    JodaBeanUtils.notNull(stubTypeCompound, "stubTypeCompound");
    this._stubTypeCompound = stubTypeCompound;
  }

  /**
   * Gets the the {@code stubTypeCompound} property.
   * @return the property, not null
   */
  public final Property<StubType> stubTypeCompound() {
    return metaBean().stubTypeCompound().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets is the notional exchanged.
   * @return the value of the property
   */
  public boolean isIsExchangeNotional() {
    return _isExchangeNotional;
  }

  /**
   * Sets is the notional exchanged.
   * @param isExchangeNotional  the new value of the property
   */
  public void setIsExchangeNotional(boolean isExchangeNotional) {
    this._isExchangeNotional = isExchangeNotional;
  }

  /**
   * Gets the the {@code isExchangeNotional} property.
   * @return the property, not null
   */
  public final Property<Boolean> isExchangeNotional() {
    return metaBean().isExchangeNotional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the number of settlement days.
   * @return the value of the property
   */
  public int getSettlementDays() {
    return _settlementDays;
  }

  /**
   * Sets the number of settlement days.
   * @param settlementDays  the new value of the property
   */
  public void setSettlementDays(int settlementDays) {
    this._settlementDays = settlementDays;
  }

  /**
   * Gets the the {@code settlementDays} property.
   * @return the property, not null
   */
  public final Property<Integer> settlementDays() {
    return metaBean().settlementDays().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets is the schedule end-of-month.
   * @return the value of the property
   */
  public boolean isIsEOM() {
    return _isEOM;
  }

  /**
   * Sets is the schedule end-of-month.
   * @param isEOM  the new value of the property
   */
  public void setIsEOM(boolean isEOM) {
    this._isEOM = isEOM;
  }

  /**
   * Gets the the {@code isEOM} property.
   * @return the property, not null
   */
  public final Property<Boolean> isEOM() {
    return metaBean().isEOM().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stub type.
   * @return the value of the property, not null
   */
  public StubType getStubTypeLeg() {
    return _stubTypeLeg;
  }

  /**
   * Sets the stub type.
   * @param stubTypeLeg  the new value of the property, not null
   */
  public void setStubTypeLeg(StubType stubTypeLeg) {
    JodaBeanUtils.notNull(stubTypeLeg, "stubTypeLeg");
    this._stubTypeLeg = stubTypeLeg;
  }

  /**
   * Gets the the {@code stubTypeLeg} property.
   * @return the property, not null
   */
  public final Property<StubType> stubTypeLeg() {
    return metaBean().stubTypeLeg().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payment lag in days.
   * @return the value of the property
   */
  public int getPaymentLag() {
    return _paymentLag;
  }

  /**
   * Sets the payment lag in days.
   * @param paymentLag  the new value of the property
   */
  public void setPaymentLag(int paymentLag) {
    this._paymentLag = paymentLag;
  }

  /**
   * Gets the the {@code paymentLag} property.
   * @return the property, not null
   */
  public final Property<Integer> paymentLag() {
    return metaBean().paymentLag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CompoundingIborLegConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code iborIndexConvention} property.
     */
    private final MetaProperty<ExternalId> _iborIndexConvention = DirectMetaProperty.ofReadWrite(
        this, "iborIndexConvention", CompoundingIborLegConvention.class, ExternalId.class);
    /**
     * The meta-property for the {@code paymentTenor} property.
     */
    private final MetaProperty<Tenor> _paymentTenor = DirectMetaProperty.ofReadWrite(
        this, "paymentTenor", CompoundingIborLegConvention.class, Tenor.class);
    /**
     * The meta-property for the {@code compoundingType} property.
     */
    private final MetaProperty<CompoundingType> _compoundingType = DirectMetaProperty.ofReadWrite(
        this, "compoundingType", CompoundingIborLegConvention.class, CompoundingType.class);
    /**
     * The meta-property for the {@code compositionTenor} property.
     */
    private final MetaProperty<Tenor> _compositionTenor = DirectMetaProperty.ofReadWrite(
        this, "compositionTenor", CompoundingIborLegConvention.class, Tenor.class);
    /**
     * The meta-property for the {@code stubTypeCompound} property.
     */
    private final MetaProperty<StubType> _stubTypeCompound = DirectMetaProperty.ofReadWrite(
        this, "stubTypeCompound", CompoundingIborLegConvention.class, StubType.class);
    /**
     * The meta-property for the {@code isExchangeNotional} property.
     */
    private final MetaProperty<Boolean> _isExchangeNotional = DirectMetaProperty.ofReadWrite(
        this, "isExchangeNotional", CompoundingIborLegConvention.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code settlementDays} property.
     */
    private final MetaProperty<Integer> _settlementDays = DirectMetaProperty.ofReadWrite(
        this, "settlementDays", CompoundingIborLegConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code isEOM} property.
     */
    private final MetaProperty<Boolean> _isEOM = DirectMetaProperty.ofReadWrite(
        this, "isEOM", CompoundingIborLegConvention.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code stubTypeLeg} property.
     */
    private final MetaProperty<StubType> _stubTypeLeg = DirectMetaProperty.ofReadWrite(
        this, "stubTypeLeg", CompoundingIborLegConvention.class, StubType.class);
    /**
     * The meta-property for the {@code paymentLag} property.
     */
    private final MetaProperty<Integer> _paymentLag = DirectMetaProperty.ofReadWrite(
        this, "paymentLag", CompoundingIborLegConvention.class, Integer.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "iborIndexConvention",
        "paymentTenor",
        "compoundingType",
        "compositionTenor",
        "stubTypeCompound",
        "isExchangeNotional",
        "settlementDays",
        "isEOM",
        "stubTypeLeg",
        "paymentLag");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1542426233:  // iborIndexConvention
          return _iborIndexConvention;
        case -507548582:  // paymentTenor
          return _paymentTenor;
        case -1936397775:  // compoundingType
          return _compoundingType;
        case -1679443146:  // compositionTenor
          return _compositionTenor;
        case -1172460845:  // stubTypeCompound
          return _stubTypeCompound;
        case 348962765:  // isExchangeNotional
          return _isExchangeNotional;
        case -295948000:  // settlementDays
          return _settlementDays;
        case 100464505:  // isEOM
          return _isEOM;
        case 1272752102:  // stubTypeLeg
          return _stubTypeLeg;
        case 1612870060:  // paymentLag
          return _paymentLag;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CompoundingIborLegConvention> builder() {
      return new DirectBeanBuilder<CompoundingIborLegConvention>(new CompoundingIborLegConvention());
    }

    @Override
    public Class<? extends CompoundingIborLegConvention> beanType() {
      return CompoundingIborLegConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code iborIndexConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> iborIndexConvention() {
      return _iborIndexConvention;
    }

    /**
     * The meta-property for the {@code paymentTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> paymentTenor() {
      return _paymentTenor;
    }

    /**
     * The meta-property for the {@code compoundingType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CompoundingType> compoundingType() {
      return _compoundingType;
    }

    /**
     * The meta-property for the {@code compositionTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> compositionTenor() {
      return _compositionTenor;
    }

    /**
     * The meta-property for the {@code stubTypeCompound} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StubType> stubTypeCompound() {
      return _stubTypeCompound;
    }

    /**
     * The meta-property for the {@code isExchangeNotional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isExchangeNotional() {
      return _isExchangeNotional;
    }

    /**
     * The meta-property for the {@code settlementDays} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> settlementDays() {
      return _settlementDays;
    }

    /**
     * The meta-property for the {@code isEOM} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isEOM() {
      return _isEOM;
    }

    /**
     * The meta-property for the {@code stubTypeLeg} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StubType> stubTypeLeg() {
      return _stubTypeLeg;
    }

    /**
     * The meta-property for the {@code paymentLag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> paymentLag() {
      return _paymentLag;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
