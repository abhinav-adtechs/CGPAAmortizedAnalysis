package abhinav.hackdev.co.amortizedanalysis;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AlertDialogFragment extends DialogFragment{

    private static final String TAG = "SPECIAL TAG";
    private List<EstimatedList> estimatedLists = new ArrayList<>();
    private RecyclerView recyclerView ;
    private AmortizedAdapter amortizedAdapter ;
    private ArrayList<GPAData> arrayListData ;

    static AlertDialogFragment newInstance(){
        return new AlertDialogFragment() ;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alert_amortized, container, false) ;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.alert_recycler_view) ;
        amortizedAdapter = new AmortizedAdapter(estimatedLists) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(amortizedAdapter);

        setData() ;

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void setListItem(DataListEvent dataListEvent){
        arrayListData = dataListEvent.getGpaDataArrayList() ;
        for (int i = 0; i < arrayListData.size(); i++) {
            Log.d(TAG, "setListItem: " + arrayListData.get(i).getSemGPA());
        }
    }

    private void setData() {


        for (int i = 16; i <=27 ; i++) {
            estimatedLists.add(new EstimatedList(i, new CalculateAmortizedGPA(arrayListData).getMinPredictedGPA(i) ));
        }

        amortizedAdapter.notifyDataSetChanged();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity()) ;
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }
}
