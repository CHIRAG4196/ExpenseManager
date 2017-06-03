package com.example.chirag.expensemanager.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chirag on 10-Apr-17.
 */

public class Expense implements Parcelable {
    int expenseId;
    String employeeName;
    String date,amountType,amount,description;


    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.expenseId);
        dest.writeString(this.employeeName);
        dest.writeString(this.date);
        dest.writeString(this.amountType);
        dest.writeString(this.amount);
        dest.writeString(this.description);
    }

    public Expense() {
    }

    protected Expense(Parcel in) {
        this.expenseId = in.readInt();
        this.employeeName = in.readString();
        this.date = in.readString();
        this.amountType = in.readString();
        this.amount = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Expense> CREATOR = new Parcelable.Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel source) {
            return new Expense(source);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };
}
