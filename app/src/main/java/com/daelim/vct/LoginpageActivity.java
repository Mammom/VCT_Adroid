package com.daelim.vct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginpageActivity extends AppCompatActivity {

    Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        init();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginpageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void init(){
        bt_login = findViewById(R.id.bt_Login);
    }
}

