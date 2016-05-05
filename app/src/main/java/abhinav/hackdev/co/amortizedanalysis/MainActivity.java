package abhinav.hackdev.co.amortizedanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pixelcan.inkpageindicator.InkPageIndicator;

public class MainActivity extends AppCompatActivity {

    private CustomAdapter customAdapter ;
    private CustomViewPager customViewPager ;
    private InkPageIndicator inkPageIndicator ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inkPageIndicator = (InkPageIndicator)findViewById(R.id.indicator) ;
        customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager) ;
        customAdapter = new CustomAdapter(getFragmentManager()) ;
        customViewPager.setAdapter(customAdapter);
        inkPageIndicator.setViewPager(customViewPager);
    }
}
