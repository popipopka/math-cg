package com.meow.matrices;

import com.meow.vectors.Vector;

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
    public void setValue(int row, int col, double value) {
        if (row > size || col > size) {
            throw new IndexOutOfBoundsException("Заданы неверные аргументы row или col");
        }

        this.data[row][col] = value;
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
                newData[i] += this.data[i][j] * vCoord[i];
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
                    newData[i][j] += this.data[k][j] * m.getData()[j][k];
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
