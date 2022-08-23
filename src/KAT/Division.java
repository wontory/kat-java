package KAT;

import java.util.Scanner;

class Division {
    static Scanner scanner = new Scanner(System.in);

    private String professor;
    private int day;
    private String time;

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setDay(String day) {
        switch(day) {
            case "월": this.day = 0; break;
            case "화": this.day = 1; break;
            case "수": this.day = 2; break;
            case "목": this.day = 3; break;
            case "금": this.day = 4; break;
            default:
                System.out.println("요일을 잘못 입력하셨습니다. 다시 입력해주세요. (월, 화, 수, 목, 금)");
                this.setDay(scanner.next());
                break;
        }
    }

    public void setTime(String time, int credit) {
        if (time.length() == credit)
            this.time = time;
        else {
            System.out.println("시간을 잘못 입력하셨습니다. 다시 입력해주세요. (" + credit + "학점)");
            this.setTime(scanner.next(), credit);
        }
    }

    public String getProfessor() {
        return this.professor;
    }

    public int getDay() {
        return this.day;
    }

    public String getTime() {
        return this.time;
    }
}