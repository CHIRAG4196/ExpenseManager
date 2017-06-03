package com.example.chirag.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chirag.expensemanager.Database.DatabaseHelper;
import com.example.chirag.expensemanager.Model.Expense;

public class ViewActivity extends AppCompatActivity {
    private AppCompatTextView mTvEmployeeName,mTvAmount,mTvDescription,mTvDate,mTvAmountType;
    private int id;
    private Expense expense;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_view_toolbar);
        setSupportActionBar(toolbar);
        mTvEmployeeName = (AppCompatTextView) findViewById(R.id.tv_employee_name);
        mTvDate = (AppCompatTextView) findViewById(R.id.tv_date);
        mTvAmount = (AppCompatTextView) findViewById(R.id.tv_amount);
        mTvAmountType = (AppCompatTextView) findViewById(R.id.tv_amount_type);
        mTvDescription = (AppCompatTextView) findViewById(R.id.tv_descritption);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getExtras().getInt("ExpenseId");
        }
        expense = databaseHelper.getExpensebyId(id);
        mTvEmployeeName.setText(expense.getEmployeeName());
        mTvDate.setText(expense.getDate());
        mTvAmountType.setText(expense.getAmountType());
        mTvAmount.setText(expense.getAmount());
        mTvDescription.setText(expense.getDescription());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.ic_edit:
                Intent intent = new Intent(ViewActivity.this,AddExpenseActivity.class);
                intent.putExtra("Expense",expense);
                intent.putExtra("ID",2);
                intent.putExtra("ExpenseId",expense.getExpenseId());
                startActivity(intent);
                Toast.makeText(this, ""+expense.getExpenseId(), Toast.LENGTH_SHORT)
                        .show();

        }
        return super.onOptionsItemSelected(item);
    }
}
