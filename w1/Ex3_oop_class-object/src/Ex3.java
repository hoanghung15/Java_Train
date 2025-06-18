import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex3 {
    public static float avgScore(List<Student> listStudent) {
        float totalScore = 0;
        try {
            if (listStudent == null || listStudent.isEmpty()) {
                throw new IllegalArgumentException("Danh sách sinh viên trống");
            }
            for (Student student : listStudent) {
                totalScore += student.getScore();
            }
            return totalScore / listStudent.size();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        int n;
        boolean flag = true;
        List<Student> listStudent = new ArrayList<>();
        while (flag) {
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Display Average Score");
            System.out.println("4. Exit");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter Student Name: ");

                    sc.nextLine();
                    String name = sc.nextLine();


                    System.out.println("Enter Student age: ");
                    int age = sc.nextInt();

                    System.out.println("Enter Student Score: ");
                    float score = sc.nextFloat();

                    Student student = new Student(name, age, score);
                    listStudent.add(student);

                    System.out.println("Add student successfully!");
                    break;
                case 2:
                    for (Student stu : listStudent) {
                        System.out.println("Student information: " + stu.toString());
                    }
                    break;
                case 3:
                    System.out.println(avgScore(listStudent));
                    break;
                case 4:
                    flag = false;
                    break;
                default:
            }
        }
    }
}