package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.ServicesDTO;

import java.util.ArrayList;

public class ServicesDAO {
    MyDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public ServicesDAO(Context context) {
        myDbHelper = new MyDbHelper(context);
        sqLiteDatabase = myDbHelper.getWritableDatabase();
    }

    public long insertServices(ServicesDTO servicesDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", servicesDTO.getServicesName());
        contentValues.put("price", servicesDTO.getServicesPrice());
        contentValues.put("categories_id", servicesDTO.getCategoriesId());
        return sqLiteDatabase.insert("tbServices", null, contentValues);
    }

    public int updateServices(ServicesDTO servicesDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", servicesDTO.getServicesName());
        contentValues.put("price", servicesDTO.getServicesPrice());
        contentValues.put("categories_id", servicesDTO.getCategoriesId());
        return sqLiteDatabase.update("tbServices", contentValues, "id=?", new String[]{servicesDTO.getServicesId() + ""});
    }

    public int deleteServices(int servicesId) {
        return sqLiteDatabase.delete("tbServices", "id=?", new String[]{servicesId + ""});
    }

    public ArrayList<ServicesDTO> selectAll() {
        ArrayList<ServicesDTO> list = new ArrayList<>();
        Cursor cs = sqLiteDatabase.rawQuery("select * from tbServices", null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                ServicesDTO servicesDTO = new ServicesDTO();
                servicesDTO.setServicesId(cs.getInt(0));
                servicesDTO.setServicesName(cs.getString(1));
                servicesDTO.setServicesPrice(cs.getFloat(2));
                servicesDTO.setCategoriesId(cs.getInt(3));

                list.add(servicesDTO);
                cs.moveToNext();
            }
        }
        return list;
    }
    public ServicesDTO getDtoServiceByIdByService (int idService){
        ServicesDTO servicesDTO = new ServicesDTO();
        String where = "id = ?";
        String[] whereArgs = {idService+""};
        Cursor cs = sqLiteDatabase.query(ServicesDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            servicesDTO.setServicesId(cs.getInt(0));
            servicesDTO.setServicesName(cs.getString(1));
            servicesDTO.setServicesPrice(cs.getFloat(2));
            servicesDTO.setCategoriesId(cs.getInt(3));
        }
        return servicesDTO;
    }
    public ArrayList<ServicesDTO> getDtoServiceByIdByNameService (String name){
        ArrayList<ServicesDTO> list = new ArrayList<>();
        String where = "name = ?";
        String[] whereArgs = {name.trim()};
        Cursor cs = sqLiteDatabase.query(ServicesDTO.nameTable,null,where,whereArgs,null,null,null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                ServicesDTO servicesDTO = new ServicesDTO();
                servicesDTO.setServicesId(cs.getInt(0));
                servicesDTO.setServicesName(cs.getString(1));
                servicesDTO.setServicesPrice(cs.getFloat(2));
                servicesDTO.setCategoriesId(cs.getInt(3));

                list.add(servicesDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

}
