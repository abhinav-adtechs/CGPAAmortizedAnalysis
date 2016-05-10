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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class GPAFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "GALAT BAAT BETA";
    private TextView textView ;
    private Button btnGPA ;
    private EditText etGPA ;
    private EditText etCredits ;
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
        etCredits = (EditText) view.findViewById(R.id.fragment_credits) ;
        textView = (TextView) view.findViewById(R.id.fragment_semester_number) ;
        textView.setText("SEMESTER " + String.valueOf(position));

        btnGPA.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button) {
            if (!etGPA.getText().toString().isEmpty() && Float.parseFloat(etGPA.getText().toString()) <= 10 && Float.parseFloat(etGPA.getText().toString()) >= 0) {
                    GPAList.newInstance().addToList(
                            Float.parseFloat(etGPA.getText().toString()),
                            Integer.getInteger(etCredits.getText().toString()),
                            position);
                    EventBus.getDefault().post(
                            new DataUpdateEvent(Float.parseFloat(etGPA.getText().toString()),
                                    Integer.getInteger(etCredits.getText().toString()),
                                    position));
            }
            else{
                Log.d(TAG, "onClick: ");
                Toast.makeText(getActivity(), "CGPA OUT OF BOUNDS!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
