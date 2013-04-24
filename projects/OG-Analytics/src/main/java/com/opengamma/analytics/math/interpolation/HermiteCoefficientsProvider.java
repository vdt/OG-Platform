/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation;

import java.util.Arrays;

/**
 * Hermite interpolation is determined if one specifies first derivatives for a cubic interpolant and first and second derivatives for a quintic interpolant
 */
public class HermiteCoefficientsProvider {

  /**
   * @param values (yValues_i)
   * @param intervals (xValues_{i+1} - xValues_{i})
   * @param slopes (yValues_{i+1} - yValues_{i})/(xValues_{i+1} - xValues_{i})
   * @param first first derivatives at xValues_i
   * @return Coefficient matrix whose i-th row vector is { a_n, a_{n-1}, ...} for the i-th interval, 
   * where a_n, a_{n-1},... are coefficients of f(x) = a_n (x-x_i)^n + a_{n-1} (x-x_i)^{n-1} + .... with n=3
   */
  public double[][] solve(final double[] values, final double[] intervals, final double[] slopes, final double[] first) {
    final int nInt = intervals.length;
    double[][] res = new double[nInt][4];
    for (int i = 0; i < nInt; ++i) {
      Arrays.fill(res[i], 0.);
    }

    for (int i = 0; i < nInt; ++i) {
      res[i][3] = values[i];
      res[i][2] = first[i];
      res[i][1] = (3. * slopes[i] - first[i + 1] - 2. * first[i]) / intervals[i];
      res[i][0] = -(2. * slopes[i] - first[i + 1] - first[i]) / intervals[i] / intervals[i];
    }

    return res;
  }

  /**
   * @param values (yValues_i)
   * @param intervals (xValues_{i+1} - xValues_{i})
   * @param slopes (yValues_{i+1} - yValues_{i})/(xValues_{i+1} - xValues_{i})
   * @param first First derivatives at xValues_i
   * @param second Second derivatives at xValues_i
   * @return Coefficient matrix whose i-th row vector is { a_n, a_{n-1}, ...} for the i-th interval, 
   * where a_n, a_{n-1},... are coefficients of f(x) = a_n (x-x_i)^n + a_{n-1} (x-x_i)^{n-1} + .... with n=5
   */
  public double[][] solve(final double[] values, final double[] intervals, final double[] slopes, final double[] first, final double[] second) {
    final int nInt = intervals.length;
    double[][] res = new double[nInt][6];
    for (int i = 0; i < nInt; ++i) {
      Arrays.fill(res[i], 0.);
    }

    for (int i = 0; i < nInt; ++i) {
      res[i][5] = values[i];
      res[i][4] = first[i];
      res[i][3] = 0.5 * second[i];
      res[i][2] = 0.5 * (second[i + 1] - 3. * second[i]) / intervals[i] + 2. * (5. * slopes[i] - 3. * first[i] - 2. * first[i + 1]) / intervals[i] / intervals[i];
      res[i][1] = 0.5 * (3. * second[i] - 2. * second[i + 1]) / intervals[i] / intervals[i] + (8. * first[i] + 7. * first[i + 1] - 15. * slopes[i]) / intervals[i] / intervals[i] / intervals[i];
      res[i][0] = 0.5 * (second[i + 1] - second[i]) / intervals[i] / intervals[i] / intervals[i] + 3. * (2. * slopes[i] - first[i + 1] - first[i]) / intervals[i] / intervals[i] / intervals[i] /
          intervals[i];
    }

    return res;
  }

  /**
   * @param xValues 
   * @return Intervals of xValues, ( xValues_{i+1} - xValues_i )
   */
  public double[] intervalsCalculator(final double[] xValues) {

    final int nDataPts = xValues.length;
    double[] intervals = new double[nDataPts - 1];

    for (int i = 0; i < nDataPts - 1; ++i) {
      intervals[i] = xValues[i + 1] - xValues[i];
    }

    return intervals;
  }

  /**
   * @param yValues Y values of data
   * @param intervals Intervals of x data
   * @return ( yValues_{i+1} - yValues_i )/( xValues_{i+1} - xValues_i )
   */
  public double[] slopesCalculator(final double[] yValues, final double[] intervals) {

    final int nDataPts = yValues.length;
    double[] slopes = new double[nDataPts - 1];

    for (int i = 0; i < nDataPts - 1; ++i) {
      slopes[i] = (yValues[i + 1] - yValues[i]) / intervals[i];
    }

    return slopes;
  }

  /** 
   * @param ints1  
   * @param ints2 
   * @param slope1 
   * @param slope2 
   * @return Value of derivative at each endpoint
   */
  public double endpointDerivatives(final double ints1, final double ints2, final double slope1, final double slope2) {
    final double val = (2. * ints1 + ints2) * slope1 / (ints1 + ints2) - ints1 * slope2 / (ints1 + ints2);

    if (Math.signum(val) != Math.signum(slope1)) {
      return 0.;
    } else {
      if (Math.signum(slope1) != Math.signum(slope2) && Math.abs(val) > 3. * Math.abs(slope1)) {
        return 3. * slope1;
      }
    }
    return val;
  }
}
