package com.example.ayurvaidya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView d;
    EditText e,p;
    Button b,c;
    ProgressBar x;
    FirebaseAuth a=FirebaseAuth.getInstance();
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = a.getCurrentUser();
        if(currentUser != null){
            Intent home=new Intent(getApplicationContext(),Home.class);
            startActivity(home);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FirebaseApp.initializeApp(this);

        e=findViewById(R.id.email);
        p=findViewById(R.id.password);
        b=findViewById(R.id.Login);
        c=findViewById(R.id.ab);
        d=findViewById(R.id.signupx);
        x=findViewById(R.id.bar);
        x.setVisibility(View.GONE);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent su=new Intent(getApplicationContext(), SignUp.class);
                startActivity(su);
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
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pa)) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                x.setVisibility(View.VISIBLE);
                a.signInWithEmailAndPassword(em, pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                x.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "LogIn Success",
                                            Toast.LENGTH_SHORT).show();
                                    Intent home=new Intent(getApplicationContext(),Home.class);
                                    startActivity(home);
                                    finish();
                                } else {

                                    Toast.makeText(Login.this, "Email or Password Incorrect.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

    }
});
    }
}