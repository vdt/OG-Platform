/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.math.statistics.descriptive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opengamma.math.function.Function2D;

/**
 * 
 */
public class LognormalFisherKurtosisFromVolatilityCalculatorTest {
  private static final Function2D<Double, Double> F = new LognormalFisherKurtosisFromVolatilityCalculator();
  private static final double SIGMA = 0.3;
  private static final double T = 0.25;

  @Test(expected = IllegalArgumentException.class)
  public void testNullSigma() {
    F.evaluate(null, T);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullT() {
    F.evaluate(SIGMA, null);
  }

  @Test
  public void test() {
    assertEquals(F.evaluate(SIGMA, T), 0.3719, 1e-4);
  }
}
