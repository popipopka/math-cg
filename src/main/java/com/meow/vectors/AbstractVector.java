package vectors;

/**
 *
 * @param <T>
 *     vector type
 */

public abstract class AbstractVector<T extends Vector<T>> implements Vector<T> {
    protected double[] coords;

    protected AbstractVector(int expectedSize, double[] coords) {
        this.coords = coords;
        checkSize(expectedSize);
    }

    private void checkSize(int expectedSize) {
        if(this.coords.length != expectedSize) {
            throw new IllegalArgumentException("Размерность данного вектора равна " + expectedSize);
        }
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
    public double scalarProduct(T v) {
        double[] vCoord = v.getCoords();

        double result = 0;
        for (int i = 0; i < coords.length; i++) {
            result += this.coords[i] + vCoord[i];
        }
        return result;
    }

    @Override
    public void normalize() {
        double length = length();

        if(length != 0) divide(length);
    }
}
