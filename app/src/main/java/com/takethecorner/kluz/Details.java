package com.takethecorner.kluz;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;



public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) findViewById(R.id.textView);
        TextView date_created = (TextView) findViewById(R.id.date);
        TextView author = (TextView) findViewById(R.id.textView2);
        ImageView thumbnail = (ImageView) findViewById(R.id.imageView);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getSerializableExtra("article")!=null){
            Article article = (Article) getIntent().getSerializableExtra("article");
            title.setText(article.getTitle());
            date_created.setText("Posted On: "+ article.getDateCreated());
            author.setText(article.getArticlel());
            Glide.with(this).load(article.getThumbnail()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade().placeholder(R.drawable.background).into(thumbnail);

        }
    }

}
