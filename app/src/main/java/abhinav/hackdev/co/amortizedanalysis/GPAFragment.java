package abhinav.hackdev.co.amortizedanalysis;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class GPAFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "Fragment";
    private TextView textView ;
    private Button btnGPA ;
    private EditText etGPA ;
    private int position ;

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

        position = getArguments().getInt("position") ;

        etGPA = (EditText) view.findViewById(R.id.fragment_id) ;
        btnGPA = (Button) view.findViewById(R.id.button) ;
        textView = (TextView) view.findViewById(R.id.fragment_semester_number) ;
        textView.setText("SEMESTER " + String.valueOf(position));

        btnGPA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Log.d(TAG, "onClick: BUTTON");
            Log.d(TAG, "onClick: " + etGPA.getText().toString());
            if(!etGPA.getText().toString().isEmpty()){
                EventBus.getDefault().post(new DataUpdateEvent(Float.parseFloat(etGPA.getText().toString()), position));
            }
        }
    }
}
