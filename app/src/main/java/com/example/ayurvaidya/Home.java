package com.example.ayurvaidya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    Button x,y;
    FirebaseAuth a;
    TextView t;
    ConstraintLayout sa,he,yl,bl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        x=findViewById(R.id.logout);
        a=FirebaseAuth.getInstance();
        t=findViewById(R.id.name);
        sa=findViewById(R.id.sa);
        he=findViewById(R.id.he);
        yl=findViewById(R.id.yl);
        bl=findViewById(R.id.bl);
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), symptom_checker.class);
                startActivity(i);
            }
        });
        yl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Yoga.class);
                startActivity(i);
            }
        });

        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Blogs.class);
                startActivity(i);
            }
        });
        FirebaseUser u=a.getCurrentUser();
        if(u==null){
            Intent lo=new Intent(getApplicationContext(),Login.class);
            startActivity(lo);
        }
        String yy=" ";
        for(int i=0;i<u.getEmail().length();i++)
            if(u.getEmail().charAt(i)=='@')
                break;
        else {
                char a = u.getEmail().charAt(i);
                if (i == 0 && a >= 'a')
                    a -= 32;
                yy += a;
            }
        t.setText(yy);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.signOut();
                Intent lo=new Intent(getApplicationContext(),Login.class);
                startActivity(lo);
                finish();
            }
        });

        BottomNavigationView bnv = findViewById(R.id.bottom_menu);

        bnv.setSelectedItemId(R.id.tab_home);
        bnv.setOnItemSelectedListener(item-> {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
                switch (item.getItemId()) {
                    case R.id.tab_home:
                        return true;
                    case R.id.tab_search:
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        return true;
                    case R.id.tab_folder:
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        return true;
                    case R.id.tab_profile:
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        return true;

                }
                return false;
            });
    }
}