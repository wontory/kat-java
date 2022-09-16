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

    void lectureCombination(Lecture[] lectures, int depth, int lectureNum, String freeDay) {
        if (depth == lectureNum) {
            combinations.add(combinationBuffer.clone());
            return;
        }

        for (int i = 0; i < lectures[depth].getDivisionNum(); i++) {
            boolean isReserved = false;
            if (week[lectures[depth].divisions[i].getDay()].equals(freeDay)) {
                isReserved = true;
            } else {
                for (int j = 0; j < depth; j++) {
                    if ((lectures[depth].divisions[i].getTime().contains(combinationBuffer[j].getTime().charAt(0)+"") ||
                            combinationBuffer[j].getTime().contains(lectures[depth].divisions[i].getTime().charAt(0)+"")) &&
                            lectures[depth].divisions[i].getDay() == (combinationBuffer[j].getDay())) {
                        isReserved = true;
                        break;
                    }
                }
            }

            if (!isReserved) {
                combinationBuffer[depth] = lectures[depth].divisions[i];
                lectureCombination(lectures, depth + 1, lectureNum, freeDay);
            }
        }
    }

    TimeTable[] convertToTable(Lecture[] lectures) {
        TimeTable[] timeTables = new TimeTable[combinations.size()];

        for (int i = 0; i < combinations.size(); i++) {
            TimeTable timeTable = new TimeTable();
            for (int j = 0; j < combinations.get(i).length; j++) {
                timeTable.add(combinations.get(i)[j], lectures[j]);
            }
            timeTables[i] = timeTable;
        }

        return timeTables;
    }

    void print(Lecture[] lectures, int i) {
        for (int j = 0; j < combinations.get(i).length; j++) {
            System.out.println("\t강의" + (j + 1));
            System.out.println("\t" + lectures[j].getName() + "  |  " +
                    combinations.get(i)[j].getProfessor() + "  |  " +
                    week[combinations.get(i)[j].getDay()] + " " + combinations.get(i)[j].getTime());
        }
    }
}