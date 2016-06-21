package abhinav.hackdev.co.amortizedanalysis.Controller;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abhinav.hackdev.co.amortizedanalysis.Model.Entities.EstimatedList;
import abhinav.hackdev.co.amortizedanalysis.R;

public class AmortizedAdapter extends RecyclerView.Adapter<AmortizedAdapter.ListHolder>{

    List<EstimatedList> estimatedLists ;

    public AmortizedAdapter(List<EstimatedList> estimatedLists) {
        this.estimatedLists = estimatedLists;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        EstimatedList estimatedList = estimatedLists.get(position) ;
        holder.tvCreds.setText(String.valueOf(estimatedList.getEstCreds()));
        if(estimatedList.getEstGPA() > 10){
            holder.tvGPA.setText("Above 10");
        }else {
            holder.tvGPA.setText(String.valueOf(estimatedList.getEstGPA()));
        }
    }

    @Override
    public int getItemCount() {
        return estimatedLists.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder{

        private TextView tvCreds, tvGPA ;

        public ListHolder(View itemView) {
            super(itemView);
            tvCreds = (TextView) itemView.findViewById(R.id.list_creds) ;
            tvGPA = (TextView) itemView.findViewById(R.id.list_gpa) ;

        }



    }

}
