package abhinav.hackdev.co.amortizedanalysis;


import android.util.Log;

import java.util.List;

public class CalculateCGPA {

    private static final String TAG = "TAG";
    public List<Float> arrayGPA ;
    public float finalCGPA = 0 ;

    public CalculateCGPA(List<Float> arrayGPA) {
        this.arrayGPA = arrayGPA;
    }

    public float getCGPA() {
        for(int i=0 ; i< arrayGPA.size() ; i++){
            finalCGPA = arrayGPA.get(i) + finalCGPA ;
            finalCGPA = finalCGPA/arrayGPA.size() ;
        }
        Log.d(TAG, "getCGPA: "+ finalCGPA);
        return finalCGPA ;
    }

    public void setArrayGPA(List<Float> arrayGPA) {
        this.arrayGPA = arrayGPA;
    }
}
