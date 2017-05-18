package com.example.venki.singleselection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by venkateswarlu on 5/18/2017.
 */

public class MainActivity extends AppCompatActivity {
    private TextView selectedText;
    private Button btnSingleSelection;
    private static final int GET_SELECTED_ISSUE_CODE = 103;
    private String selectedString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedText = (TextView) findViewById(R.id.tv_selected);
        btnSingleSelection = (Button) findViewById(R.id.btn_single);
        btnSingleSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call an activity
                Intent intent=new Intent(MainActivity.this,SingleSelectionActivity.class);
                startActivityForResult(intent, GET_SELECTED_ISSUE_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_SELECTED_ISSUE_CODE && resultCode == RESULT_OK) {
            selectedString = data.getStringExtra("SELECTED_ISSUE");
            selectedText.setText("Selected Text:"+selectedString);
        }
    }
}
