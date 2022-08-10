import java.util.Scanner;

class Lecture {
    String name;
    int credit;
    int divisionNum;
    Division[] division;

    public void setName(String name) { this.name = name; }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setDivisionNum(int divisionNum) {
        this.divisionNum = divisionNum;
    }

    public String getName() {
        return this.name;
    }

    public int getCredit() {
        return this.credit;
    }

    public int getDivisionNum() {
        return this.divisionNum;
    }
}

class Division {
    String professor;
    String day;
    String time;

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfessor() {
        return this.professor;
    }

    public String getDay() {
        return this.day;
    }

    public String getTime() {
        return this.time;
    }
}

class DataIO {
    static Scanner scanner = new Scanner(System.in);
    public static int setLectureNum() {
        System.out.print("과목 수: ");
        return scanner.nextInt();
    }
    public static Lecture setLectureData(int num) {
        Lecture lecture = new Lecture();

        System.out.println("과목" + (num + 1));
        System.out.print("과목명: ");
        lecture.setName(scanner.next());
        System.out.print("학점: ");
        lecture.setCredit(scanner.nextInt());
        System.out.print("분반 수: ");
        lecture.setDivisionNum(scanner.nextInt());

        lecture.division = new Division[lecture.divisionNum];

        for (int i = 0; i < lecture.divisionNum; i++) {
            lecture.division[i] = setDivisionData(i);
        }

        return lecture;
    }

    public static Division setDivisionData(int num) {
        Division division = new Division();

        System.out.println("분반" + (num + 1));
        System.out.print("교수: ");
        division.setProfessor(scanner.next());
        System.out.print("요일: ");
        division.setDay(scanner.next());
        System.out.print("시간: ");
        division.setTime(scanner.next());

        return division;
    }

    public static void printLectureInfo(Lecture[] archive) {
        for (int i = 0; i < archive.length; i++) {
            System.out.println("과목" + (i + 1));
            System.out.println("과목명: " + archive[i].getName());
            System.out.println("학점: " + archive[i].getCredit());
            System.out.println("분반 수: " + archive[i].getDivisionNum());
            for (int j = 0; j < archive[i].division.length; j++) {
                System.out.println();
                System.out.println("  분반" + (j + 1));
                System.out.println("  교수: " + archive[i].division[j].getProfessor());
                System.out.println("  요일: " + archive[i].division[j].getDay());
                System.out.println("  시간: " + archive[i].division[j].getTime());
            }
            System.out.println();
        }
    }

    public static String setFreeDay() {
        System.out.print("공강 요일: ");
        return scanner.next();
    }
}

class TimeTable {
    static Division[] tableBuffer;
    int timeTableIndex = 0;
    static Division[][] timeTable;

    void generateTable(int lectureNum) {
        tableBuffer = new Division[lectureNum];
        timeTable = new Division[1000][];
    }

    void lectureCombination(Lecture[] lectureArchive, int depth, int lectureNum, String freeDay) {
        if (depth == lectureNum) {
            timeTable[timeTableIndex++] = tableBuffer.clone();
            return;
        }

        for (int i = 0; i < lectureArchive[depth].divisionNum; i++) {
            boolean isReserved = false;
            for (int j = 0; j < depth; j++) {
                if (((lectureArchive[depth].division[i].time.equals(tableBuffer[j].time)) && (lectureArchive[depth].division[i].day.equals(tableBuffer[j].day))) || (lectureArchive[depth].division[i].day.equals(freeDay))) {
                    isReserved = true;
                    break;
                }
            }

            if (!isReserved) {
                tableBuffer[depth] = lectureArchive[depth].division[i];
                lectureCombination(lectureArchive, depth + 1, lectureNum, freeDay);
            }
        }
    }

    void printTable(Lecture[] lectureArchive) {
        for (int i = 0; i < timeTableIndex; i++) {
            System.out.println("시간표" + (i + 1));
            for (int j = 0; j < timeTable[i].length; j++) {
                System.out.println("  강의" + (j + 1));
                System.out.println("  강의명: " + lectureArchive[j].getName() + "\t교수: " + timeTable[i][j].getProfessor() + "\t요일: " + timeTable[i][j].getDay() + "\t시간: " + timeTable[i][j].getTime());
            }
            System.out.println("=======================================================");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int lectureNum = DataIO.setLectureNum();
        Lecture[] lectureArchive = new Lecture[lectureNum];

        TimeTable table = new TimeTable();
        String freeDay = DataIO.setFreeDay();

        for (int i = 0; i < lectureNum; i++) {
            lectureArchive[i] = DataIO.setLectureData(i);
        }

        //System.out.println("====================\n        결과\n====================");
        //DataIO.printLectureInfo(lectureArchive);

        table.generateTable(lectureNum);
        table.lectureCombination(lectureArchive, 0, lectureNum, freeDay);

        table.printTable(lectureArchive);
    }
}