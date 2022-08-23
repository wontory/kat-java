package KAT;

import java.util.ArrayList;

class Combination {
    static final String[] week = {"월","화","수","목","금"};
    static Division[] combinationBuffer;
    static ArrayList<Division[]> combination;

    void generateTable(int lectureNum) {
        combinationBuffer = new Division[lectureNum];
        combination = new ArrayList<>();
    }

    void lectureCombination(Lecture[] lectureArchive, int depth, int lectureNum, String freeDay) {
        if (depth == lectureNum) {
            combination.add(combinationBuffer.clone());
            return;
        }

        for (int i = 0; i < lectureArchive[depth].getDivisionNum(); i++) {
            boolean isReserved = false;
            if (week[lectureArchive[depth].division[i].getDay()].equals(freeDay)) {
                isReserved = true;
            }
            else {
                for (int j = 0; j < depth; j++) {
                    if ((lectureArchive[depth].division[i].getTime().contains(combinationBuffer[j].getTime().charAt(0)+"") || combinationBuffer[j].getTime().contains(lectureArchive[depth].division[i].getTime().charAt(0)+"")) && lectureArchive[depth].division[i].getDay() == (combinationBuffer[j].getDay())) {
                        isReserved = true;
                        break;
                    }
                }
            }

            if (!isReserved) {
                combinationBuffer[depth] = lectureArchive[depth].division[i];
                lectureCombination(lectureArchive, depth + 1, lectureNum, freeDay);
            }
        }
    }

    void printCombination(Lecture[] lectureArchive) {
        for (int i = 0; i < combination.size(); i++) {
            System.out.println("시간표" + (i + 1));
            for (int j = 0; j < combination.get(i).length; j++) {
                System.out.println("  강의" + (j + 1));
                System.out.println("  강의명: " + lectureArchive[j].getName() + "\t교수: " + combination.get(i)[j].getProfessor() + "\t요일: " + week[combination.get(i)[j].getDay()] + "\t시간: " + combination.get(i)[j].getTime());
            }
            System.out.println("=======================================================");
        }
    }
}