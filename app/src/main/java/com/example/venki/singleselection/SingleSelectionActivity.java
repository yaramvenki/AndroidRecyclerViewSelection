package com.example.venki.singleselection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SingleSelectionActivity extends AppCompatActivity {
    private RecyclerView rvSelections;
    private String selectedItem;
    private static final int GET_SELECTED_ISSUE_CODE = 103;
    private SingleSelectionAdapter mSingleSelectionAdapter;
    private String[] dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_selection);

        rvSelections = (RecyclerView) findViewById(R.id.rv_issues);

        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (null != getSupportActionBar()) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Single Selection");
        }

        dataSource = getResources().getStringArray(R.array.selections);
        if (dataSource != null) {
            mSingleSelectionAdapter = new SingleSelectionAdapter(dataSource);
            RecyclerView.LayoutManager mLayoutManagerIssues = new LinearLayoutManager(getApplicationContext());
            rvSelections.setLayoutManager(mLayoutManagerIssues);
            rvSelections.setItemAnimator(new DefaultItemAnimator());
            rvSelections.setAdapter(mSingleSelectionAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("SELECTED_ISSUE", selectedItem);
                setResult(RESULT_OK, intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    public class SingleSelectionAdapter extends RecyclerView.Adapter<SingleSelectionAdapter.SelectionHolder> {

        private int previousSelectedPos = -1;
        String[] dataSource;

        SingleSelectionAdapter(String[] dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public SelectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.single_selection_row, parent, false);

            SelectionHolder viewHolder = new SelectionHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(SelectionHolder holder, int position) {
            holder.tv_selection.setText(dataSource[position]);
        }

        @Override
        public int getItemCount() {
            return dataSource.length;
        }

        class SelectionHolder extends RecyclerView.ViewHolder {
            View itemViewNew;
            private TextView tv_selection;

            SelectionHolder(final View itemView) {
                super(itemView);
                itemViewNew = itemView;
                tv_selection = (TextView) itemView.findViewById(R.id.tv_selection);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (previousSelectedPos != -1) {
                            View last = rvSelections.getChildAt(previousSelectedPos); // the last one clicked
                            last.findViewById(R.id.iv_checked).setVisibility(View.GONE); // kill it
                        }
                        v.findViewById(R.id.iv_checked).setVisibility(View.VISIBLE);
                        previousSelectedPos = rvSelections.getChildAdapterPosition(v); // remember the new clicked position
                        selectedItem = dataSource[previousSelectedPos];
                    }
                });
            }


        }
    }
}
