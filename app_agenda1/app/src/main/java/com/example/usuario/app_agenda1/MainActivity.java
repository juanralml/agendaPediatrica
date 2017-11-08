package com.example.usuario.app_agenda1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText passUser;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userName);
        passUser = (EditText) findViewById(R.id.passUser);
        buttonLogin = (Button) findViewById(R.id.buttonLog);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SonActivity = new Intent(getApplicationContext(),SonsActivity.class);
                startActivity(SonActivity);
            }
        });
    }
}
