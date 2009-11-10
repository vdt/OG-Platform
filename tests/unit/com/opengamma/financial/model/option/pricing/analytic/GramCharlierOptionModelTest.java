/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.analytic;

import static org.junit.Assert.assertEquals;

import javax.time.calendar.ZonedDateTime;

import org.junit.Test;

import com.opengamma.financial.model.interestrate.curve.ConstantInterestRateDiscountCurve;
import com.opengamma.financial.model.interestrate.curve.DiscountCurve;
import com.opengamma.financial.model.option.definition.EuropeanVanillaOptionDefinition;
import com.opengamma.financial.model.option.definition.OptionDefinition;
import com.opengamma.financial.model.option.definition.SkewKurtosisOptionDataBundle;
import com.opengamma.financial.model.option.definition.StandardOptionDataBundle;
import com.opengamma.financial.model.volatility.surface.ConstantVolatilitySurface;
import com.opengamma.financial.model.volatility.surface.VolatilitySurface;
import com.opengamma.util.time.DateUtil;
import com.opengamma.util.time.Expiry;

/**
 * 
 * @author emcleod
 */
public class GramCharlierOptionModelTest {
  private static final AnalyticOptionModel<OptionDefinition, SkewKurtosisOptionDataBundle> GRAM_CHARLIER = new GramCharlierOptionModel();
  private static final AnalyticOptionModel<OptionDefinition, StandardOptionDataBundle> BSM = new BlackScholesMertonModel();
  private static final DiscountCurve CURVE = new ConstantInterestRateDiscountCurve(0.05);
  private static final double B = 0.05;
  private static final VolatilitySurface SURFACE = new ConstantVolatilitySurface(0.3);
  private static final double SPOT = 30;
  private static final ZonedDateTime DATE = DateUtil.getUTCDate(2009, 1, 1);
  private static final Expiry EXPIRY = new Expiry(DateUtil.getDateOffsetWithYearFraction(DATE, 5. / 12));
  private static final double PERIODS_PER_YEAR = 12;
  private static final double SKEW = -2.3 / Math.sqrt(PERIODS_PER_YEAR);
  private static final double KURTOSIS = 1.2 / PERIODS_PER_YEAR;
  private static final SkewKurtosisOptionDataBundle NORMAL_DATA = new SkewKurtosisOptionDataBundle(CURVE, B, SURFACE, SPOT, DATE, 0, 0);
  private static final SkewKurtosisOptionDataBundle DATA = new SkewKurtosisOptionDataBundle(CURVE, B, SURFACE, SPOT, DATE, SKEW, KURTOSIS);
  private static final OptionDefinition CALL = new EuropeanVanillaOptionDefinition(30, EXPIRY, true);
  private static final OptionDefinition PUT = new EuropeanVanillaOptionDefinition(30, EXPIRY, false);
  private static final double EPS = 1e-6;

  @Test(expected = IllegalArgumentException.class)
  public void testNullDefinition() {
    GRAM_CHARLIER.getPricingFunction(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullData() {
    GRAM_CHARLIER.getPricingFunction(CALL).evaluate((SkewKurtosisOptionDataBundle) null);
  }

  @Test
  public void test() {
    assertEquals(BSM.getPricingFunction(CALL).evaluate(NORMAL_DATA), GRAM_CHARLIER.getPricingFunction(CALL).evaluate(NORMAL_DATA), EPS);
    assertEquals(BSM.getPricingFunction(PUT).evaluate(NORMAL_DATA), GRAM_CHARLIER.getPricingFunction(PUT).evaluate(NORMAL_DATA), EPS);
    assertEquals(2.519585, GRAM_CHARLIER.getPricingFunction(CALL).evaluate(DATA), EPS);
    assertEquals(1.901050, GRAM_CHARLIER.getPricingFunction(PUT).evaluate(DATA), EPS);
  }
}
