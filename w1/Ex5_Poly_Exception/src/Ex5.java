import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void printNameOfStudent() {
//        String fileName = "C:\\Users\\hunghv\\Downloads\\Project\\TrainingJava\\w1\\Ex5_Poly_Exception\\src\\Student.txt";
        try {
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedReader br = new BufferedReader(new InputStreamReader(Ex5.class.getResourceAsStream("/Student.txt")));
            String name;
            while ((name = br.readLine()) != null) {
                System.out.println(name);
            }
        } catch (IIOException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read_write_with_file(){
        try{
            FileWriter fw = new FileWriter("out.txt");
            fw.write("hello");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        Divide();
        printNameOfStudent();
        read_write_with_file();
    }
}