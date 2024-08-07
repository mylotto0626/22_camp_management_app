package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjects;



    public Student(String seq, String studentName) { // 생성자
        this.studentId = seq; // 학생 고유번호
        this.studentName = studentName; // 학생 이름
        this.subjects = new ArrayList<>();
    }


    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }


    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    // Optional: Setter
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String toString() {
        return "학생 ID: " + studentId + ", 이름: " + studentName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

}


//