import java.util.Scanner;

public class Ex2 {
    public static boolean isEven(int a) {
        return a % 2 == 0;
    }

    public static boolean isDivisibleBy3(int a) {
        return a % 3 == 0;
    }

    public static boolean isDivisibleBy3withLongNumber(String a) {
        int sum = 0;
        boolean flag = false;

        for (int i = 0; i < a.length(); i++) {
            int tmp = Integer.parseInt(a.charAt(i) + "");
            sum += tmp;
        }

        if (sum % 3 == 0) {
            flag = true;
        }
        return flag;
    }

    public static void printMultiplicationTable() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + " x " + j + " = " + (i * j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int a;
        System.out.println("Enter a number: ");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();

        System.out.println(isEven(a) ? "Even" : "Odd");
        System.out.println(isDivisibleBy3(a) ? "Divisible by 3" : "No Divisible by 3");
        System.out.println(isDivisibleBy3withLongNumber("666666666666666666661666") ? "Divisible by 3" : "No Divisible by 3");

        System.out.println("MultiplicationTable: ");
        printMultiplicationTable();
        sc.close();
    }
}