package com.education;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.ResultsAdapter;
import util.CommonClass;
import util.JSONReader;

public class ResultActivity extends CommonAppCompatActivity {
    CommonClass common;
    JSONReader j_reader;
    JSONArray objResultArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_41));
        }

        common = new CommonClass(this);
        j_reader = new JSONReader(this);

        ListView listView = (ListView)findViewById(R.id.listView2);
        final ResultsAdapter adapter = new ResultsAdapter(this, new JSONArray());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject obj = adapter.getItem(position);
                try {
                    //new getResultTask().execute(obj.getString("exam_id"));
                    Intent intent = new Intent(ResultActivity.this, ResultListActivity.class);
                    intent.putExtra("exam_id",obj.getString("exam_id"));
                    intent.putExtra("exam",obj.toString());
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });
    }


    public  void  showNoticePopup(){

    }




}
