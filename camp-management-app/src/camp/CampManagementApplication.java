package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.*;

// dev
/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static final String INDEX_TYPE_SCORE = "SC";
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static int subjectIndex;
    private static int scoreIndex;
    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 학생 정보 조회
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                        // dddd
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    //연습

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }

            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");
        String studentName = getStudentName(); // 해석하면 학생 이름을 가져옴 -> getStudentName() 메서드를 호출하여 학생 이름을 가져옴
        if (studentName == null) return; // 학생 이름이 null 이면(비어있으면) return -> 즉, 학생 이름이 비어있으면 이전 화면으로 이동

        Set<Subject> selectedMandatorySubjects = getSubjects(SUBJECT_TYPE_MANDATORY, 3); // 필수 과목을 SUBJECT_TYPE_MANDATORY 의 minCount n개로가져옴
        if (selectedMandatorySubjects == null) return; // 필수 과목이 null 이면 return -> 즉, 필수 과목이 비어있으면 이전 화면으로 이동

        Set<Subject> selectedOptionalSubjects = getSubjects(SUBJECT_TYPE_CHOICE, 2); // 선택 과목을 SUBJECT_TYPE_CHOICE 의 minCount 로 n개로가져옴
        if (selectedOptionalSubjects == null) return; // 선택 과목이 null 이면 return -> 즉, 선택 과목이 비어있으면  이전 화면으로 이동

        // 과목 수 확인
        if (selectedMandatorySubjects.size() < 3 || selectedOptionalSubjects.size() < 2) { // 필수 과목이 3개 미만이거나 선택 과목이 2개 미만이면
            System.out.println("필수 과목은 최소 3개, 선택 과목은 최소 2개 이상 선택해야 합니다.\n메인 화면 이동..."); // 조건이 맞지 않으면 호출할 메세지
            return; // 이전 화면으로 이동
        }

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 등록 인스턴스 생성 -> sequence 메서드를 호출하여 INDEX_TYPE_STUDENT를 통해 학생 고유번호를 가져옴
        selectedMandatorySubjects.forEach(student::addSubject); // 필수 과목을 sutdent에 추가
        selectedOptionalSubjects.forEach(student::addSubject); // 선택 과목을 sutdent에 추가

        studentStore.add(student); // 수강생 스토어에 추가
        System.out.println("수강생 등록 성공!\n");
    }

    private static String getStudentName() {
        System.out.print("수강생 이름을 입력하세요...(취소: 0) ");
        String studentName = sc.next();
        if (studentName.equals("0")) {
            System.out.println("수강생 등록 취소!\n");
            return null;
        }
        return studentName;
    }

    private static Set<Subject> getSubjects(String type, int minCount) {
        Set<Subject> selectedSubjects = new HashSet<>();
        String displayType = type.equals(SUBJECT_TYPE_MANDATORY) ? "필수과목" : "선택과목"; // ?는 삼항연산자, type이 SUBJECT_TYPE_MANDATORY 이면 "필수과목" 아니면 "선택과목"을 displayType에 저장
        System.out.println("\n" + displayType + "을 선택하세요. (취소: 0)");
        subjectStore.stream()
                .filter(subject -> subject.getSubjectType().equals(type))
                .forEach(subject -> System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName()));

        while (true) {
            System.out.print("과목 번호를 입력하세요 (쉼표로 구분, 최소 " + minCount + "개): ");
            String subjectIds = sc.next();
            if (subjectIds.equals("0")) {
                System.out.println("수강생 등록 취소!\n");
                return null;
            }

            String[] subjectIdArray = subjectIds.split(",");
            if (subjectIdArray.length < minCount) {
                System.out.println(displayType + "은 최소 " + minCount + "개 이상 선택해야 합니다. 다시 시도하세요.");
                continue;
            }

            for (String subjectId : subjectIdArray) {
                subjectStore.stream()
                        .filter(subject -> subject.getSubjectId().equals(subjectId.trim()) && subject.getSubjectType().equals(type))
                        .findFirst()
                        .ifPresent(subject -> {
                            if (selectedSubjects.contains(subject)) {
                                System.out.println("이미 입력한 과목입니다: " + subject.getSubjectName());
                            } else {
                                selectedSubjects.add(subject);
                            }
                        });
            }

            if (selectedSubjects.size() >= minCount) {
                break;
            } else {
                System.out.println(displayType + "은 최소 " + minCount + "개 이상 선택해야 합니다. 다시 시도하세요.");
                selectedSubjects.clear();
            }
        }

        return selectedSubjects;
    }

    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("**********************************");
        System.out.println("수강생 목록을 조회합니다...");
        System.out.println("**********************************");

        if (studentStore.isEmpty()) { // studentStore가 비어있으면
            System.out.println("##### 등록된 수강생이 존재하지 않습니다. #####"); // 수강생이 존재하지 않는다고 출력
            return; // 메서드 종료시킴
        }

        for (Student student : studentStore) { // studentStore를 루프를 돌면서 student에 저장
            System.out.println("==================================");
            System.out.println("고유번호 : " + "[" + student.getStudentId() + "] " + "이름 : " + student.getStudentName());
            System.out.println("과목 목록 :");
            for (Subject subject : student.getSubjects()) { // 과목은 student의 getSubjects를 루프를 돌면서 subject에 저장 출력이 제대로 안되는 버그 방지를 위해 2중으로 루프를 돌면서 출력
                String subjectType = subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY) ? "필수과목" : "선택과목"; // subjectType이 SUBJECT_TYPE_MANDATORY 이면 필수과목 아니면 선택과목을 subjectType에 저장
                System.out.println("  [" + subject.getSubjectId() + "] " + subject.getSubjectName() + " (" + subjectType + ")");
            }
            System.out.println("==================================");
        }


        System.out.println("**********************************");
        System.out.println("수강생 목록 조회 성공!");
        System.out.println("**********************************");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 ID 입력
    private static String getStudentId() {
        System.out.println("==================================");
        if (!studentStore.isEmpty()) {
            for (Student student : studentStore) {
                System.out.println("[" + student.getStudentId() + "] " + student.getStudentName());
            }
        }
        System.out.print("\n관리할 수강생의 번호를 입력해주세요...");
        String studentId = sc.next();

        return studentId;
    }

    // 회차 입력
    private static int getScoreRound() {
        System.out.println("==================================");
        System.out.print("\n점수를 부여할 시험의 회차를 입력해주세요...");
        return sc.nextInt();
    }

    // 점수 등록
    private static String getSubjectId() {
        System.out.println("==================================");
//        if (!studentStore.isEmpty()) {
//            for (Student student : studentStore) {
//                System.out.println("[" + student.getStudentId() + "] " + student.getStudentName());
//            }
//        }
        System.out.print("\n관리할 과목의 번호를 입력해주세요...");
        return sc.next();
    }

    // 점수 입력
    private static int getScoreNum() {
        System.out.println("==================================");
        System.out.print("\n점수를 부여할 시험의 점수를 입력해주세요...");
        return sc.nextInt();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호 입력 받기
        String subjectId = getSubjectId(); // 관리할 과목 ID 입력 받기
        int scoreRound = getScoreRound(); // 시험 회차 입력 받기
        int scoreNum = getScoreNum(); // 점수 입력 받기
        Character scoreGrade = 0; // 등급 변수 초기화


        // 중복 확인
        // 수강생id, 과목id, 회차 같으면 중복
        boolean isDuplicate = false;    // 중복 체크 변수
        if (!scoreStore.isEmpty()) {    // scoreStore 가 초기에는 비어있으니 안 비어있으면 중복 검사
            for (Score score : scoreStore) {
                if (score.getScoreStudentId().equals(studentId) && score.getScoreSubjectId()
                        .equals(subjectId) && score.getScoreRound().equals(scoreRound)) {
                    System.out.println("이미 등록되어 있습니다.");
                    isDuplicate = true;
                    break;
                }
            }
        }

        // 필수과목, 선택과목 분류
        for(Subject subject : subjectStore) {
            if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals("MANDATORY")) {
                scoreGrade = mainTranslateGrade(scoreNum);
            } else if (subject.getSubjectId().equals(subjectId) && subject.getSubjectType().equals("CHOICE")) {
                scoreGrade = subTranslateGrade(scoreNum);
            }
        }

        // 입력받은 정보로 점수 객체 생성
        if (!isDuplicate) {
            Score score = new Score(studentId, subjectId, scoreRound, scoreNum, scoreGrade);
            scoreStore.add(score);
            // 저장 확인
            System.out.println("[수강생ID:" + score.getScoreStudentId() + "] [과목ID:" + score.getScoreSubjectId() +
                    "] [회차:" + score.getScoreRound() + "] [점수:" + score.getScoreNum() +
                    "] [등급:" + score.getScoreGrade() + "]");
            // 점수 등록 로직 구현 (저장소에 추가)
            System.out.println("시험 점수를 등록했습니다.");
        }





    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {

        String studentId = getStudentId();// 관리할 수강생 고유 번호

        char scoreGrade = '0';

        //학생 ID가 있는지 검사
        boolean studentIdValid = false;
        for (Score score : scoreStore) {
            if (score.getScoreStudentId().equals(studentId)) {
                studentIdValid = true;
                break;
            }
        }

        if (!studentIdValid) {
            System.out.println("등록된 학생이 아닙니다. 수강생 관리 페이지로 이동합니다.");
            displayStudentView();
            return;

        }
        String scoreSubjectId = getSubjectId();

        // 기능 구현 (수정할 과목 및 회차, 점수)

        System.out.println("시험 점수를 수정합니다...");

        // 점수 수정 로직

        System.out.println("수정할 과목의 회차를 입력 : ");
        Integer scoreRound = sc.nextInt();
        System.out.println("수정할 과목의 점수를 입력 : ");
        Integer score = sc.nextInt();


        boolean roundFound = false;
        boolean scoreUpdated = false;

        for (Score score2 : scoreStore) {
            if (score2.getScoreStudentId().equals(studentId) &&
                    score2.getScoreSubjectId().equals(scoreSubjectId) &&
                    score2.getScoreRound() == scoreRound) {

                // 회차가 일치하고 점수가 동일하다면 수정할 필요 없음
                if (score2.getScoreNum() == score) {
                    System.out.println("점수가 기존과 동일합니다. 수정할 필요가 없습니다.");
                    return; //점수를 수정할 필요가 없어서 함수 종료
                }
                // 고유번호들이 일치하고 회차가 일치하고 점수가 다르다면 점수 수정
                System.out.println("수정되기 전 점수 : " + score2.getScoreNum());
                score2.setScoreNum(score);
                for (Subject subject : subjectStore) {
                    if (subject.getSubjectId().equals(scoreSubjectId) && subject.getSubjectType().equals("MANDATORY")) {

                        scoreGrade = mainTranslateGrade(score);
                    } else if (subject.getSubjectId().equals(scoreSubjectId) && subject.getSubjectType().equals("CHOICE")) {

                        scoreGrade = subTranslateGrade(score);
                    }
                }
                score2.setScoreGrade(scoreGrade);
                scoreUpdated = true;
                roundFound = true;
                System.out.println("수정 된 점수 : " + score2.getScoreNum());
                System.out.println("수정 된 등급 : " + score2.getScoreGrade());
                break;
            }
        }

        //회차별 점수가 등록되지 않았을 경우
        if (!roundFound) {
            System.out.println("등록된 회차가 없습니다. 점수 관리 페이지로 이동합니다.");
            displayScoreView();
        } else if (scoreUpdated) {
            System.out.println("점수 수정 성공!");
        } else {
            System.out.println("점수 수정 실패 해당 회차에 점수가 존재하지 않습니다. 점수 관리 페이지로 이동합니다.");
            displayScoreView();
        }


    }// 기능 구현

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {

        Scanner sc = new Scanner(System.in);

        // 학생 id
        String studentId = getStudentId();
        // 학생 이름
        String studentName =getStudentName();

        // 과목 id
        System.out.print("조회할 과목 ID를 입력하시오: ");
        String subjectId = sc.nextLine();

        // 기능 구현 (조회할 특정 과목)


        boolean foundScores=false;


        Integer scoreRound = 1;

        System.out.println("회차별 등급을 조회합니다...");

        //
        // 학생 ID -> 출력 완료
        // 과목 ID -> 출력 완료
        // 회차 -> 출력 완료 1
        // 등급 ->  A

        for (Score needScore : scoreStore) {

            if (needScore.getScoreStudentId().equals(studentId) && needScore.getScoreSubjectId().equals(subjectId) ) {
                foundScores= true;
                System.out.println("고유번호: [" + studentId + "] ,"+"학생 이름 : "+studentName);
                System.out.println("회차: " + needScore.getScoreRound() + ", 등급: " + needScore.getScoreGrade());
            }
        }

        if (!foundScores) {
            System.out.println("해당 과목에 대한 점수가 없습니다.");
        } else {
            System.out.println("\n등급 조회 성공!");
        }
    }
    public static char mainTranslateGrade(int score) {
        char scoreGrade;
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
        } else {
            throw new RuntimeException();
        }
        return scoreGrade;
    }

    public static char subTranslateGrade(int score) {
        char scoreGrade;
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
        } else {
            throw new RuntimeException();
        }
        return scoreGrade;
    }
}
