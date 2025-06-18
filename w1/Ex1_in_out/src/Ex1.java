import java.util.Scanner;

public class Ex1 {

    public static void in_out() {
        int a, b;

        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        b = input.nextInt();

        try {
            if (b == 0) {
                throw new DivideError("Can not divide by zero");
            }
            float div = (float) a / b;
            System.out.println("div = " + String.format("%.2f", div));
        } catch (DivideError e) {
            System.out.println("Exception: " + e.getMessage());
        }

        int sum = a + b;
        int sub = a - b;
        long mul = (long) a * b;
        float div = (float) a / b;

        System.out.println("sum = " + sum);
        System.out.println("sub = " + sub);
        System.out.println("mul = " + mul);
        System.out.println("div = " + String.format("%.2f", div));

        input.close();
    }

    public static void circle(int R) {
        float perimeter = (float) (2 * Math.PI * R);
        float area = (float) (Math.PI * Math.pow(R, 2));

        System.out.println("Per and area of Circle");
        System.out.println("perimeter = " + String.format("%.2f", perimeter));
        System.out.println("area = " + String.format("%.2f", area));
    }

    public static void main(String[] args) {
        System.out.println("Enter 2 num: ");
        in_out();
        circle(3);
    }
}