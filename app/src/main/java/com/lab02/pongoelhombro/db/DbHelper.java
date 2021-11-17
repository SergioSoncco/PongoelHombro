package com.lab02.pongoelhombro.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATA_BASE_VERSION =1;
    private static final String DATA_BASE_NAME ="datos";
    public static final String TABLE_USERS ="users";


    public DbHelper(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USERS + "(" +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR2(54) NOT NULL," +
                "dni NUMBER(8) NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_USERS);
        onCreate(sqLiteDatabase);

    }
}
