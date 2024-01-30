package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.RoomsDTO;

import java.util.ArrayList;

public class RoomsDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public RoomsDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertRow(RoomsDTO roomsDTO){
        ContentValues val = new ContentValues();
        val.put(RoomsDTO.colName,roomsDTO.getName());
        val.put(RoomsDTO.colLocaton,roomsDTO.getLocation());
        long res = db.insert(RoomsDTO.nameTable,null,val);
        return res;
    }
    public int updateRow(RoomsDTO roomsDTO){
        String[] check = new String[]{roomsDTO.getId()+""};
        ContentValues val = new ContentValues();
        val.put(RoomsDTO.colName,roomsDTO.getName());
        val.put(RoomsDTO.colLocaton,roomsDTO.getLocation());

        int res = db.update(RoomsDTO.nameTable,val,"id = ?",check);
        return res;
    }
    public ArrayList<RoomsDTO> selectAll(){
        ArrayList<RoomsDTO> listRooms = new ArrayList<>();
        Cursor cs = db.query(RoomsDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                RoomsDTO roomsDTO = new RoomsDTO();
                roomsDTO.setId(cs.getInt(0));
                roomsDTO.setName(cs.getString(1));
                roomsDTO.setLocation(cs.getString(2));

                listRooms.add(roomsDTO);
                cs.moveToNext();
            }
        }
        return listRooms;
    }
    public RoomsDTO getDtoRoomByIdRoom(int idRoom){
        RoomsDTO roomsDTO = new RoomsDTO();
        String where = "id = ?";
        String[] whereArgs = {idRoom+""};
        Cursor cs = db.query(RoomsDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            roomsDTO.setId(cs.getInt(0));
            roomsDTO.setName(cs.getString(1));
            roomsDTO.setLocation(cs.getString(2));
        }
        return roomsDTO;
    }
}
