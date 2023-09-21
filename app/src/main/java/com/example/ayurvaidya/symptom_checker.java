package com.example.ayurvaidya;
import java.util.HashMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class symptom_checker extends AppCompatActivity {

    ListView lv;
    RecyclerView tags;
    SearchView sv;
    LinearLayout ll;

    private void handleItemClick(String p)
    {
        sv.setQuery(p,true);
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);
        lv=findViewById(R.id.searchrec);
        sv=findViewById(R.id.searchView);
        lv.setVisibility(View.GONE);
        ArrayList<String> a=new ArrayList<>();
        ArrayList<String> tag=new ArrayList<>();
        a.add("fever");
        a.add("jwara");
        a.add("cancer");
        a.add("corona");
        a.add("brain tumor");
        HashMap<String,Boolean> map=new HashMap<>();
        for(String i:a)
            map.put(i,false);

        ll=findViewById(R.id.tags);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = parent.getItemAtPosition(position).toString();
                handleItemClick(clickedItem);
            }
        });

        ArrayAdapter<String> x=new ArrayAdapter(this, android.R.layout.simple_list_item_1,a);
        lv.setAdapter(x);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                lv.setVisibility(View.GONE);
                if(!map.containsKey(s))
                {
                    Toast.makeText(symptom_checker.this, "Invalid Symptom, please try again", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(map.get(s))
                    return false;
                map.put(s,true);
                tag.add(s);

                TextView z=new TextView(symptom_checker.this);
                z.setText(s);
                z.setBackgroundResource(R.drawable.item_layout);
                z.setTextSize(25);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,  // Width (MATCH_PARENT, WRAP_CONTENT, or a specific value)
                        LinearLayout.LayoutParams.WRAP_CONTENT  // Height (MATCH_PARENT, WRAP_CONTENT, or a specific value)
                );
                lp.setMargins(0,0,30,0);
                z.setLayoutParams(lp);
                z.setPadding(10,10,10,10);
                z.setTextColor(getResources().getColor(R.color.black));
                z.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        map.put(s,false);
                        tag.remove(String.valueOf(s));
                        ll.removeView(view);
                    }
                });
                ll.addView(z);
                sv.setQuery("",false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(sv.getQuery().length()>0)
                lv.setVisibility(View.VISIBLE);
                else
                    lv.setVisibility(View.GONE);
                x.getFilter().filter(s);

                return false;
            }
        });
    }
}