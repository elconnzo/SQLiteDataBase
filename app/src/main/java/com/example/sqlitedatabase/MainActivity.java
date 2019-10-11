package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an instance of an SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
    }

    public void signIn(View V){
        try{
            String username = ((EditText)findViewById(R.id.editText_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.editText_password)).getText().toString();

            //fetch the Password from the database for the username
            String storedPassword = loginDataBaseAdapter.getSingleEntry(username);

            if(password.equals(storedPassword)) {
                Toast.makeText(MainActivity.this, "Successful Login",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,
                        LoginSuccessActivity.class);
                intent.putExtra("Name", username);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this,
                        "The given records are not available, please sign up",
                        Toast.LENGTH_LONG).show();
            }

        }catch (Exception ex){
            Log.e("Error", "error login");
        }
    }

    public void GoToSignUP(View view){
        Intent intent = new Intent(MainActivity.this, signUp.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Close the Database
        loginDataBaseAdapter.close();
    }
}
