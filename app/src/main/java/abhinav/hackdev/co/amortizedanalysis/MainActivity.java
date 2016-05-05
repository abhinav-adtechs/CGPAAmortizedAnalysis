package abhinav.hackdev.co.amortizedanalysis;

import android.content.Entity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Array;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inkPageIndicator = (InkPageIndicator)findViewById(R.id.indicator) ;
        customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager) ;
        customAdapter = new CustomAdapter(getFragmentManager()) ;
        customViewPager.setAdapter(customAdapter);
        inkPageIndicator.setViewPager(customViewPager);
        barChart = (BarChart) findViewById(R.id.chart) ;
        chartHandling() ;
    }

    private void chartHandling() {
        barChart.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        barChart.setTouchEnabled(true);
        barChart.setDrawGridBackground(false);
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

        for(int j=1 ; j< 9 ; j++){
            updateChart(j*2, j-1);
        }


    }

    @Subscribe
    public void updateChart(int val, int index){
        yVals.add(new BarEntry(val, index)) ;


        BarDataSet barDataSet = new BarDataSet(yVals, "GPA") ;
        BarData barData = new BarData(xVals, barDataSet) ;

        barChart.setData(barData);
        barChart.notifyDataSetChanged();

        barChart.animateXY(2000, 2000) ;
        barChart.invalidate();
    }
}
