package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.TimeWorkDTO;
import com.example.datlichkham.dto.TimeWorkDetailDTO;

import java.util.ArrayList;

public class TimeWorkDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbhelper;

    public TimeWorkDetailDAO(Context context){
        dbhelper = new MyDbHelper(context);
    }
    public void open(){
        db = dbhelper.getWritableDatabase();
    }

    public long insertRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(TimeWorkDetailDTO.colTime,dtoTimeWorkDetail.getTime());
        val.put(TimeWorkDetailDTO.colTimework_id,dtoTimeWorkDetail.getTimework_id());

        long res  =db.insert(TimeWorkDetailDTO.nameTable,null,val);
        return res;
    }
    public int deleteRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};
        int res = db.delete(TimeWorkDetailDTO.nameTable,"id = ?",check);
        return res;
    }
    public int updateRow(TimeWorkDetailDTO dtoTimeWorkDetail){
        ContentValues val = new ContentValues();
        val.put(TimeWorkDetailDTO.colTime,dtoTimeWorkDetail.getTime());
        val.put(TimeWorkDetailDTO.colTimework_id,dtoTimeWorkDetail.getTimework_id());
        String[] check = new String[]{dtoTimeWorkDetail.getId()+""};

        int res = db.update(TimeWorkDetailDTO.nameTable,val,"id = ?",check);
        return res;
    }

    public ArrayList<TimeWorkDetailDTO> selectAll(){
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO dtoTimeWorkDetail = new TimeWorkDetailDTO();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

    public ArrayList<TimeWorkDetailDTO> selectTimeWorkDetailByTimeWorkId(int idTimeWork){
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
        String where = "timework_id = ?";
        String[] whereArgs = new String[]{idTimeWork+""};
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO dtoTimeWorkDetail = new TimeWorkDetailDTO();
                dtoTimeWorkDetail.setId(cs.getInt(0));
                dtoTimeWorkDetail.setTimework_id(cs.getInt(1));
                dtoTimeWorkDetail.setTime(cs.getString(2));

                listTimeWorkDetail.add(dtoTimeWorkDetail);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }
    public ArrayList<TimeWorkDetailDTO> listTimeWorkDetailByStartDate(String startDate , int idDoctor){
        ArrayList<TimeWorkDetailDTO> listTimeWorkDetail = new ArrayList<>();
        String[] check = {startDate.trim(),idDoctor+""};
        String select = "select tbOrderDoctor.start_time  from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id where tbOrderDoctor.start_date = ? and tbDoctor.id = ?";
        Cursor cs = db.rawQuery(select,check);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDetailDTO timeWorkDetailDTO = new TimeWorkDetailDTO();
                timeWorkDetailDTO.setTime(cs.getString(0));
                listTimeWorkDetail.add(timeWorkDetailDTO);
                cs.moveToNext();
            }
        }
        return listTimeWorkDetail;
    }

   public ArrayList<TimeWorkDTO> listTimeWork(String time){
        ArrayList<TimeWorkDTO> list = new ArrayList<>();
        String[] whereArgs = {time};
        String select = "select  tbTimeWork.id  from tbTimeWorkDetail inner join tbTimeWork on tbTimeWorkDetail.timework_id = tbTimeWork.id where time = ?";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                TimeWorkDTO timeWorkDTO = new TimeWorkDTO();
                timeWorkDTO.setId(cs.getInt(0));
                list.add(timeWorkDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
   }

    public TimeWorkDetailDTO getTimeWorkDetail (String timeWorkDetail){
        TimeWorkDetailDTO timeWorkDetailDTO  = new TimeWorkDetailDTO();
        String where = "time = ?";
        String[] whereArgs = new String[]{timeWorkDetail.trim()};
        Cursor cs = db.query(TimeWorkDetailDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            timeWorkDetailDTO.setId(cs.getInt(0));
            timeWorkDetailDTO.setTimework_id(cs.getInt(1));
            timeWorkDetailDTO.setTime(cs.getString(2));
        }
        return timeWorkDetailDTO;
    }

}
