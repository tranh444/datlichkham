package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.OrderDoctorDTO;

import java.util.ArrayList;

public class OrderDoctorDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDoctorDAO(Context context){
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertRow(OrderDoctorDTO orderDoctorDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDoctorDTO.colFileId,orderDoctorDTO.getFile_id());
        val.put(OrderDoctorDTO.colDoctorId,orderDoctorDTO.getDoctor_id());
        val.put(OrderDoctorDTO.colStartTime,orderDoctorDTO.getStart_time());
        val.put(OrderDoctorDTO.colStartDate,orderDoctorDTO.getStart_date());
        val.put(OrderDoctorDTO.colTotal,orderDoctorDTO.getTotal());
        long res =db.insert(OrderDoctorDTO.nameTable,null,val);
        return res;
    }
    public int updateRow(OrderDoctorDTO orderDoctorDTO){
        ContentValues val = new ContentValues();
        val.put(OrderDoctorDTO.colFileId,orderDoctorDTO.getFile_id());
        val.put(OrderDoctorDTO.colDoctorId,orderDoctorDTO.getDoctor_id());
        val.put(OrderDoctorDTO.colStartTime,orderDoctorDTO.getStart_time());
        val.put(OrderDoctorDTO.colStartDate,orderDoctorDTO.getStart_date());
        val.put(OrderDoctorDTO.colTotal,orderDoctorDTO.getTotal());
        int res = db.update(OrderDoctorDTO.nameTable,val,"id=?",new String[]{orderDoctorDTO.getId()+""});
        return res;
    }

    public int deleteRow(OrderDoctorDTO orderDoctorDTO){
        String[] check = new String[]{orderDoctorDTO.getId()+""};
        int res = db.delete(OrderDoctorDTO.nameTable,"id = ?",check);
        return res;
    }
    public ArrayList<OrderDoctorDTO> selectAll(){
        ArrayList<OrderDoctorDTO> list = new ArrayList<>();
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,null,null,null,null,null);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setId(cs.getInt(0));
                orderDoctorDTO.setFile_id(cs.getInt(1));
                orderDoctorDTO.setDoctor_id(cs.getInt(2));
                orderDoctorDTO.setStart_time(cs.getString(3));
                orderDoctorDTO.setStart_date(cs.getString(4));
                orderDoctorDTO.setTotal(cs.getFloat(5));

                list.add(orderDoctorDTO);
                cs.moveToNext();
            }
        }
        return list;
    }
    public OrderDoctorDTO getOrderDoctorDtoDesc(){
        OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
        String orderBy = "id desc";
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,null,null,null,null,orderBy);
        if(cs.moveToFirst()){
            orderDoctorDTO.setId(cs.getInt(0));
            orderDoctorDTO.setFile_id(cs.getInt(1));
            orderDoctorDTO.setDoctor_id(cs.getInt(2));
            orderDoctorDTO.setStart_time(cs.getString(3));
            orderDoctorDTO.setStart_date(cs.getString(4));
            orderDoctorDTO.setTotal(cs.getFloat(5));
        }
        return orderDoctorDTO;
    }

    public OrderDoctorDTO getOrderDoctorDtoById(int idOrderDoctor){
        OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
        String where = "id = ?";
        String[] whereArgs = {idOrderDoctor+""};
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            orderDoctorDTO.setId(cs.getInt(0));
            orderDoctorDTO.setFile_id(cs.getInt(1));
            orderDoctorDTO.setDoctor_id(cs.getInt(2));
            orderDoctorDTO.setStart_time(cs.getString(3));
            orderDoctorDTO.setStart_date(cs.getString(4));
            orderDoctorDTO.setTotal(cs.getFloat(5));
        }
        return orderDoctorDTO;
    }

    public OrderDoctorDTO getOrderDoctorDtoById1(int idOrderDoctor){
        OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
        String where = "doctor_id = ?";
        String[] whereArgs = {idOrderDoctor+""};
        Cursor cs = db.query(OrderDoctorDTO.nameTable,null,where,whereArgs,null,null,null);
        if(cs.moveToFirst()){
            orderDoctorDTO.setId(cs.getInt(0));
            orderDoctorDTO.setFile_id(cs.getInt(1));
            orderDoctorDTO.setDoctor_id(cs.getInt(2));
            orderDoctorDTO.setStart_time(cs.getString(3));
            orderDoctorDTO.setStart_date(cs.getString(4));
            orderDoctorDTO.setTotal(cs.getFloat(5));
        }
        return orderDoctorDTO;
    }
    public ArrayList<OrderDoctorDTO> listOrderDoctorByDateToDayByDoctor(String dateToDay,int idDoctor){
        ArrayList<OrderDoctorDTO> list = new ArrayList<>();
        String[] whereArgs = {dateToDay.trim(),idDoctor+""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id where tbOrders.status ='Chờ ngày khám' and tbOrderDoctor.start_date = ? and tbOrderDoctor.doctor_id =?";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setOrder_id(cs.getInt(0));
                orderDoctorDTO.setId(cs.getInt(1));

                list.add(orderDoctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }

    public ArrayList<OrderDoctorDTO> listOrderDoctorByDateToDayByDoctorAllNoConfirm(int idDoctor){
        ArrayList<OrderDoctorDTO> list = new ArrayList<>();
        String[] whereArgs = {idDoctor+""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id where tbOrders.status ='Chờ ngày khám'and tbOrderDoctor.doctor_id =? order by tbOrderDoctor.start_date";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setOrder_id(cs.getInt(0));
                orderDoctorDTO.setId(cs.getInt(1));

                list.add(orderDoctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }
    public ArrayList<OrderDoctorDTO> listOrderDoctorByDateToDayByDoctorAllYesConfirm(int idDoctor){
        ArrayList<OrderDoctorDTO> list = new ArrayList<>();
        String[] whereArgs = {idDoctor+""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id where tbOrders.status ='Đã khám xong'and tbOrderDoctor.doctor_id =? order by tbOrderDoctor.start_date";
        Cursor cs = db.rawQuery(select,whereArgs);
        if(cs.moveToFirst()){
            while(!cs.isAfterLast()){
                OrderDoctorDTO orderDoctorDTO = new OrderDoctorDTO();
                orderDoctorDTO.setOrder_id(cs.getInt(0));
                orderDoctorDTO.setId(cs.getInt(1));

                list.add(orderDoctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }
}
