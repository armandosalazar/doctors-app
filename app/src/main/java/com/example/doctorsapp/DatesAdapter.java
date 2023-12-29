package com.example.doctorsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctorsapp.models.Date;

import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.ViewHolder> {
    private final List<Date> dates;

    public DatesAdapter(List<Date> dates) {
        this.dates = dates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date = dates.get(position);
        holder.textViewName.setText(String.format("%s %s", date.getNames(), date.getLastNames()));
        holder.textViewSex.setText(date.getSex());
        holder.textViewPhone.setText(date.getPhone());
        holder.textViewSymptoms.setText(date.getSymptoms());
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewSex;
        private final TextView textViewPhone;
        private final TextView textViewSymptoms;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_name);
            textViewSex = itemView.findViewById(R.id.tv_sex);
            textViewPhone = itemView.findViewById(R.id.tv_phone);
            textViewSymptoms = itemView.findViewById(R.id.tv_symptoms);

        }
    }
}
