package com.example.jerryminotta.registro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre, et_correo, et_pass;
    Button btn_reg;
    FirebaseDatabase db;
    TextView has_account;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        et_nombre= findViewById(R.id.et_nombre);
        et_correo= findViewById(R.id.et_correo);
        et_pass= findViewById(R.id.et_pass);
        btn_reg= findViewById(R.id.btn_reg);
        has_account= findViewById(R.id.no_account);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.createUserWithEmailAndPassword(et_correo.getText().toString(),et_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Usuario nuevo = new Usuario(et_nombre.getText().toString(),et_correo.getText().toString(),et_pass.getText().toString());
                            nuevo.setUid(auth.getCurrentUser().getUid());
                            db.getReference().child("usuarios").child(nuevo.getUid()).setValue(nuevo);
                        }else{

                            Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        has_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
