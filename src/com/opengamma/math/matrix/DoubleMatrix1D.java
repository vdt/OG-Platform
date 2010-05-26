/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.math.matrix;

import java.util.Arrays;

/**
 * A minimal implementation of a vector (in the maths sense) of doubles class 
 */
public class DoubleMatrix1D implements Matrix<Double> {
  private final double[] _data;
  private final int _elements;

  public DoubleMatrix1D(final Double[] data) {
    if (data == null) {
      throw new IllegalArgumentException("Cannot initialize matrix with null data");
    }
    _elements = data.length;
    _data = new double[_elements];
    for (int i = 0; i < _elements; i++) {
      _data[i] = data[i];

    }
  }

  public DoubleMatrix1D(final double[] primitives) {
    if (primitives == null) {
      throw new IllegalArgumentException("Cannot initialize matrix with null data");
    }
    _elements = primitives.length;
    _data = new double[_elements];
    for (int i = 0; i < _elements; i++) {
      _data[i] = primitives[i];
    }
  }

  /**
   * Returns the underlying vector data. If this is changed so is Vector
   * @see toArray() to get clone of data
   * @return array containing the vector elements 
   */
  public double[] getData() {
    return _data;
  }

  /**
   * Convert the vector to a double array. 
   * The array is independent from vector data, it's elements are copied.
   * @return array containing a copy of vector elements
   */
  public double[] toArray() {
    return _data.clone();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opengamma.math.matrix.Matrix#getNumberOfElements()
   */
  @Override
  public int getNumberOfElements() {
    return _elements;
  }

  /**
   * @param index 
   * @return the element at the index
   */
  @Override
  public Double getEntry(final int... index) {
    return _data[index[0]];
  }

  public double dotProduct(final DoubleMatrix1D other) {

    return dotProduct(this, other);
  }

  public static double dotProduct(final DoubleMatrix1D a, final DoubleMatrix1D b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Passed in a null");
    }
    final int l = a._elements;
    if (b._elements != l) {
      throw new IllegalArgumentException("Matrix size mismatch");
    }
    double sum = 0.0;
    for (int i = 0; i < l; i++) {
      sum += a._data[i] * b._data[i];
    }
    return sum;
  }

  /**
   * Multiply a vector by a scalar 
   * @param vector
   * @param scalar
   * @return a new vector which each element the input vector multiplied by the scalar 
   */
  public static DoubleMatrix1D multiply(final DoubleMatrix1D vector, double scalar) {
    if (vector == null) {
      throw new IllegalArgumentException("Passed in a null");
    }

    double[] temp = new double[vector._elements];
    for (int i = 0; i < vector._elements; i++) {
      temp[i] = scalar * vector._data[i];
    }
    return new DoubleMatrix1D(temp);
  }

  /**
   * Multiply a vector by a scalar 
   * @param scalar
   * @param vector
   * @return  a new vector which each element the input vector multiplied by the scalar 
   */
  public static DoubleMatrix1D multiply(double scalar, final DoubleMatrix1D vector) {
    return multiply(vector, scalar);
  }

  /**
  * Multiply this vector by a scalar 
  * @param scalar
  * @return  a new vector which each element of this vector multiplied by the scalar 
  */
  public DoubleMatrix1D multiply(double scalar) {
    return multiply(this, scalar);
  }

  public DoubleMatrix1D add(final DoubleMatrix1D other) {
    return add(this, other);
  }

  public static DoubleMatrix1D add(final DoubleMatrix1D a, final DoubleMatrix1D b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Passed in a null");
    }
    final int l = a._elements;
    if (b._elements != l) {
      throw new IllegalArgumentException("Matrix size mismatch");
    }
    double[] res = new double[l];
    for (int i = 0; i < l; i++) {
      res[i] = a._data[i] + b._data[i];
    }
    return new DoubleMatrix1D(res);
  }

  public DoubleMatrix1D subtract(final DoubleMatrix1D other) {
    return subtract(this, other);
  }

  public static DoubleMatrix1D subtract(final DoubleMatrix1D a, final DoubleMatrix1D b) {
    if (a == null || b == null) {
      throw new IllegalArgumentException("Passed in a null");
    }
    final int l = a._elements;
    if (b._elements != l) {
      throw new IllegalArgumentException("Matrix size mismatch");
    }
    double[] res = new double[l];
    for (int i = 0; i < l; i++) {
      res[i] = a._data[i] - b._data[i];
    }
    return new DoubleMatrix1D(res);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_data);
    result = prime * result + _elements;
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DoubleMatrix1D other = (DoubleMatrix1D) obj;
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    if (_elements != other._elements) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    for (final Double d : _data) {
      sb.append("(" + d + ")\n");
    }
    return sb.toString();
  }
}
