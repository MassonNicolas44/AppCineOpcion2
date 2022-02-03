package com.example.tpfmassonnicolas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMovie extends AppCompatActivity {

    private EditText name;
    private EditText type;
    private EditText year;
    private EditText movieRating;
    private EditText movieDescription;
    Button joinAddMovie;
    Button cancelAddMovie;

    private Movie item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        setComponent();

    }


    //Method to create the components
    private void setComponent() {
        name = (EditText) findViewById(R.id.etvNameAddMovie);
        type = (EditText) findViewById(R.id.etvTypeAddMovie);
        year = (EditText) findViewById(R.id.etvYearAddMovie);
        movieRating = (EditText) findViewById(R.id.etvMovieRatingAddMovie);
        movieDescription = (EditText) findViewById(R.id.etvMovieDescriptionAddMovie);

        joinAddMovie = (Button) findViewById(R.id.btJoinAddMovie);
        cancelAddMovie = (Button) findViewById(R.id.btCancelAddMovie);
    }

    // Method to create object Movie and go list movie (ListMovie), in case any box is not filled, it will show a message
    public void joinAdd(View v) {
        if ((name.getText().toString().trim().isEmpty()) ||
                (type.getText().toString().trim().isEmpty()) ||
                (year.getText().toString().trim().isEmpty()) ||
                (movieRating.getText().toString().trim().isEmpty()) ||
                (movieDescription.getText().toString().trim().isEmpty())) {
            Toast.makeText(this, "Enter Value, please", Toast.LENGTH_SHORT).show();
        } else {
            if (Integer.parseInt(movieRating.getText().toString()) >= 0 && Integer.parseInt(movieRating.getText().toString()) <= 10) {
                Intent listMovie = new Intent(this, ListMovie.class);
                Movie movie = new Movie(name.getText().toString(), type.getText().toString(), Integer.parseInt(year.getText().toString()), Integer.parseInt(movieRating.getText().toString()), movieDescription.getText().toString());
                listMovie.putExtra("DetailMovie", movie);
                setResult(RESULT_OK, listMovie);
                finish();
            } else {
                Toast.makeText(this, "Value Movie Rating between 0 and 10", Toast.LENGTH_SHORT).show();
            }

        }
    }

    // Method to back list movie (ListMovie)
    public void cancelMovie(View v) {
        Intent listMovie = new Intent(this, ListMovie.class);
        setResult(RESULT_CANCELED, listMovie);
        finish();
    }
}