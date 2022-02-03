package com.example.tpfmassonnicolas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListMovie extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick {

    private RecyclerView listMovie;
    private RecyclerAdapter adapter;
    private List<Movie> items;

    private static boolean cond = false;

    private static final int REQUEST_CODE = 1;

    private static final List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        if(!cond){
            getPosts();
            cond=true;
        }
        initViews();
        initValues();
    }

    //Method for initialize views the list movie
    private void initViews() {
        listMovie = findViewById(R.id.listMovie);
    }

    //Method for initialize values and adapter
    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listMovie.setLayoutManager(manager);

        items = movies;
        adapter = new RecyclerAdapter(items, this);
        listMovie.setAdapter(adapter);
    }

    //Click method to go to movie detail (DetailMovie)
    @Override
    public void itemClick(Movie item) {
        Intent intent = new Intent(this, DetailMovie.class);
        intent.putExtra("DetailMovie", item);
        startActivity(intent);
    }

    //Method to go to page add movie (AddMovie)
    public void pageAddMovie(View v) {
        startActivityForResult(new Intent(getApplicationContext(), AddMovie.class), REQUEST_CODE);
    }

    //return method of what is obtained in page add movie (AddMovie)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Verification that the REQUEST_CODE belongs to the corresponding activity, like the RESULT_OK. In case different, it will show a message
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Movie movie = (Movie) extras.getSerializable("DetailMovie");
                    String name = movie.getName();
                    String type = movie.getType();
                    int year = movie.getYear();
                    int movieRating = movie.getMovieRating();
                    String movieDescription = movie.getMovieDescription();
                    movies.add(new Movie("" + name, "" + type, year, movieRating, "" + movieDescription));
                    Toast.makeText(this, "Movie Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent listMovie = new Intent(this, ListMovie.class);
                    finish();
                    startActivity(listMovie);
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Unsaved Data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getPosts(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Movie>> call=jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ListMovie.this, "Codigo: "+response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Movie> RetrofitList =response.body();

                for (Movie Retrofit: RetrofitList) {
                    int userId=Retrofit.getUserId();
                    int id=Retrofit.getId();
                    String title=Retrofit.getTitle();

                    movies.add(new Movie(userId,id,""+title));
                }
                LinearLayoutManager manager = new LinearLayoutManager(ListMovie.this);
                listMovie.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(ListMovie.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}