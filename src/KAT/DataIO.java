package KAT;

import java.io.IOException;
import java.util.Scanner;

class DataIO {
    static Scanner scanner = new Scanner(System.in);

    public static int setLectureNum() {
        System.out.print("과목 수: ");
        return scanner.nextInt();
    }

    public static Lecture setLectureData(int num) {
        Lecture lecture = new Lecture();

        System.out.println("과목 " + (num + 1));
        System.out.print("과목명: ");
        lecture.setName(scanner.next());
        System.out.print("학점: ");
        lecture.setCredit(scanner.nextInt());
        System.out.print("분반 수: ");
        lecture.setDivisionNum(scanner.nextInt());

        lecture.divisions = new Division[lecture.getDivisionNum()];

        for (int i = 0; i < lecture.getDivisionNum(); i++) {
            lecture.divisions[i] = setDivisionData(i, lecture.getCredit());
        }

        return lecture;
    }

    public static Division setDivisionData(int num, int credit) {
        Division division = new Division();

        System.out.println("분반 " + (num + 1));
        System.out.print("교수: ");
        division.setProfessor(scanner.next());
        System.out.print("요일: ");
        division.setDay(scanner.next());
        System.out.print("시간: ");
        division.setTime(scanner.next(), credit);

        return division;
    }

    public static String setFreeDay() {
        System.out.print("공강인 요일을 선택하시겠습니까? Y/N ");
        String answer = scanner.next();
        if (answer.charAt(0) == 'Y' || answer.charAt(0) == 'y') {
            System.out.print("공강 요일: ");
            return scanner.next();
        } else if (answer.charAt(0) == 'N' || answer.charAt(0) == 'n') {
            return null;
        } else {
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            return setFreeDay();
        }
    }

    public static int chooseResultOption() {
        System.out.print(Combination.combinations.size() + "개의 시간표가 생성되었습니다. 보기 옵션을 선택해주세요.\n" +
                "1. 하나씩 보기    2. 한번에 모두 보기    3. 프로그램 종료\n입력: ");
        int userInput = scanner.nextInt();
        if (userInput == 3) {
            System.out.print("프로그램을 종료합니다.");
            System.exit(0);
        } else if (userInput < 1 || userInput > 3) {
            while (true) {
                System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.\n입력: ");
                userInput = scanner.nextInt();
                if (userInput == 3) {
                    System.exit(0);
                } else if (userInput >= 1 && userInput < 3) {
                    break;
                }
            }
        }

        return userInput;
    }

    public static void printResult(int userInput, Lecture[] lectures, Combination combination, TimeTable[] timeTables) throws IOException {
        System.out.println("\n=================================================================\n");
        for (int i = 0; i < Combination.combinations.size(); i++) {
            System.out.print("  시간표 " + (i + 1));
            if (userInput == 1) {
                System.out.print(" / " + Combination.combinations.size());
            }
            System.out.println("\n");
            timeTables[i].printTable();
            combination.printCombination(lectures, i);
            if (userInput == 1) {
                System.out.print("\n계속하려면 Enter 키를 누르십시오 ...");
                int waitEnter = System.in.read();
            }
            System.out.println("\n=================================================================\n");
        }
    }
}