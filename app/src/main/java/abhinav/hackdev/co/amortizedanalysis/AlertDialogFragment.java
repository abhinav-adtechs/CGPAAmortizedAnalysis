package abhinav.hackdev.co.amortizedanalysis;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AlertDialogFragment extends DialogFragment{

    private List<EstimatedList> estimatedLists = new ArrayList<>();
    private RecyclerView recyclerView ;
    private AmortizedAdapter amortizedAdapter ;
    private ArrayList<GPAData> arrayListData ;

    static AlertDialogFragment newInstance(){
        return new AlertDialogFragment() ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alert_amortized, container, false) ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.alert_recycler_view) ;
        amortizedAdapter = new AmortizedAdapter(estimatedLists) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(amortizedAdapter);

        setData() ;

    }

    public void setListItem(ArrayList<GPAData> data){
        arrayListData = data ;
    }

    private void setData() {
        /////SAMPLE DATA

        ArrayList<GPAData> gpaDatas = new ArrayList<>() ;
        gpaDatas.add(new GPAData(Float.parseFloat("9.4"), 24, 1));
        gpaDatas.add(new GPAData(Float.parseFloat("9.3"), 26, 2));
        gpaDatas.add(new GPAData(Float.parseFloat("9.2"), 24, 3));
        gpaDatas.add(new GPAData(Float.parseFloat("9.0"), 24, 4));

        for (int i = 16; i <=27 ; i++) {
            estimatedLists.add(new EstimatedList(i, new CalculateAmortizedGPA(gpaDatas).getMinPredictedGPA(i) ));
        }

        amortizedAdapter.notifyDataSetChanged();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity()) ;
    }
}
