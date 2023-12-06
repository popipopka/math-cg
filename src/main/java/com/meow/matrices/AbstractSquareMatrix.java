package com.meow.matrices;

import com.meow.vectors.Vector;

import java.util.Arrays;

public abstract class AbstractSquareMatrix<T extends SquareMatrix, V extends Vector> implements SquareMatrix<T, V> {
    protected double[][] data;
    private final int size;

    protected AbstractSquareMatrix(int expectedSize, double[][] data) {
        this.data = data;
        this.size = expectedSize;
        checkSize(expectedSize);
    }

    private void checkSize(int expectedSize) {
        if (this.size != expectedSize) {
            throw new IllegalArgumentException(String.format("Размерность данной матрицы %d на %d", expectedSize, expectedSize));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractSquareMatrix<?, ?> that = (AbstractSquareMatrix<?, ?>) o;

        if (size != that.size) return false;
        return Arrays.deepEquals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(data);
    }

    @Override
    public void setNull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.data[i][j] = 0;
            }
        }
    }

    @Override
    public void setUnit() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    this.data[i][j] = 1;
                    continue;
                }
                this.data[i][j] = 0;
            }
        }
    }

    protected abstract T initialCreateMatrix(double[][] data);

    protected abstract V initialCreateVector(double[] data);

    @Override
    public T add(T m) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = this.data[i][j] + m.getData()[i][j];
            }
        }

        return initialCreateMatrix(newData);
    }

    @Override
    public T subtract(T m) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = this.data[i][j] - m.getData()[i][j];
            }
        }

        return initialCreateMatrix(newData);
    }

    @Override
    public V multiplyVector(V v) {
        double[] newData = new double[size];

        double[] vCoord = v.getCoords();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i] += this.data[i][j] * vCoord[j];
            }
        }
        return initialCreateVector(newData);
    }

    @Override
    public T multiply(T m) {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                for (int k = 0; k < size; k++) {
                    newData[i][j] += this.data[i][k] * m.getData()[k][j];
                }
            }
        }
        return initialCreateMatrix(newData);
    }

    @Override
    public void transpose() {
        double[][] newData = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newData[i][j] = data[j][i];
            }
        }
        this.data = newData;
    }

    @Override
    public double[][] getData() {
        return this.data;
    }
}
