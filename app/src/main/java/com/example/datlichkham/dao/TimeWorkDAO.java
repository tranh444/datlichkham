package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.TimeWorkDTO;

import java.util.ArrayList;

public class TimeWorkDAO {
    SQLiteDatabase db;
    MyDbHelper myDbHelper;
    public TimeWorkDAO(Context context){
        myDbHelper = new MyDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public void open(){
        db = myDbHelper.getWritableDatabase();
    }
    public long insertRow(TimeWorkDTO obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("session", obj.getSession());
        return db.insert("tbTimeWork", null, contentValues);

    }
    public int updateRow(TimeWorkDTO obj ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("session", obj.getSession());
        return db.update("tbTimeWork", contentValues, "id=?", new String[]{obj.getId()+""});
    }
    public int deleteRow(TimeWorkDTO obj){
        return db.delete("tbTimeWork", "id=?", new String[]{obj.getId()+""});
    }
    public ArrayList<TimeWorkDTO> getAll(){
        ArrayList<TimeWorkDTO> list = new ArrayList<>();
        String select = "select * from tbTimeWork";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                TimeWorkDTO obj = new TimeWorkDTO();
                obj.setId(cursor.getInt(0));
                obj.setSession(cursor.getString(1));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public TimeWorkDTO getDtoTimeWork(int idTimeWork){
        TimeWorkDTO _timeWorkDTO =new TimeWorkDTO();
        String where = "id = ?";
        String[] whereArgs = {idTimeWork+""};
        Cursor cs = db.query(TimeWorkDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            _timeWorkDTO.setId(cs.getInt(0));
            _timeWorkDTO.setSession(cs.getString(1));
        }
        return _timeWorkDTO;
    }
}
