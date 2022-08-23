package KAT;

public class TimeTable {
    protected int creditSum;
    protected char[][] timeTable = {
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '-'}
    };

    protected void add(Division division, Lecture lectureArchive) {
        for(int i = (int)division.getTime().charAt(0) - 48; i < (int)division.getTime().charAt(division.getTime().length() - 1) - 47; i++) {
            this.timeTable[division.getDay()][i - 1] = lectureArchive.getName().charAt(0);
        }
        this.creditSum += lectureArchive.getCredit();
    }

    void printTable() {
        System.out.println("\t\t월\t화\t수\t목\t금");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + (i + 1));
            for (int j = 0; j < 5; j++) {
                System.out.print("\t" + timeTable[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }
}