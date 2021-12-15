package com.lab02.pongoelhombro.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                "dosis VARCHAR2(54) NOT NULL," +
                "vacuna VARCHAR2(54) NOT NULL," +
                "fecha VARCHAR2(54) NOT NULL )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_USERS);
        onCreate(sqLiteDatabase);

    }

    public boolean addData (String _dosis, String _vacuna, String _fecha){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("dosis", _dosis);
        contentValues.put("vacuna", _vacuna);
        contentValues.put("fecha", _fecha);
        long result = db.insert(TABLE_USERS, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getListaContenidos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        return data;
    }
}
