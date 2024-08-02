package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectType;
    private List<String> mainSubjects;

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.mainSubjects = new ArrayList<>();
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

}
