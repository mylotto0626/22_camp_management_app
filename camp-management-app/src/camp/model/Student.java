package camp.model;

public class Student {
    private String studentId;
    private String studentName;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String toString() {
        return "학생 ID: " + studentId + ", 이름: " + studentName;
    }
}
