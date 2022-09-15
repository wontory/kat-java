package KAT;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 수강 예정인 강의 수 입력
        int lectureNum = DataIO.setLectureNum();
        Lecture[] lectures = new Lecture[lectureNum];

        //강의 및 분반 정보 입력
        for (int i = 0; i < lectureNum; i++) {
            lectures[i] = DataIO.setLectureData(i);
        }

        // 공강 요일 입력
        String freeDay = DataIO.setFreeDay();

        // 시간표 메모리 생성
        Combination combination = new Combination(lectureNum);
        TimeTable[] timeTables;

        // 시간표 조합
        combination.lectureCombination(lectures, 0, lectureNum, freeDay);

        // 시간표 조합 테이블 변환
        timeTables = combination.convertToTable(lectures);

        // 시간표 결과 출력
        int userInput = DataIO.chooseResultOption();
        if (userInput == 3) {
            DataIO.createFile(lectures, timeTables);
            System.out.println("""

                    =================================================================

                    txt 파일 생성이 완료되었습니다. 프로그램을 종료합니다.""");
        } else {
            DataIO.printResult(userInput, lectures, combination, timeTables);
            System.out.println("시간표 출력이 완료되었습니다. 프로그램을 종료합니다.");
        }

        // 프로그램 종료
        System.exit(0);
    }
}