package com.meow.matrices;

/**
 *
 * @param <T>
 *     matrix type
 * @param <V>
 *     vector type
 */
public interface SquareMatrix<T, V> {
    void setValue(int row, int col, double value);

    double[][] getData();

    T add(T m);

    T subtract(T m);

    V multiplyVector(V v);

    T multiply(T m);

    void transpose();
}
