package abhinav.hackdev.co.amortizedanalysis;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GPAList {


    private static final String TAG = "TAG";

    private static List<Float> gpaList = new ArrayList<>();

    public GPAList() {
        Log.d(TAG, "GPAList: ");

    }

    public static List<Float> getGpaList() {
        return gpaList;
    }

    public static GPAList newInstance(){
        GPAList gpaList = new GPAList() ;
        return gpaList ;
    }


    public static void addToList(float gpaData, int position){
        gpaList.add(position-1, gpaData) ;
    }
}
