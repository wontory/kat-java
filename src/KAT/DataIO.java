package KAT;

import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

import static KAT.Combination.combinations;
import static KAT.Combination.week;

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
        System.out.print(combinations.size() + "개의 시간표가 생성되었습니다. 보기 옵션을 선택해주세요.\n" +
                "1. 하나씩 보기    2. 한번에 모두 보기    3. txt 파일로 저장    4. 프로그램 종료\n입력: ");
        int userInput = scanner.nextInt();
        if (userInput == 4) {
            System.out.print("프로그램을 종료합니다.");
            System.exit(0);
        } else if (userInput < 1 || userInput > 4) {
            while (true) {
                System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.\n입력: ");
                userInput = scanner.nextInt();
                if (userInput == 4) {
                    System.exit(0);
                } else if (userInput >= 1 && userInput < 4) {
                    break;
                }
            }
        }

        return userInput;
    }

    public static void printResult(int userInput, Lecture[] lectures, Combination combination, TimeTable[] timeTables) throws IOException {
        System.out.println("\n=================================================================\n");
        for (int i = 0; i < combinations.size(); i++) {
            System.out.print("  시간표 " + (i + 1));
            if (userInput == 1) {
                System.out.print(" / " + combinations.size());
            }
            System.out.println("\n");
            timeTables[i].printTable();
            combination.print(lectures, i);
            if (userInput == 1) {
                System.out.print("\n계속하려면 Enter 키를 누르십시오 ...");
                int waitEnter = System.in.read();
            }
            System.out.println("\n=================================================================\n");
        }
    }

    public static void createFile(Lecture[] lectures, TimeTable[] timeTables) throws IOException {
        FileWriter fw = new FileWriter("table.txt");
        fw.write("\n=================================================================\n\n");
        for (int i = 0; i < combinations.size(); i++) {
            fw.write("  시간표 " + (i + 1) + "\n\n");

            fw.write("\t\t월\t화\t수\t목\t금\n");
            for (int j = 0; j < 8; j++) {
                fw.write("\t" + (j + 1));
                for (int k = 0; k < 5; k++) {
                    fw.write("\t" + timeTables[i].timeTable[k][j]);
                }
                fw.write("\n");
            }

            fw.write("\n");

            for (int j = 0; j < combinations.get(i).length; j++) {
                fw.write("\t강의" + (j + 1) + "\n");
                fw.write("\t" + lectures[j].getName() + "  |  " +
                        combinations.get(i)[j].getProfessor() + "  |  " +
                        week[combinations.get(i)[j].getDay()] + " " + combinations.get(i)[j].getTime() + "\n");
            }
            fw.write("\n=================================================================\n\n");
        }
        fw.close();
    }
}