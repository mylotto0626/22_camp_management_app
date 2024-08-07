package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;

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

        Set<Subject> selectedMandatorySubjects = getSubjects(SUBJECT_TYPE_MANDATORY, 3); // 필수 과목을 SUBJECT_TYPE_MANDATORY 의 minCount n개로가져옴 + set<Subject>로 반환>> = 중복X > 똑같은 과목이 여러 번 추가되는 걸 방지
        if (selectedMandatorySubjects == null) return; // 필수 과목이 null 이면 return -> 즉, 필수 과목이 비어있으면 이전 화면으로 이동

        Set<Subject> selectedChoiceSubjects = getSubjects(SUBJECT_TYPE_CHOICE, 2); // 선택 과목을 SUBJECT_TYPE_CHOICE 의 minCount 로 n개로가져옴
        if (selectedChoiceSubjects == null) return; // 선택 과목이 null 이면 return -> 즉, 선택 과목이 비어있으면  이전 화면으로 이동

        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 등록 인스턴스 생성 -> sequence 메서드를 호출하여 INDEX_TYPE_STUDENT를 통해 학생 고유번호를 가져옴
        selectedMandatorySubjects.forEach(student :: addSubject); // 필수 과목을 sutdent에 추가
        selectedChoiceSubjects.forEach(student :: addSubject); // 선택 과목을 sutdent에 추가

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
        Set<Subject> selectedSubjects = new LinkedHashSet<>(); // 중복을 허용하지 않는 Set 인터페이스를 구현한 LinkedHashSet 인스턴스 생성 -> 선택한 과목을 저장할 selectedSubjects + LinkedHashSet를 이용해 저장 순서를 유지하면서 중복방지
        String displayType = type.equals(SUBJECT_TYPE_MANDATORY) ? "필수과목" : "선택과목"; // type이 SUBJECT_TYPE_MANDATORY 이면 "필수과목" 아니면 "선택과목"을 displayType에 저장, equals 메서드를 사용해서 스트링만으로도
        subjectStore.stream() // subjectStore를 stream으로 변환
                .filter(subject -> subject.getSubjectType().equals(type)) // subject의 subjectType이 type과 같은 것만 찾음 type은 SUBJECT_TYPE_MANDATORY 또는 SUBJECT_TYPE_CHOICE,
                .forEach(subject -> System.out.println("[" + subject.getSubjectId() + "] " + subject.getSubjectName())); // 찾은 subject의 subjectId와 subjectName를 forEach를 사용하여 루프를 돌면서 출력

        while (true) {
            System.out.print("과목 번호를 입력하세요 (쉼표로 구분, 최소 " + minCount + "개): ");
            String subjectIds = sc.next();
            if (subjectIds.equals("0")) {
                System.out.println("수강생 등록 취소!\n");
                return null;
            }

            String[] subjectIdArray = subjectIds.split(","); // subjectIds를 쉼표로 구분하여 배열로 저장
            if (subjectIdArray.length < minCount) { // subjectIdArray의 길이가 minCount보다 작으면
                System.out.println(displayType + "은 최소 " + minCount + "개 이상 선택해야 합니다. 다시 시도하세요.");
                continue;
            }

            for (String subjectId : subjectIdArray) {
                subjectStore.stream() // subjectStore를 stream으로 변환
                        .filter(subject -> subject.getSubjectId().equals(subjectId.trim()) && subject.getSubjectType().equals(type)) // subject의 subjectId가 subjectId와 같고 subjectType이 type과 같은 것만 찾음
                        .findFirst() // 찾은 것 중 첫번째 것만 찾음 하나만 찾아도 되는 이유는 subjectId는 중복이 없기 때문 -> 중복이 있으면 findAny()를 사용 -> Optional로 반환 -> ifPresent를 사용하여 값이 있으면 실행
                        .ifPresent(subject -> { // 값이 있으면 실행
                            if (selectedSubjects.contains(subject)) { // selectedSubjects에 subject가 포함되어 있으면 -> 중복을 허용하지 않기 위해 contains 메서드를 사용 -> 중복이 있으면 출력
                                System.out.println("이미 입력한 과목입니다: " + subject.getSubjectName());
                            } else {
                                selectedSubjects.add(subject); // selectedSubjects에 subject를 추가 else문에서 중복이 없으면 추가 = 중복을 허용하지 않기 위해 add 메서드를 사용 >> add 메서드를 사용하면 중복을 허용하지 않음
                            }
                        });
            }

            if (selectedSubjects.size() >= minCount) { // selectedSubjects의 크기가 minCount보다 크거나 같으면
                break; // 반복문 탈출
            } else {
                System.out.println(displayType + "은 최소 " + minCount + "개 이상 선택해야 합니다. 다시 시도하세요.");
                selectedSubjects.clear(); // selectedSubjects를 비움 -> 왜??? >>> 다시 선택할 수 있도록 초기화시키려고 초기화 안하면 계속 더해져서 minCount(최소 선택 수)를 벗어날 수 있음
            }

        }
        return selectedSubjects; // 선택한 과목을 반환해쥼
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

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        // 기능 구현
        System.out.println("\n점수 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        System.out.println("\n점수 수정 성공!");
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

}
