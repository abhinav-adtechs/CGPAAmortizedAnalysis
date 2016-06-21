package abhinav.hackdev.co.amortizedanalysis.View.Activities;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import abhinav.hackdev.co.amortizedanalysis.Controller.CustomAdapter;
import abhinav.hackdev.co.amortizedanalysis.Model.Entities.GPAData;
import abhinav.hackdev.co.amortizedanalysis.Model.Entities.CalculateCGPA;
import abhinav.hackdev.co.amortizedanalysis.Model.EventBusEvents.DataListEvent;
import abhinav.hackdev.co.amortizedanalysis.Model.EventBusEvents.DataUpdateEvent;
import abhinav.hackdev.co.amortizedanalysis.R;
import abhinav.hackdev.co.amortizedanalysis.View.CustomViews.CustomViewPager;
import abhinav.hackdev.co.amortizedanalysis.View.Fragments.AlertDialogFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private TextView tvResults;
    private CustomAdapter customAdapter ;
    private CustomViewPager customViewPager ;
    private InkPageIndicator inkPageIndicator ;
    private BarChart barChart ;

    private List<BarEntry> yVals ;
    private ArrayList<String> xVals ;
    private ArrayList<GPAData> gpaDataArrayList;

    private float amortizedGPA ;
    private float tempCGPA ;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>AmortizedAnalysis </font>"));

        tvResults = (TextView) findViewById(R.id.estimated_gpa) ;
        inkPageIndicator = (InkPageIndicator)findViewById(R.id.indicator) ;
        customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager) ;
        customAdapter = new CustomAdapter(getFragmentManager()) ;
        customViewPager.setAdapter(customAdapter);
        inkPageIndicator.setViewPager(customViewPager);
        barChart = (BarChart) findViewById(R.id.chart) ;

        gpaDataArrayList = new ArrayList<>() ;



        chartHandling() ;
    }


    private void chartHandling() {
        barChart.setTouchEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setBorderColor(getResources().getColor(R.color.black));

        barChart.setPadding(0,0,0,0);
        XAxis xAxis = barChart.getXAxis() ;
        YAxis yAxis = barChart.getAxisLeft() ;
        yAxis.setAxisLineColor(getResources().getColor(R.color.colorPrimaryDark));

        getData() ;

    }

    public void getData() {
        xVals = new ArrayList<>() ;
        for (int i = 1; i < 9; i++) {
            xVals.add("SEM " + i);
        }

        yVals = new ArrayList<>();



    }

    public void updateChartData(float val, int index){
        yVals.add(new BarEntry(val, index-1)) ;

        Log.d(TAG, "updateChartData: " + val + index);

        BarDataSet barDataSet = new BarDataSet(yVals, "GPA") ;

        barDataSet.setBarShadowColor(getResources().getColor(R.color.black));
        barDataSet.setColor(getResources().getColor(R.color.colorPrimary));
        BarData barData = new BarData(xVals, barDataSet) ;

        barChart.setData(barData);
        barChart.notifyDataSetChanged();

        barChart.animateXY(2000, 2000) ;
        barChart.invalidate();
    }

    @Subscribe
    public void updateChartFromGPA(DataUpdateEvent dataUpdateEvent){
        gpaDataArrayList.add(new GPAData(dataUpdateEvent.getDataValue(), dataUpdateEvent.getDataCredits(), dataUpdateEvent.getDataIndex())) ;
        tempCGPA = new CalculateCGPA(gpaDataArrayList).getCGPA() ;
        updateChartData(dataUpdateEvent.getDataValue(), dataUpdateEvent.getDataIndex());
        customViewPager.setCurrentItem(customViewPager.getCurrentItem()+1);

        EventBus.getDefault().postSticky(new DataListEvent(gpaDataArrayList));

        DialogFragment dialogFragment = AlertDialogFragment.newInstance() ;
        dialogFragment.show(getFragmentManager(), "TAG") ;

        printArrayList();
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void printArrayList(){
        for (int i = 0; i < gpaDataArrayList.size(); i++) {
            Log.d(TAG, "printArrayList: " +
                gpaDataArrayList.get(i).getSemVal() + " " +
                gpaDataArrayList.get(i).getSemGPA() + " " +
                gpaDataArrayList.get(i).getSemCreds()
            );
        }

        Log.d(TAG, "TempCGPA: " + tempCGPA);
        if(tempCGPA > 9){
            tvResults.setText("Go and Explore the world! CGPA: " + tempCGPA);
        }else {
            tvResults.setText("Start Studying! CGPA: " + tempCGPA);
        }
    }

}
