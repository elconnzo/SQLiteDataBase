package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //create an instance of the SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
    }

    public void SignUp_OK(View view){
        String username = ((EditText)findViewById(R.id.editText_ca_uname)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText_ca_password)).getText().toString();
        String confirmPassword = ((EditText)findViewById(R.id.editText_ca_cpassword)).getText().toString();

        if(!password.equals(confirmPassword)){
            Toast.makeText(getApplicationContext(), "Passwords do not match!",
                    Toast.LENGTH_LONG).show();
        } else {
            loginDataBaseAdapter.insertEntry(username, password);
            Toast.makeText(getApplicationContext(),
                    "Your account has been created!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(signUp.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Close the Database
        loginDataBaseAdapter.close();
    }
}
