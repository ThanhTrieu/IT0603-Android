package com.example.campusexpense.DatabaseSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.campusexpense.Model.Account;


public class AccountDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "asm_expense";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "account";
    public static final String ID_COL = "id";
    public static final String USERNAME_COL = "username";
    public static final String PASSWORD_COL = "password";
    public static final String EMAIL_COL = "email";
    public static final String PHONE_COL = "phone";
    public AccountDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang du lieu
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                        + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + USERNAME_COL + " VARCHAR(60), "
                        + PASSWORD_COL + " VARCHAR(200), "
                        + EMAIL_COL + " VARCHAR(60), "
                        + PHONE_COL + " VARCHAR(30))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long addNewAccount(String username, String password, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        return db.insert(TABLE_NAME, null, values);
        // tra ve -1 : insert khong thanh cong
    }
    public Account getInfoUser(String username, String password){
        Cursor cursor = null;
        Account account = new Account();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] columns = { ID_COL, USERNAME_COL, EMAIL_COL, PHONE_COL };
            String conditions = USERNAME_COL + " =? " + " AND " + PASSWORD_COL + " =? ";
            String[] params = { username, password };
            cursor = db.query(
                    TABLE_NAME,
                    columns,
                    conditions,
                    params,
                    null,
                    null,
                    null
            );
            // select id, username, email, phone from account where username = ? AND password = ?;
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                // do du lieu vao model Account
                account.setId(cursor.getInt(cursor.getColumnIndex(ID_COL)));
                account.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME_COL)));
                account.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                account.setPhone(cursor.getString(cursor.getColumnIndex(PHONE_COL)));
            }
            db.close();
        } finally {
            assert cursor != null;
            cursor.close();
        }
        return account;
    }
}
