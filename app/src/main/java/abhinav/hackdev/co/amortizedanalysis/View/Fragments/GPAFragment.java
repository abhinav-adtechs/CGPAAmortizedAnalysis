package abhinav.hackdev.co.amortizedanalysis.View.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import abhinav.hackdev.co.amortizedanalysis.Model.Entities.GPAList;
import abhinav.hackdev.co.amortizedanalysis.Model.EventBusEvents.DataUpdateEvent;
import abhinav.hackdev.co.amortizedanalysis.R;

public class GPAFragment extends Fragment implements View.OnClickListener{

    private static final int MAX_SEMESTER_CREDITS = 40;
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
        if (v.getId() != R.id.button) {
            return;
        }

        float gpa;
        int credits;
        try {
            gpa = Float.parseFloat(etGPA.getText().toString());
            credits = Integer.parseInt(etCredits.getText().toString());
        } catch (NumberFormatException e) {
            showInvalidInputs();
            return;
        }

        if (Float.isNaN(gpa) || gpa < 0 || gpa > 10 || credits < 1 || credits > MAX_SEMESTER_CREDITS) {
            showInvalidInputs();
            return;
        }

        GPAList.newInstance().addToList(gpa, credits, position);
        EventBus.getDefault().post(new DataUpdateEvent(gpa, credits, position));
    }

    private void showInvalidInputs() {
        Toast.makeText(getActivity(), "INVALID INPUTS!", Toast.LENGTH_SHORT).show();
    }
}
