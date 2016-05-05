package abhinav.hackdev.co.amortizedanalysis;

/**
 * Created by abhinav on 05/05/16.
 */
public class CalculateCGPA {

    public float[] arrayGPA ;
    public float finalCGPA = 0 ;

    public CalculateCGPA(float[] arrayGPA) {
        this.arrayGPA = arrayGPA;
    }

    public float getCGPA() {
        for(int i=0 ; i< arrayGPA.length ; i++){
            finalCGPA = arrayGPA[i] + finalCGPA ;
            finalCGPA = finalCGPA/arrayGPA.length ;
        }
        return finalCGPA ;
    }

    public void setArrayGPA(float[] arrayGPA) {
        this.arrayGPA = arrayGPA;
    }
}
