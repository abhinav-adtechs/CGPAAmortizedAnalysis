package abhinav.hackdev.co.amortizedanalysis.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import abhinav.hackdev.co.amortizedanalysis.Controller.AmortizedAdapter;
import abhinav.hackdev.co.amortizedanalysis.Model.Entities.CalculateAmortizedGPA;
import abhinav.hackdev.co.amortizedanalysis.Model.Entities.EstimatedList;
import abhinav.hackdev.co.amortizedanalysis.Model.Entities.GPAData;
import abhinav.hackdev.co.amortizedanalysis.R;


public class AlertFragment extends AppCompatActivity{

    private List<EstimatedList> estimatedLists = new ArrayList<>();
    private RecyclerView recyclerView ;
    private AmortizedAdapter amortizedAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_alert_amortized);

        recyclerView = (RecyclerView) findViewById(R.id.alert_recycler_view) ;
        amortizedAdapter = new AmortizedAdapter(estimatedLists) ;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(amortizedAdapter);

        setData() ;
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
}
