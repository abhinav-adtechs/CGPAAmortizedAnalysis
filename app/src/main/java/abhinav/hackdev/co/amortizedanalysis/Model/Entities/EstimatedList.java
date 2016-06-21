package abhinav.hackdev.co.amortizedanalysis.Model.Entities;


public class EstimatedList {

    private int estCreds ;
    private float estGPA ;

    public EstimatedList() {
    }

    public EstimatedList(int estCreds, float estGPA) {
        this.estCreds = estCreds;
        this.estGPA = estGPA;
    }

    public int getEstCreds() {
        return estCreds;
    }

    public void setEstCreds(int estCreds) {
        this.estCreds = estCreds;
    }

    public float getEstGPA() {
        return estGPA;
    }

    public void setEstGPA(float estGPA) {
        this.estGPA = estGPA;
    }
}
