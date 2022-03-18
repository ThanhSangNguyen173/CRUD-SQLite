package com.example.crudlistitem.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateDBitem extends SQLiteOpenHelper {
    public static final String DB_NAME = "CRUDListItem.db";
    public static final int DB_VERSION = 1;

    public CreateDBitem(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_cr_tb_item="CREATE TABLE tb_item(id_item INTEGER NOT NULL,ten_item TEXT NOT NULL,season TEXT, PRIMARY KEY(id_item AUTOINCREMENT))";
        sqLiteDatabase.execSQL(sql_cr_tb_item);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
