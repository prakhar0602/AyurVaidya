package com.example.ayurvaidya;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText e,p;
    Button b,c;
    ProgressBar x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_sign_up);
        e=findViewById(R.id.email);
        p=findViewById(R.id.password);
        b=findViewById(R.id.SignUp);
        c=findViewById(R.id.ab);
        FirebaseAuth a=FirebaseAuth.getInstance();
        x=findViewById(R.id.bar);
        x.setVisibility(View.GONE);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent li=new Intent(getApplicationContext(),Login.class);
                startActivity(li);
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                x.setVisibility(View.VISIBLE);
                String em=String.valueOf(e.getText());
                String pa=String.valueOf(p.getText());
                if(TextUtils.isEmpty(em)) {
                    Toast.makeText(SignUp.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pa)) {
                    Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                x.setVisibility(View.VISIBLE);
                a.createUserWithEmailAndPassword(em, pa)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                x.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Welcome to AyurVaidya",
                                            Toast.LENGTH_SHORT).show();
                                    Intent home=new Intent(getApplicationContext(),Home.class);
                                    startActivity(home);
                                    finish();
                                } else {
                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
}