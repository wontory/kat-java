package KAT;

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
}