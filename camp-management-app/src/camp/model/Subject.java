package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectType;

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String toString() {
        return "선택할 필수 과목: " + subjectType + ", " + subjectName;
    }

}
