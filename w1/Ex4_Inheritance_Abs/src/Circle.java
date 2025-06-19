public class Circle extends Shape {
    public int R;

    public Circle(int r) {
        R = r;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    @Override
    public float getArea() {
        return (float) (Math.PI * Math.pow(R,2));
    }
}
