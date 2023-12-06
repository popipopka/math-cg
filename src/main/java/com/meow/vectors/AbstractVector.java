package com.meow.vectors;

import java.util.Arrays;

/**
 * @param <T> vector type
 */

public abstract class AbstractVector<T extends Vector> implements Vector<T> {
    protected double[] coords;
    protected int size;

    protected AbstractVector(int expectedSize, double[] coords) {
        this.coords = coords;
        this.size = coords.length;
        checkSize(expectedSize);
    }

    private void checkSize(int expectedSize) {
        if (this.coords.length != expectedSize) {
            throw new IllegalArgumentException("Размерность данного вектора равна " + expectedSize);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractVector<?> that = (AbstractVector<?>) o;

        return Arrays.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    @Override
    public double[] getCoords() {
        return coords;
    }

    @Override
    public void add(T v) {
        double[] vCoord = v.getCoords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] += vCoord[i];
        }
    }

    @Override
    public void subtract(T v) {
        double[] vCoord = v.getCoords();
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] -= vCoord[i];
        }
    }

    @Override
    public void multiply(double number) {
        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] *= number;
        }
    }

    @Override
    public void divide(double number) {
        if (number == 0) throw new ArithmeticException("Деление на ноль запрещено");

        for (int i = 0; i < this.coords.length; i++) {
            this.coords[i] /= number;
        }
    }

    @Override
    public double length() {
        double sum = 0;
        for (double e : this.coords) {
            sum += Math.pow(e, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public void normalize() {
        double length = length();

        if (length != 0) divide(length);
    }
}
