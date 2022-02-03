package com.example.tpfmassonnicolas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailMovie extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView movieRating;
    private TextView movieDescription;

    private Movie itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
    }

    //Method for initialize views
    private void initViews() {

        name = findViewById(R.id.tvNameDetail);
        type = findViewById(R.id.tvTypeDetail);
        movieRating = findViewById(R.id.tvMovieRatingDetail);
        movieDescription = findViewById(R.id.tvMovieDescriptionDetail);
    }

    //Method for initialize values
    private void initValues() {
        itemDetail = (Movie) getIntent().getExtras().getSerializable("DetailMovie");

        if (itemDetail.getName()==null){
            movieDescription.setVisibility(View.GONE);
            name.setText("UserId: " + itemDetail.getUserId());
            type.setText("Id: " + itemDetail.getId());
            movieRating.setText("Title: " + itemDetail.getTitle());
        }else {
            movieDescription.setVisibility(View.VISIBLE);
            name.setText(itemDetail.getName() + " (" + itemDetail.getYear() + ")");
            type.setText("Category: " + itemDetail.getType());
            movieRating.setText("Ranking: " + itemDetail.getMovieRating());
            movieDescription.setText("Description: " + itemDetail.getMovieDescription());
        }

    }
}
