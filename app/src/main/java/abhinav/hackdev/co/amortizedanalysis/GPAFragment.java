package abhinav.hackdev.co.amortizedanalysis;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GPAFragment extends Fragment{

    private TextView textView ;

    public GPAFragment() {
    }

    public static GPAFragment newInstance(int position){
        GPAFragment fragment = new GPAFragment() ;
        Bundle bundle = new Bundle() ;
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gpa, container, false) ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = getArguments().getInt("position") ;

        textView = (TextView) view.findViewById(R.id.fragment_semester_number) ;
        textView.setText("SEMESTER " + String.valueOf(position));


    }
}
