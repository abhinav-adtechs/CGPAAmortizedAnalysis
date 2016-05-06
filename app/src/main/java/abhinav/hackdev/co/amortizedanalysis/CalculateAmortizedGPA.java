package abhinav.hackdev.co.amortizedanalysis;


import android.util.Log;

import java.util.List;

public class CalculateAmortizedGPA {

    private static final String TAG = "TAG";

    public float returnRequiredGPA(float currentCGPA, int semCount){
        return (9*semCount) - currentCGPA ;
    }

    public float getAmortizedGPA(List<Float> gpaList){
        Log.d(TAG, "getAmortizedGPA: " + returnRequiredGPA(new CalculateCGPA(gpaList).getCGPA(), gpaList.size()));
        return returnRequiredGPA(new CalculateCGPA(gpaList).getCGPA(), gpaList.size()) ;
    }

}
