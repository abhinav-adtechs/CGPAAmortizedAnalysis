package abhinav.hackdev.co.amortizedanalysis;

public class GPAData {

    private float semGPA ;
    private int semCreds ;
    private int semVal ;

    public GPAData(float semGPA, int semCreds, int semVal) {
        this.semGPA = semGPA;
        this.semCreds = semCreds;
        this.semVal = semVal;
    }

    public float getSemGPA() {
        return semGPA;
    }

    public void setSemGPA(float semGPA) {
        this.semGPA = semGPA;
    }

    public int getSemCreds() {
        return semCreds;
    }

    public void setSemCreds(int semCreds) {
        this.semCreds = semCreds;
    }

    public int getSemVal() {
        return semVal;
    }

    public void setSemVal(int semVal) {
        this.semVal = semVal;
    }

}
