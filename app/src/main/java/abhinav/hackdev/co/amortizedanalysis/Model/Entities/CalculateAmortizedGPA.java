package abhinav.hackdev.co.amortizedanalysis.Model.Entities;



import java.util.ArrayList;

public class CalculateAmortizedGPA {

    private static final String TAG = "TAG";

    private ArrayList<GPAData> gpaDataArrayList ;
    private float currentCGPA ;

    public CalculateAmortizedGPA(ArrayList<GPAData> gpaDataArrayList) {
        this.gpaDataArrayList = gpaDataArrayList;
        currentCGPA = new CalculateCGPA(gpaDataArrayList).getCGPA() ;
    }

    private int getCurrentTotalCreds(){
        int sum = 0 ;

        for (int i = 0; i < gpaDataArrayList.size(); i++) {
            sum = sum + gpaDataArrayList.get(i).getSemCreds() ;
        }
        return sum ;
    }

    public float getMinPredictedGPA(int nextCreds){
        float estimatedGPA ;
        estimatedGPA = 9*(getCurrentTotalCreds() + nextCreds) - (getCurrentTotalCreds()*currentCGPA) ;
        estimatedGPA = estimatedGPA/nextCreds ;
        return estimatedGPA ;
    }
}
