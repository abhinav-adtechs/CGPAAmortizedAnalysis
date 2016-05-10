package abhinav.hackdev.co.amortizedanalysis;


import java.util.ArrayList;

public class DataListEvent {

    private ArrayList<GPAData> gpaDataArrayList ;

    public DataListEvent(ArrayList<GPAData> gpaDataArrayList) {
        this.gpaDataArrayList = gpaDataArrayList;
    }

    public ArrayList<GPAData> getGpaDataArrayList() {
        return gpaDataArrayList;
    }

    public void setGpaDataArrayList(ArrayList<GPAData> gpaDataArrayList) {
        this.gpaDataArrayList = gpaDataArrayList;
    }
}
