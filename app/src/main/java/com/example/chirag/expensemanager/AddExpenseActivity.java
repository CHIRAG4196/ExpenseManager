package com.example.chirag.expensemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.DatePicker;

import com.example.chirag.expensemanager.Database.DatabaseHelper;
import com.example.chirag.expensemanager.Model.Expense;

import java.util.ArrayList;

public class AddExpenseActivity extends AppCompatActivity {

    private AppCompatEditText mEtEmployeeName,mEtDate,mEtAmount,mEtDescription;
    private AppCompatSpinner mSpAmountType;
    private AppCompatButton mBtSave,mBtDate,mBtSaveContinue,mBtUpdate;
    private DatabaseHelper databaseHelper;
    private ArrayList<Expense> expenseArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        mEtEmployeeName = (AppCompatEditText) findViewById(R.id.et_employee_name);
        mEtDate = (AppCompatEditText) findViewById(R.id.et_date);
        mEtAmount = (AppCompatEditText) findViewById(R.id.et_amount);
        mEtDescription = (AppCompatEditText) findViewById(R.id.et_description);
        mSpAmountType = (AppCompatSpinner) findViewById(R.id.sp_amount_type);
        mBtDate = (AppCompatButton) findViewById(R.id.bt_date);
        mBtUpdate = (AppCompatButton) findViewById(R.id.bt_update);
        mBtSave = (AppCompatButton) findViewById(R.id.bt_save);
        mBtSaveContinue = (AppCompatButton) findViewById(R.id.bt_save_continue);
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        mBtUpdate.setVisibility(View.GONE);
        mBtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        showDate(year, month, dayOfMonth);
                    }

                }, 2017, 2, 23);
                datePickerDialog.show();

            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("ID",1);
            if (id == 1) {

                mBtSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amountType = mSpAmountType.getSelectedItem().toString();
                        Expense expense = new Expense();
                        expense.setEmployeeName(mEtEmployeeName.getText().toString());
                        expense.setDate(mEtDate.getText().toString());
                        expense.setAmount(mEtAmount.getText().toString());
                        expense.setAmountType(amountType);
                        expense.setDescription(mEtDescription.getText().toString());
                        databaseHelper.inserData(expense);
                        Intent intent = new Intent(AddExpenseActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

                mBtSaveContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amountType = mSpAmountType.getSelectedItem().toString();
                        Expense expense = new Expense();
                        expense.setEmployeeName(mEtEmployeeName.getText().toString());
                        expense.setDate(mEtDate.getText().toString());
                        expense.setAmount(mEtAmount.getText().toString());
                        expense.setAmountType(amountType);
                        expense.setDescription(mEtDescription.getText().toString());
                        databaseHelper.inserData(expense);
                        Intent intent = new Intent(AddExpenseActivity.this, AddExpenseActivity.class);
                        startActivity(intent);
                    }
                });
            }
            int id2 = intent.getIntExtra("ID",2);
            if (id2 == 2){
                mBtUpdate.setVisibility(View.VISIBLE);
                Expense expense = getIntent().getParcelableExtra("Expense");
                mEtAmount.setText(expense.getAmount());
                mEtEmployeeName.setText(expense.getEmployeeName());
                mEtDate.setText(expense.getDate());
                mEtDescription.setText(expense.getDescription());

                mBtUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amountType = mSpAmountType.getSelectedItem().toString();
                        Expense expense = new Expense();
                        expense.setEmployeeName(mEtEmployeeName.getText().toString());
                        expense.setDate(mEtDate.getText().toString());
                        expense.setAmount(mEtAmount.getText().toString());
                        expense.setAmountType(amountType);
                        expense.setDescription(mEtDescription.getText().toString());
                        expense.setExpenseId(expense.getExpenseId());
                        databaseHelper.updateExpense(expense);
                        Intent intent1 = new Intent(AddExpenseActivity.this,HomeActivity.class);
                        startActivity(intent1);
                    }
                });
                mBtSaveContinue.setVisibility(View.GONE);
                mBtSave.setVisibility(View.GONE);

            }
        }

    }
    private void showDate(int year, int month, int dayOfMonth) {
        mEtDate.setText(new StringBuilder().append(dayOfMonth).append("/")
                .append(month+1).append("/").append(year));
    }
}
