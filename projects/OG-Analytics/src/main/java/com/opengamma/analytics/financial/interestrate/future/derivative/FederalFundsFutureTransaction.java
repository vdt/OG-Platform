/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.future.derivative;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.util.ArgumentChecker;

/**
 * Description of an transaction on a Federal Funds Futures.
 */
public class FederalFundsFutureTransaction implements InstrumentDerivative {

  /**
   * The underlying future security.
   */
  private final FederalFundsFutureSecurity _underlyingFuture;
  /**
   * The quantity of the transaction. Can be positive or negative.
   */
  private final int _quantity;
  /**
   * The reference price. It is the transaction price on the transaction date and the last close (margining) price afterward.
   * The price is in relative number and not in percent. A standard price will be 0.985 and not 98.5.
   */
  private final double _referencePrice;

  /**
   * Constructor.
   * @param underlyingFuture The underlying future security.
   * @param quantity The quantity of the transaction. Can be positive or negative.
   * @param referencePrice The reference price. It is the transaction price on the transaction date and the last close (margining) price afterward.
   */
  public FederalFundsFutureTransaction(final FederalFundsFutureSecurity underlyingFuture, final int quantity, final double referencePrice) {
    ArgumentChecker.notNull(underlyingFuture, "Future");
    _underlyingFuture = underlyingFuture;
    _quantity = quantity;
    _referencePrice = referencePrice;
  }

  /**
   * Gets the underlying future security.
   * @return The future.
   */
  public FederalFundsFutureSecurity getUnderlyingFuture() {
    return _underlyingFuture;
  }

  /**
   * Gets the quantity of the transaction. Can be positive or negative.
   * @return The quantity.
   */
  public int getQuantity() {
    return _quantity;
  }

  /**
   * Gets the reference price. It is the transaction price on the transaction date and the last close (margining) price afterward.
   * @return The reference price.
   */
  public double getReferencePrice() {
    return _referencePrice;
  }

  @Override
  public <S, T> T accept(final InstrumentDerivativeVisitor<S, T> visitor, final S data) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitFederalFundsFutureTransaction(this, data);
  }

  @Override
  public <T> T accept(final InstrumentDerivativeVisitor<?, T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitFederalFundsFutureTransaction(this);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _quantity;
    long temp;
    temp = Double.doubleToLongBits(_referencePrice);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + _underlyingFuture.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final FederalFundsFutureTransaction other = (FederalFundsFutureTransaction) obj;
    if (_quantity != other._quantity) {
      return false;
    }
    if (Double.doubleToLongBits(_referencePrice) != Double.doubleToLongBits(other._referencePrice)) {
      return false;
    }
    if (!ObjectUtils.equals(_underlyingFuture, other._underlyingFuture)) {
      return false;
    }
    return true;
  }

}
