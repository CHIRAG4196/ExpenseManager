package com.example.chirag.expensemanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chirag.expensemanager.Model.Expense;

import java.util.ArrayList;

/**
 * Created by chirag on 10-Apr-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_EXPANSE = "expanse";
    public static final String COLUMN_ID = "expanse_id";
    public static final String COLUMN_NAME = "expanse_name";
    public static final String COLUMN_DATE = "expanse_date";
    public static final String COLUMN_MODE = "expanse_mode";
    public static final String COLUMN_DESCRIPTION = "expanse_description";
    public static final String COLUMN_RUPEES = "expanse_rupees";


    public static final String CREATE_TABLE = "" +
            "create table " + TABLE_EXPANSE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_MODE + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_RUPEES + " TEXT, " +
            COLUMN_DATE + " TEXT)";

    private static final String DATABASE_NAME = "expanse.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.e("TEST", "SQL : " + CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPANSE);

        onCreate(db);

    }

    public void inserData(Expense expanse) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, expanse.getEmployeeName());
        contentValues.put(COLUMN_DATE, expanse.getDate());
        contentValues.put(COLUMN_DESCRIPTION, expanse.getDescription());
        contentValues.put(COLUMN_MODE, expanse.getAmountType());
        contentValues.put(COLUMN_RUPEES, expanse.getAmount());
        database.insert(TABLE_EXPANSE, null, contentValues);
        database.close();
    }

    public ArrayList<Expense> getAllData() {
        ArrayList<Expense> expanseArrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_EXPANSE, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            Expense expanse = new Expense();
            expanse.setExpenseId(cursor.getInt(0));
            expanse.setEmployeeName(cursor.getString(1));
            expanse.setDate(cursor.getString(5));
            expanse.setAmountType(cursor.getString(2));
            expanse.setAmount(cursor.getString(4));
            expanse.setDescription(cursor.getString(3));
            expanseArrayList.add(expanse);
        } while (cursor.moveToNext());
        cursor.close();
        database.close();
        return expanseArrayList;
    }

    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, TABLE_EXPANSE);
        db.close();
        return cnt;
    }

    public Expense getExpensebyId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery
                ("select * from " + TABLE_EXPANSE + " where " + COLUMN_ID + " =" + id, null);
        if (cursor != null)
            cursor.moveToFirst();

        Expense expanse = new Expense();
        expanse.setExpenseId(cursor.getInt(0));
        expanse.setEmployeeName(cursor.getString(1));
        expanse.setDate(cursor.getString(5));
        expanse.setAmountType(cursor.getString(2));
        expanse.setAmount(cursor.getString(4));
        expanse.setDescription(cursor.getString(3));

        cursor.close();
        return expanse;

    }
    public void updateExpense(Expense expanse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, expanse.getEmployeeName());
        values.put(COLUMN_DATE, expanse.getDate());
        values.put(COLUMN_RUPEES, expanse.getAmount());
        values.put(COLUMN_MODE, expanse.getAmountType());
        values.put(COLUMN_DESCRIPTION, expanse.getDescription());

        // updating row
        db.update(TABLE_EXPANSE, values, COLUMN_ID + " = ?", new String[]{String.valueOf(expanse.getExpenseId())});

        db.close();
    }
    public void deleteExpense(Expense expense){
        SQLiteDatabase database = this.getWritableDatabase();
        database.rawQuery("delete from " + TABLE_EXPANSE + " where " + COLUMN_ID + " ="+expense.getExpenseId(),null);
        database.close();
    }

}