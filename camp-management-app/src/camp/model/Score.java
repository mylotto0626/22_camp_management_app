package camp.model;

public class Score {
    private Integer scoreNum; // 점수
    private Integer scoreRound; // 점수 회차 변수   ????? = xxxround
    private Character scoreGrade; // 점수 등급 변수
    private String scoreStudentId; // 수강생 ID
    private String scoreSubjectId; // 과목 ID

    public Score(String scoreStudentId, String scoreSubjectId, Integer scoreRound, Integer scoreNum, Character scoreGrade) {
        this.scoreStudentId = scoreStudentId;
        this.scoreSubjectId = scoreSubjectId;
        this.scoreRound = scoreRound;
        this.scoreNum = scoreNum;
        this.scoreGrade = scoreGrade;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public void setScoreGrade(Character scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    // Getter

    public String getScoreStudentId() {
        return scoreStudentId;
    }

    public String getScoreSubjectId() {
        return scoreSubjectId;
    }

    public Integer getScoreRound() {
        return scoreRound;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public Character getScoreGrade() {
        return scoreGrade;
    }


}
