package com.example.tpfmassonnicolas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    Button loginMain;
    Button cancelMain;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponent();
    }

    //Method to create the components
    private void setComponent() {
        username = (EditText) findViewById(R.id.etvUsernameAccount);
        password = (EditText) findViewById(R.id.etvPasswordAccount);

        loginMain = (Button) findViewById(R.id.btLoginAccount);
        cancelMain = (Button) findViewById(R.id.btCancelAccount);
        createAccount = (Button) findViewById(R.id.btCreateAccount);
    }

    // Method to get into list movie (ListMovie), in case any box is not filled or username/password incorrect, it will show a message
    public void loginMain(View v) {
        if ((username.getText().toString().trim().isEmpty()) || (password.getText().toString().trim().isEmpty())) {
            Toast.makeText(this, "Enter Value, please", Toast.LENGTH_SHORT).show();
        } else {
            if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                Intent nextPage = new Intent(this, ListMovie.class);
                startActivity(nextPage);
            } else {
                Toast.makeText(this, "User Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to delete the box
    public void cancelMain(View v) {
        username.setText("");
        password.setText("");
    }

    // Method to access created page (PageCreateAccount)
    public void createAccount(View v) {
        Intent nextPage = new Intent(this, PageCreateAccount.class);
        startActivity(nextPage);
    }

}