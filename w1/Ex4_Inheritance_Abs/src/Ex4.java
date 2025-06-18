//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ex4 {
    public static void main(String[] args) {
        Circle c = new Circle(5);
        Rectangle r = new Rectangle(5, 5);

        System.out.printf("Area of circle: %.2f\n", c.getArea());
        System.out.printf("Area of rectangle: %.2f\n", r.getArea());
    }
}