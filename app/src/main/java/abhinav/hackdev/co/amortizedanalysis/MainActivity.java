package abhinav.hackdev.co.amortizedanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private CustomAdapter customAdapter ;
    private CustomViewPager customViewPager ;
    private InkPageIndicator inkPageIndicator ;
    private BarChart barChart ;

    private List<BarEntry> yVals ;
    private ArrayList<String> xVals ;

    private float amortizedGPA ;

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

        inkPageIndicator = (InkPageIndicator)findViewById(R.id.indicator) ;
        customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager) ;
        customAdapter = new CustomAdapter(getFragmentManager()) ;
        customViewPager.setAdapter(customAdapter);
        inkPageIndicator.setViewPager(customViewPager);
        barChart = (BarChart) findViewById(R.id.chart) ;
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

       /* for(int j=1 ; j< 9 ; j++){
            updateChartData(j*2, j-1);
        }*/


    }

    public void updateChartData(float val, int index){
        yVals.add(new BarEntry(val, index)) ;


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

        amortizedGPA = new CalculateAmortizedGPA().getAmortizedGPA(GPAList.newInstance().getGpaList()) ;
        updateChartData(dataUpdateEvent.getDataValue(), dataUpdateEvent.getDataIndex());
        customViewPager.setCurrentItem(customViewPager.getCurrentItem()+1);
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
}
