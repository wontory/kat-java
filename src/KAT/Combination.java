package KAT;

import java.util.ArrayList;

class Combination {
    static final String[] week = {"월","화","수","목","금"};
    static Division[] combinationBuffer;
    static ArrayList<Division[]> combinations;

    Combination (int lectureNum) {
        combinationBuffer = new Division[lectureNum];
        combinations = new ArrayList<>();
    }

    void lectureCombination(Lecture[] lectureArchive, int depth, int lectureNum, String freeDay) {
        if (depth == lectureNum) {
            combinations.add(combinationBuffer.clone());
            return;
        }

        for (int i = 0; i < lectureArchive[depth].getDivisionNum(); i++) {
            boolean isReserved = false;
            if (week[lectureArchive[depth].divisions[i].getDay()].equals(freeDay)) {
                isReserved = true;
            }
            else {
                for (int j = 0; j < depth; j++) {
                    if ((lectureArchive[depth].divisions[i].getTime().contains(combinationBuffer[j].getTime().charAt(0)+"") || combinationBuffer[j].getTime().contains(lectureArchive[depth].divisions[i].getTime().charAt(0)+"")) && lectureArchive[depth].divisions[i].getDay() == (combinationBuffer[j].getDay())) {
                        isReserved = true;
                        break;
                    }
                }
            }

            if (!isReserved) {
                combinationBuffer[depth] = lectureArchive[depth].divisions[i];
                lectureCombination(lectureArchive, depth + 1, lectureNum, freeDay);
            }
        }
    }

    void printCombination(Lecture[] lectureArchive) {
        for (int i = 0; i < combinations.size(); i++) {
            System.out.println("시간표" + (i + 1) + "\n");
            TimeTable timeTable = new TimeTable();
            for (int j = 0; j < combinations.get(i).length; j++) {
                timeTable.add(combinations.get(i)[j], lectureArchive[j]);
            }
            timeTable.printTable();
            for (int j = 0; j < combinations.get(i).length; j++) {
                System.out.println("  강의" + (j + 1));
                System.out.println("  강의명: " + lectureArchive[j].getName() + "\t교수: " + combinations.get(i)[j].getProfessor() + "\t요일: " + week[combinations.get(i)[j].getDay()] + "\t시간: " + combinations.get(i)[j].getTime());
            }
            System.out.println("\n=======================================================\n");
        }
    }
}