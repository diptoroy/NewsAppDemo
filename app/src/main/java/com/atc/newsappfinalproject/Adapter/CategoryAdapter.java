package com.atc.newsappfinalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atc.newsappfinalproject.Activity.BusinessActivity;
import com.atc.newsappfinalproject.Activity.EntertainmentActivity;
import com.atc.newsappfinalproject.Activity.HealthActivity;
import com.atc.newsappfinalproject.Activity.SportsActivity;
import com.atc.newsappfinalproject.Activity.TechnologyAcivity;
import com.atc.newsappfinalproject.ItemClickListener;
import com.atc.newsappfinalproject.Model.CategoryModel;
import com.atc.newsappfinalproject.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<CategoryModel> cList;
    private Context ctx;

    public CategoryAdapter(List<CategoryModel> cList) {
        this.cList = cList;
        this.ctx = ctx;
    }

    //    public CategoryAdapter(List<CategoryModel> cList) {
//        this.cList = cList;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        CategoryModel categoryModel = cList.get(position);
        holder.cText.setText(categoryModel.getCa_name());
        holder.cImage.setImageResource(categoryModel.getCa_img());

       holder.setItemClickListener(new ItemClickListener() {
           @Override
           public void onClick(View view, int position, boolean isLongClick) {
               ctx = view.getContext();
               if(position == 0) {
                   Intent intent = new Intent(ctx, BusinessActivity.class);
                   ctx.startActivity(intent);
               }else if(position == 1){
                   Intent intent = new Intent(ctx, HealthActivity.class);
                   ctx.startActivity(intent);
               }else if(position == 2){
                   Intent intent = new Intent(ctx, SportsActivity.class);
                   ctx.startActivity(intent);
               }else if(position == 3){
                   Intent intent = new Intent(ctx, EntertainmentActivity.class);
                   ctx.startActivity(intent);
               }else if(position == 4){
                   Intent intent = new Intent(ctx, TechnologyAcivity.class);
                   ctx.startActivity(intent);
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView cText;
        public ImageView cImage;
        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cText = itemView.findViewById(R.id.category_name);
            cImage = itemView.findViewById(R.id.category_image);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){

            this.itemClickListener = itemClickListener;
        }
    }
}
