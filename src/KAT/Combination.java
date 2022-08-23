package KAT;

import java.util.ArrayList;
import java.util.Arrays;

class Combination {
    static final String[] week = {"월","화","수","목","금"};
    static Timetable combinationBuffer = new Timetable();
    static ArrayList<Timetable> combination = new ArrayList<Timetable>();


    void lectureCombination(Lecture[] lectureArchive, int depth, int lectureNum, String freeDay) throws CloneNotSupportedException {
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
                    if (!combinationBuffer.canRegistration(lectureArchive[depth].division[i])) {
                        isReserved = true;
                        break;
                    }
                }
            }

            if (!isReserved) {
                combinationBuffer.add(lectureArchive[depth].division[i]);
                lectureCombination(lectureArchive, depth + 1, lectureNum, freeDay);
            }
        }
    }

    void printCombination() {
        int j = 0;
        for (Timetable t: combination) {
            System.out.println("시간표" + (j++));
            System.out.println(Arrays.deepToString(t.schedule));
            for (int i = 0; i < t.division.size(); i++) {
                System.out.println("  강의" + (i + 1));
                System.out.println("\t교수: " + t.division.get(i).getProfessor());
                System.out.println("\t요일: " + t.division.get(i).getDay());
                System.out.println("\t시간: " + t.division.get(i).getTime());
            }
            System.out.println("=======================================================");
        }

    }
}