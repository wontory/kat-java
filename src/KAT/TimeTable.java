package KAT;

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
            if (lectureArchive[depth].division[i].day.equals(freeDay)) {
                isReserved = true;
            }
            else {
                for (int j = 0; j < depth; j++) {
                    if ((lectureArchive[depth].division[i].time.contains(tableBuffer[j].time.charAt(0)+"") || tableBuffer[j].time.contains(lectureArchive[depth].division[i].time.charAt(0)+"")) && lectureArchive[depth].division[i].day.equals(tableBuffer[j].day)) {
                        isReserved = true;
                        break;
                    }
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