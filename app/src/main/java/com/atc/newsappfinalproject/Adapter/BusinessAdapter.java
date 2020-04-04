package com.atc.newsappfinalproject.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.atc.newsappfinalproject.Model.ArticleModel;
import com.atc.newsappfinalproject.R;
import com.atc.newsappfinalproject.Util.Util;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.MyViewHolder> {

    private List<ArticleModel> articleModels;
    private Context context;
    RequestOptions options;
    private OnItemClickListner itemClickListner;

    public void setArticleModels(List<ArticleModel> articleModels) {
        this.articleModels = articleModels;
        notifyDataSetChanged();
    }

    public BusinessAdapter(List<ArticleModel> articleModels, Context context) {
        this.articleModels = articleModels;
        this.context = context;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_row,parent,false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArticleModel articleModel = articleModels.get(position);

        holder.title.setText(articleModel.getTitle());
        holder.author.setText(articleModel.getAuthor());
        holder.desc.setText(articleModel.getDescription());
        holder.date.setText(Util.dateFormatted(articleModel.getPublishedAt()));

        Glide.with(context).load(articleModel.getUrlToImage()).apply(options).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articleModels.size();
    }

    public void setOnClickListener(OnItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,author,desc,date;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            desc = itemView.findViewById(R.id.detail);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);

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
