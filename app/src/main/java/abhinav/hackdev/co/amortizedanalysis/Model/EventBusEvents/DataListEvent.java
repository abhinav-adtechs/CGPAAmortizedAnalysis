package abhinav.hackdev.co.amortizedanalysis.Model.EventBusEvents;


import java.util.ArrayList;

import abhinav.hackdev.co.amortizedanalysis.Model.Entities.GPAData;

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
