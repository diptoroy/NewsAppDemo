package com.atc.newsappfinalproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.atc.newsappfinalproject.Adapter.CategoryAdapter;
import com.atc.newsappfinalproject.Adapter.HeadlineAdapter;
import com.atc.newsappfinalproject.Model.CategoryModel;
import com.atc.newsappfinalproject.Model.HeadlineModel;
import com.atc.newsappfinalproject.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;

    RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    RecyclerView.LayoutManager caLayoutManager;
    int[] sampleImages = {R.drawable.news, R.drawable.bn, R.drawable.tech};
    
    //Headline

    private static final String URL = "https://gist.githubusercontent.com/diptoroy/42051c2b3af7dd727f5dcb8f6a077f95/raw/f9a9fe0cf551dcafda3f5ed146c00e912a45074e/news";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<HeadlineModel> headlineModels;
    private RecyclerView recyclerView;
    private HeadlineAdapter headlineAdapter;

    private TextView marquee;
    private Toolbar toolbar;
    private ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marquee = (TextView) this.findViewById(R.id.mywidget);
        marquee.setSelected(true);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("News App");
        setSupportActionBar(toolbar);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.VISIBLE);

        ArrayList<CategoryModel> clist = new ArrayList<>();
        clist.add(new CategoryModel("Business",R.drawable.business));
        clist.add(new CategoryModel("Health",R.drawable.health));
        clist.add(new CategoryModel("Sports",R.drawable.sports));
        clist.add(new CategoryModel("Entertainment",R.drawable.entertainment));
        clist.add(new CategoryModel("Technology",R.drawable.technology));

        categoryRecyclerview =  findViewById(R.id.category_recyclerview);
        categoryAdapter = new CategoryAdapter(clist);
        caLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerview.setLayoutManager(caLayoutManager);
        categoryRecyclerview.setAdapter(categoryAdapter);

        headlineModels = new ArrayList<>();
        recyclerView = findViewById(R.id.headlines_recyclerview);
        setRecyclerView(headlineModels);
       // headlineModels.add(new HeadlineModel("Title","Jhon wick","Detail","www","2022/21/12"));


        headlineLoad();
    }

    private void headlineLoad() {

        request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0;i<response.length();i++){

                    try {
                        progressBar2.setVisibility(View.INVISIBLE);
                        jsonObject = response.getJSONObject(i);
                        HeadlineModel headlineModel = new HeadlineModel();
                        headlineModel.setTitle(jsonObject.getString("title"));
                        headlineModel.setAuthor(jsonObject.getString("author"));
                        headlineModel.setDescription(jsonObject.getString("description"));
                        headlineModel.setPublishedAt(jsonObject.getString("publishedAt"));
                        headlineModel.setUrl(jsonObject.getString("url"));
                        headlineModels.add(headlineModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                headlineAdapter.setHeadlineModels(headlineModels);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
        requestQueue.start();
    }

    private void setRecyclerView(final List<HeadlineModel> headlineModelList) {

        headlineAdapter = new HeadlineAdapter(headlineModelList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(headlineAdapter);
        headlineAdapter.setOnClickListener(new HeadlineAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                HeadlineModel articleModel = headlineModelList.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("url", articleModel.getUrl());
                startActivity(intent);
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
