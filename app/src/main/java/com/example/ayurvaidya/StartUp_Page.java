package com.example.ayurvaidya;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class StartUp_Page extends AppCompatActivity {
Button x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_page);
        x=findViewById(R.id.journey);
        SharedPreferences p = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean zyzy = p.getBoolean("intro", false);
        if(zyzy)
        {
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
            finish();
        }

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor e = p.edit();
                e.putBoolean("intro", true);
                e.apply();
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}