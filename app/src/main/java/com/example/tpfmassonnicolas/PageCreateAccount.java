package com.example.tpfmassonnicolas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PageCreateAccount extends AppCompatActivity {

    private EditText name;
    private EditText familyName;
    private EditText age;
    private EditText username;
    private EditText password;
    Button loginAccount;
    Button cancelAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_create_account);

        setComponent();
    }

    //Method to create component
    private void setComponent() {
        name = (EditText) findViewById(R.id.etvNameAccount);
        familyName = (EditText) findViewById(R.id.etvFamilyNameAccount);
        age = (EditText) findViewById(R.id.etvAgeAccount);
        username = (EditText) findViewById(R.id.etvUsernameAccount);
        password = (EditText) findViewById(R.id.etvPasswordAccount);

        loginAccount = (Button) findViewById(R.id.btLoginAccount);
        cancelAccount = (Button) findViewById(R.id.btCancelAccount);
    }

    // Method to access page list movie (ListMovie), in case any box is not filled, it will show a message
    public void loginAccount(View v) {
        if ((name.getText().toString().trim().isEmpty()) ||
                (familyName.getText().toString().trim().isEmpty()) ||
                (age.getText().toString().trim().isEmpty()) ||
                (username.getText().toString().trim().isEmpty()) ||
                (password.getText().toString().trim().isEmpty())) {
            Toast.makeText(this, "Enter Value, please", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "User Successfully Registered", Toast.LENGTH_SHORT).show();
            Intent nextPage = new Intent(this, ListMovie.class);
            startActivity(nextPage);
        }
    }

    // Method to go back to the main page (MainActivity)
    public void cancelAccount(View v) {
        Intent mainPage = new Intent(this, MainActivity.class);
        startActivity(mainPage);
    }

}