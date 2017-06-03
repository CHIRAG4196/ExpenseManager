package com.example.chirag.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chirag.expensemanager.Adapters.ExpenseAdapter;
import com.example.chirag.expensemanager.Database.DatabaseHelper;
import com.example.chirag.expensemanager.Model.Expense;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRvExpense;
    ArrayList<Expense> expenseArrayList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private TextView mTvNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        mTvNoData = (TextView) findViewById(R.id.tv_no_data);
        mRvExpense = (RecyclerView) findViewById(R.id.rv_expense);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvExpense.setLayoutManager(layoutManager);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        int profile_counts = (int) databaseHelper.getProfilesCount();
        Toast.makeText(this,"" +profile_counts, Toast.LENGTH_SHORT).show();
        if (profile_counts == 0) {
            mTvNoData.setVisibility(View.VISIBLE);
        } else {
            mTvNoData.setVisibility(View.GONE);
            expenseArrayList = databaseHelper.getAllData();
            ExpenseAdapter expenseAdapter = new ExpenseAdapter(HomeActivity.this,expenseArrayList);
            mRvExpense.setAdapter(expenseAdapter);
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
          }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.ic_plus:
                Intent intent = new Intent(HomeActivity.this,AddExpenseActivity.class);
                intent.putExtra("ID",1);
                startActivity(intent);
                Toast.makeText(this, "Plus selected", Toast.LENGTH_SHORT)
                        .show();

        }
        return super.onOptionsItemSelected(item);
    }
}
