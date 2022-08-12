package KAT;

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
        //KAT.DataIO.printLectureInfo(lectureArchive);

        table.generateTable(lectureNum);
        table.lectureCombination(lectureArchive, 0, lectureNum, freeDay);

        table.printTable(lectureArchive);
    }
}