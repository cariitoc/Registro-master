package com.example.jerryminotta.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login extends AppCompatActivity {

    EditText et_correo, et_pass;
    TextView no_account;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correo = findViewById(R.id.et_correo);
        et_pass = findViewById(R.id.et_pass);
        no_account = findViewById(R.id.no_account);
        btn_login = findViewById(R.id.btn_login);


        no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(login.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
