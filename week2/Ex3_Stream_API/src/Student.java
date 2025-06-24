public class Student {
    private float score;
    private int age;

    public Student(float score, int age) {
        this.score = score;
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", age=" + age +
                '}';
    }
}
