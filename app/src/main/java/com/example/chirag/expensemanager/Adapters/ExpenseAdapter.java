package com.example.chirag.expensemanager.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.chirag.expensemanager.Database.DatabaseHelper;
import com.example.chirag.expensemanager.HomeActivity;
import com.example.chirag.expensemanager.Model.Expense;
import com.example.chirag.expensemanager.R;
import com.example.chirag.expensemanager.ViewActivity;

import java.util.ArrayList;

/**
 * Created by chirag on 10-Apr-17.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private Context context;
    private ArrayList<Expense> expenseArrayList;

    public ExpenseAdapter(Context context,ArrayList<Expense> expenseArrayList){
        this.context = context;
        this.expenseArrayList = expenseArrayList;
    }
    @Override
    public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        final Expense expense = expenseArrayList.get(position);
        holder.mTvEmployeeName.setText(expense.getEmployeeName());
        holder.mTvDate.setText(expense.getDate());
        holder.mTvAmount.setText(expense.getAmount());
        holder.mTvDescription.setText(expense.getDescription());
        holder.mTvAmountType.setText(expense.getAmountType());
        holder.mRlExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra("ExpenseId", expense.getExpenseId());
                context.startActivity(intent);
            }
        });
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                databaseHelper.deleteExpense(expense);
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return expenseArrayList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mTvEmployeeName,mTvAmount,mTvDescription,mTvDate,mTvAmountType;
        private RelativeLayout mRlExpense;
        private AppCompatImageView mIvDelete;


        public ExpenseViewHolder(View itemView) {

            super(itemView);
            mTvEmployeeName = (AppCompatTextView) itemView.findViewById(R.id.tv_employee_name);
            mTvDate = (AppCompatTextView) itemView.findViewById(R.id.tv_date);
            mTvAmount = (AppCompatTextView) itemView.findViewById(R.id.tv_amount);
            mTvAmountType = (AppCompatTextView) itemView.findViewById(R.id.tv_amount_type);
            mTvDescription = (AppCompatTextView) itemView.findViewById(R.id.tv_descritption);
            mRlExpense = (RelativeLayout) itemView.findViewById(R.id.rl_expense);
            mIvDelete = (AppCompatImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
