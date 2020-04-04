package com.atc.newsappfinalproject.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.atc.newsappfinalproject.Model.HeadlineModel;
import com.atc.newsappfinalproject.R;
import com.atc.newsappfinalproject.Util.Util;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.MyViewHolder> {

    private List<HeadlineModel> headlineModels;
    private Context context;
    private OnItemClickListner itemClickListner;

    public HeadlineAdapter(List<HeadlineModel> headlineModels, Context context) {
        this.headlineModels = headlineModels;
        this.context = context;
    }

    public void setHeadlineModels(List<HeadlineModel> headlineModels) {
        this.headlineModels = headlineModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.headlines_row,parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HeadlineModel headlineModel = headlineModels.get(position);
        holder.hTitle.setText(headlineModel.getTitle());
        holder.hAuthor.setText(headlineModel.getAuthor());
        holder.hDetail.setText(headlineModel.getDescription());
        holder.hDate.setText(Util.dateFormatted(headlineModel.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return headlineModels.size();
    }

    public void setOnClickListener(OnItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView hTitle,hDetail,hDate,hAuthor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hTitle = itemView.findViewById(R.id.headlines_title);
            hDetail = itemView.findViewById(R.id.headlines_detail);
            hDate = itemView.findViewById(R.id.headlines_date);
            hAuthor = itemView.findViewById(R.id.headlines_author);

            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            itemClickListner.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListner {
        void onItemClick(View view, int position);
    }
}
