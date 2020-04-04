package com.atc.newsappfinalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.atc.newsappfinalproject.Adapter.BusinessAdapter;
import com.atc.newsappfinalproject.Model.ArticleModel;
import com.atc.newsappfinalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {

    private static final String URL = "https://gist.githubusercontent.com/diptoroy/42051c2b3af7dd727f5dcb8f6a077f95/raw/f9a9fe0cf551dcafda3f5ed146c00e912a45074e/news";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ArticleModel> articleList;
    private RecyclerView recyclerView;
    private BusinessAdapter businessAdapter;

    //private ProgressBar progressBar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        articleList = new ArrayList<>();
        recyclerView = findViewById(R.id.entertainment_recyclerView);
        //articleList.add(new ArticleModel("Title","Jhon wick","Detail","www","www","2022"));
        setRecyclerView(articleList);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Entertainment News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // progressBar = findViewById(R.id.progressBar);
        //  progressBar.setVisibility(View.VISIBLE);


        jsonParse();
    }


    private void jsonParse() {

        // progressBar.setVisibility(View.INVISIBLE);
        request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i=0;i<response.length();i++){

                    try {
                        jsonObject = response.getJSONObject(i);
                        ArticleModel article = new ArticleModel();
                        article.setTitle(jsonObject.getString("title"));
                        article.setAuthor(jsonObject.getString("author"));
                        article.setDescription(jsonObject.getString("description"));
                        article.setPublishedAt(jsonObject.getString("publishedAt"));
                        article.setUrlToImage(jsonObject.getString("urlToImage"));
                        article.setUrl(jsonObject.getString("url"));
                        articleList.add(article);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                businessAdapter.setArticleModels(articleList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(EntertainmentActivity.this);
        requestQueue.add(request);
        //requestQueue.start();

    }


    private void setRecyclerView(final List<ArticleModel> articleList) {

        businessAdapter = new BusinessAdapter(articleList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(businessAdapter);
        businessAdapter.setOnClickListener(new BusinessAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(View view, int position) {
                ArticleModel articleModel = articleList.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("url", articleModel.getUrl());
                startActivity(intent);
            }
        });
    }
}
