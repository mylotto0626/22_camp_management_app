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
        this.subjects = new ArrayList<>(); // 수강 과목
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

    public List<Subject> getSubjects() {
        return subjects;
    }
}


//