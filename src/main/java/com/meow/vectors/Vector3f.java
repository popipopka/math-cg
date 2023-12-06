package vectors;

public class Vector3f extends AbstractVector<Vector3f> {
    public Vector3f(double[] coords) {
        super(3, coords);
    }

    public double x() {
        return coords[0];
    }

    public double y() {
        return coords[1];
    }

    public double z() {
        return coords[2];
    }
}
