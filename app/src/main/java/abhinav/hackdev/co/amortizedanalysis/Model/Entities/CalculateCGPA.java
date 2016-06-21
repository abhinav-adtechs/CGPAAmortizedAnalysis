package abhinav.hackdev.co.amortizedanalysis.Model.Entities;


import android.util.Log;

import java.util.ArrayList;

public class CalculateCGPA {

    private static final String TAG = "TAG";
    public ArrayList<GPAData> gpaDataArrayList ;
    public float finalCGPA = 0 ;
    public int totalCredits = 0 ;

    public CalculateCGPA(ArrayList<GPAData> gpaDataArrayList) {
        this.gpaDataArrayList = gpaDataArrayList ;
    }

    public float getCGPA() {
        for (int i = 0; i < gpaDataArrayList.size(); i++) {
            finalCGPA = finalCGPA + gpaDataArrayList.get(i).getSemGPA()*gpaDataArrayList.get(i).getSemCreds() ;
            totalCredits = totalCredits + gpaDataArrayList.get(i).getSemCreds() ;

        }
        finalCGPA = finalCGPA / totalCredits ;

        Log.d(TAG, "getCGPA: "+ finalCGPA);
        return finalCGPA ;
    }
}
