package com.example.ayurvaidya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a=findViewById(R.id.textView6);

        BottomNavigationView bnv = findViewById(R.id.bottom_menu);


        int i1=3;
        if(bnv.getSelectedItemId()==R.id.tab_home)
            i1=0;
        else if(bnv.getSelectedItemId()==R.id.tab_search)
            i1=1;
        else if(bnv.getSelectedItemId()==R.id.tab_folder)
            i1=2;
        else
            i1=3;
        bnv.setSelectedItemId(R.id.tab_search);
        Menu m=bnv.getMenu();

        a.setText((m.getItem(i1).getTitle()).toString());
        bnv.setOnItemSelectedListener(item-> {
            Intent i=new Intent(getApplicationContext(),Home.class);
            switch (item.getItemId()) {
                case R.id.tab_home:
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    finish();
                    return true;
            }

            return false;
        });

    }


}