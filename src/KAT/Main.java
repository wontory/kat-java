package KAT;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print(Combination.combinations.size() + "개의 시간표가 생성되었습니다. 보기 옵션을 선택해주세요.\n1. 하나씩 보기    2. 한번에 모두 보기    3. 프로그램 종료\n입력: ");
        int userInput = scanner.nextInt();
        if (userInput == 3) {
            System.out.print("프로그램을 종료합니다.");
            System.exit(0);
        }
        else if (userInput < 1 || userInput > 3) {
            while (true) {
                System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.\n입력: ");
                userInput = scanner.nextInt();
                if (userInput == 3)
                    System.exit(0);
                else if (userInput >= 1 && userInput < 3)
                    break;
            }
        }

        System.out.println("\n=================================================================\n");
        for (int i = 0; i < Combination.combinations.size(); i++) {
            if (userInput == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            System.out.print("  시간표 " + (i + 1));
            if (userInput == 1) {
                System.out.print(" / " + Combination.combinations.size());
            }
            System.out.println("\n");
            timeTables[i].printTable();
            combination.printCombination(lectures, i);
            if (userInput == 1) {
                System.out.print("\n계속하려면 Enter 키를 누르십시오 ...");
                try {
                    System.in.read();
                } catch (IOException e) { }
            }
            System.out.println("\n=================================================================\n");
        }

        System.out.println("시간표 출력이 완료되었습니다. 프로그램을 종료합니다.");
        System.exit(0);
    }
}