import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void filterChain() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(7, 11));
        studentList.add(new Student(8, 11));
        studentList.add(new Student(9, 11));
        studentList.add(new Student(10, 11));
        studentList.add(new Student(11, 11));
        studentList.add(new Student(12, 11));
        studentList.add(new Student(13, 11));
        studentList.add(new Student(14, 22));
        studentList.add(new Student(15, 22));
        studentList.add(new Student(16, 20));
        studentList.add(new Student(17, 21));
        studentList.add(new Student(18, 22));
        studentList.add(new Student(19, 22));

//        studentList.stream()
//                .filter(s -> s.getScore() >= 7)
//                .forEach(System.out::println);
//
        Map<Integer, List<Student>> groupByAge = studentList.stream()
                .filter(student -> student.getAge() >= 18)
                .collect(Collectors.groupingBy(Student::getAge));
        groupByAge.forEach((age, students) -> {
            students.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        filterChain();

    }
}