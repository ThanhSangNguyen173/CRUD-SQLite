package com.example.crudlistitem.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.crudlistitem.DTO.TBitem;
import com.example.crudlistitem.SQLiteHelper.CreateDBitem;

import java.util.ArrayList;

public class tbitemDAO {
    SQLiteDatabase database;
    CreateDBitem createDBitem;

    public tbitemDAO(Context context) {
        createDBitem = new CreateDBitem(context);
    }
    public void open(){
        database = createDBitem.getWritableDatabase();
    }
    public void close(){
        createDBitem.close();
    }
    public long AddNew(TBitem tBitem){
        ContentValues contentValues= new ContentValues();
        contentValues.put(TBitem.COL_NAME_SEASON, tBitem.getSeason());
        contentValues.put(TBitem.COL_NAME_TEN_ITEM, tBitem.getTen_item());
        long res = database.insert(TBitem.TB_NAME,null,contentValues);
        return  res;
    }
    public ArrayList<TBitem> GetAll() {
        ArrayList<TBitem> dsItem = new ArrayList<TBitem>();
        String[] danh_sach_cot_lay_du_lieu =
                new String[]{TBitem.COL_NAME_ID, TBitem.COL_NAME_TEN_ITEM, TBitem.COL_NAME_SEASON};
        Cursor cursor = database.query(TBitem.TB_NAME, danh_sach_cot_lay_du_lieu,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String ten_item = cursor.getString(1);
            String season = cursor.getString(2);
            TBitem tBitem = new TBitem();
            tBitem.setId_item(id);
            tBitem.setTen_item(ten_item);
            tBitem.setSeason(season);
            dsItem.add(tBitem);
            Log.d("DAO--","get row"+ten_item);
            cursor.moveToNext();
        }
        return dsItem;
    }
    public int Update(TBitem tBitem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TBitem.COL_NAME_TEN_ITEM,tBitem.getTen_item());
        contentValues.put(TBitem.COL_NAME_SEASON,tBitem.getSeason());

        int res = database.update(TBitem.TB_NAME,contentValues,"id_item = " +tBitem.getId_item(),null);

        return res;
    }
    public int Delete (TBitem tBitem){
        return database.delete(TBitem.TB_NAME,"id_item = "+tBitem.getId_item(),null);
    }
}
