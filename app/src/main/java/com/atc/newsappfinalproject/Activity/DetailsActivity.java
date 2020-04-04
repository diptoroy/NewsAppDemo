package com.atc.newsappfinalproject.Activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.atc.newsappfinalproject.R;

public class DetailsActivity extends AppCompatActivity {

    private WebView webView;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        webView = (WebView) findViewById(R.id.webView);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String url = bundle.getString("url");

            webView.loadUrl(url);
        }
    }
}
