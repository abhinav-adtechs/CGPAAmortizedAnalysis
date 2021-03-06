package abhinav.hackdev.co.amortizedanalysis.Controller;

import android.os.Bundle;

import abhinav.hackdev.co.amortizedanalysis.View.Fragments.GPAFragment;


public class CustomAdapter extends android.support.v13.app.FragmentPagerAdapter {

    Bundle bundle ;


    public CustomAdapter(android.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.app.Fragment getItem(int position) {
        bundle = new Bundle() ;
        bundle.putInt("position", position+1);

        return new GPAFragment().newInstance(position+1);

    }

    @Override
    public int getCount() {
        return 8;
    }
}
