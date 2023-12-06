package com.meow.vectors;

/**
 *
 * @param <T>
 *     vector type
 */
public interface Vector<T> {
    double[] getCoords();

    void add(T v);

    void subtract(T v);

    void multiply(double number);

    void divide(double number);

    double length();

    void normalize();
}
