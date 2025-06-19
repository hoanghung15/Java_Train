
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ex5 {
    public static void Divide() {
        int a, b;
        System.out.println("Enter number: ");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        try {
            if (b == 0) throw new DivideError("Can not Divide with 0");
            float rs = (float) a / b;
            System.out.printf("Result: %.2f\n", rs);
        } catch (DivideError e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void read_file() {
        File f = new File("student.txt");

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            //Hoang Hung | 15 | 8
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String data[] = line.split("[|]");
                String name = data[0].trim();
                int age = Integer.parseInt(data[1].trim());
                String score = data[2];
                Student student = new Student(name, age, Float.parseFloat(score));
                System.out.println(student.toString());

            }
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        Divide();
        read_file();
    }
}