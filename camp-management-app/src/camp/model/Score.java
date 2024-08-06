package camp.model;

public class Score {
    private String scoreId; // 점수 ID
    private Integer scoreNum;
    private Integer scoreRound; // 점수 회차 변수   ????? = xxxround
    private Character scoreGrade; // 점수 등급 변수
    private String scoreStudentId; // 수강생 ID
    private String scoreSubjectId; // 과목 ID

    public Score(String scoreStudentId, String scoreSubjectId, Integer scoreRound, Integer scoreNum, Character scoreGrade) {
        this.scoreNum = scoreNum;
        this.scoreSubjectId = scoreSubjectId;
      this.scoreStudentId = scoreStudentId;
        this.scoreRound = scoreRound;
    }

    // Getter
    public String getScoreId() {
        return scoreId;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }



    public char mainTranslateGrade(int score) {
        if (95 <= score && score <= 100) {
            scoreGrade = 'A';
        } else if (90 <= score && score <= 94) {
            scoreGrade = 'B';
        } else if (80 <= score && score <= 89) {
            scoreGrade = 'C';
        } else if (70 <= score && score <= 79) {
            scoreGrade = 'D';
        } else if (60 <= score && score <= 69) {
            scoreGrade = 'F';
        } else if (score < 60) {
            scoreGrade = 'N';
        }
        return scoreGrade;
    }

    public char subTranslateGrade(int score) {
        if (90 <= score && score <= 100) {
            scoreGrade = 'A';
        } else if (80 <= score && score <= 89) {
            scoreGrade = 'B';
        } else if (70 <= score && score <= 79) {
            scoreGrade = 'C';
        } else if (60 <= score && score <= 69) {
            scoreGrade = 'D';
        } else if (50 <= score && score <= 59) {
            scoreGrade = 'F';
        } else if (score < 50) {
            scoreGrade = 'N';
        }
        return scoreGrade;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public Integer getScoreRound() {
        return scoreRound;
    }

    public Character getScoreGrade() {
        return scoreGrade;
    }

    public String getScoreStudentId() {
        return scoreStudentId;
    }

    public String getScoreSubjectId() {
        return scoreSubjectId;
    }
}

// 필드
// 점수 ID
// 과목 ID
// 수강생 ID
// 점수 회차
// 점수 등급

// 구현기능 : 점수 등록, 점수 수정, 점수 조회

// 점수 등록
// 점수 저장되어있는 List 접근
// 수강생 ID 가 찾으려는 수강생 ID 와 일치하는 데이터들 먼저 뽑기
// STUDENT 객체 안에 해당 수강생의 수강 강의 점수, 등급, 회차 등록
// (1) 회차와 점수 입력을 받게 -> 받으면 (2) 등급 변환되는 메서드 만들기

// 점수 수정
// 필요한 메서드
// 점수 수정할 메서드
// 회차별 점수 조회 , 해당과목을 조회 해야함
// 등급도 자동으로 변환 되야함  이 과정은 어차피 등록할 때 구현 될거라 괜찮음?


// for( 회차){
//scorestore[회차]
//}

// 점수 조회
// 태주 의견
// 등급은 score 클래스에 넣어서 메서드만 호출하면 업데이트 될 수 있게 하는 건 어떨까요?
//public String getGrade() {
//    if (score >= 90) return "A";
//    if (score >= 80) return "B";
//    if (score >= 70) return "C";
//    return "D";
//}
//


//boolean foundScores = false;
//        for (Score score : scoreStore) {
//        if (student.getStudentId() && score.getSubjectId().equals(subjectId)) {
//foundScores = true;
//        System.out.println("회차: " + score.getRound() + ", 점수: " + score.getScore() + ", 등급: " + score.getGrade());
//        }
//        }