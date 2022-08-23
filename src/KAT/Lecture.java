package KAT;

class Lecture {
    private String name;
    private int credit;
    private int divisionNum;
    protected Division[] division;

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