package com.example.datlichkham.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datlichkham.database.MyDbHelper;
import com.example.datlichkham.dto.AllDTO;
import com.example.datlichkham.dto.DoctorDTO;
import com.example.datlichkham.dto.OrderDetailDTO;
import com.example.datlichkham.dto.StatisticalDTO;

import java.util.ArrayList;

public class OrderDetailDAO {
    SQLiteDatabase db;
    MyDbHelper dbHelper;

    public OrderDetailDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long innsertRow(OrderDetailDTO orderDetailDTO) {
        ContentValues val = new ContentValues();
        val.put(OrderDetailDTO.colOrderId, orderDetailDTO.getOrder_id());
        val.put(OrderDetailDTO.colOrderDoctorId, orderDetailDTO.getOrderDoctor_id());

        long res = db.insert(OrderDetailDTO.nameTable, null, val);
        return res;
    }
    public OrderDetailDTO getOrderDetialDto(int id){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        String where = "order_id = ?";
        String[] whereArgs = {id+""};
        Cursor cs = db.query(OrderDetailDTO.nameTable,null,where,whereArgs,null,null,null,null);
        if(cs.moveToFirst()){
            orderDetailDTO.setOrder_id(cs.getInt(0));
            orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
        }
        return orderDetailDTO;
    }

    public ArrayList<OrderDetailDTO> selectAll() {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        Cursor cs = db.query(OrderDetailDTO.nameTable, null, null, null, null, null, null);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));

                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoById(int idUser) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {idUser + ""};
        String select = "select tbOrderDetail.order_id ,tbOrderDetail.orderDoctor_id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbFile.user_id = tbAccount.id where tbAccount.id =?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoByYesConfirm(int idUser) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {idUser + ""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrders.status ='Đã khám xong' and  tbAccount.id = ?";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }

        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderDetailDtoByNoConfirm(int idUser) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {idUser + ""};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrders.status ='Chờ ngày khám' and  tbAccount.id = ? order by tbOrderDoctor.start_date";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListOrderToDay(String today) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {today.trim()};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrders.order_date = ? and tbOrders.status = 'Chờ ngày khám'";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public ArrayList<OrderDetailDTO> getListExaminationToDay(String today) {
        ArrayList<OrderDetailDTO> list = new ArrayList<>();
        String[] whereArgs = {today.trim()};
        String select = "select tbOrders.id,tbOrderDoctor.id from tbOrderDetail inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbOrders on tbOrders.id = tbOrderDetail.order_id inner join tbFile on tbFile.id = tbOrders.file_id inner join tbAccount on tbAccount.id = tbFile.user_id where tbOrderDoctor.start_date = ? and tbOrders.status = 'Chờ ngày khám'";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrder_id(cs.getInt(0));
                orderDetailDTO.setOrderDoctor_id(cs.getInt(1));
                list.add(orderDetailDTO);
                cs.moveToNext();
            }
        }
        return list;
    }

    public StatisticalDTO getStatisticalDTOByStartToDay(String toDay) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {toDay.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.start_date = ? ";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByOrderToDay(String OrderToDay) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {OrderToDay.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrders.order_date = ? ";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByToMonth(String month1, String month2) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {month1.trim(), month2.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.start_date > ?  and tbOrderDoctor.start_date <? ";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public StatisticalDTO getStatisticalDTOByOrderMonth(String month1, String month2) {
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        String[] whereArgs = {month1.trim(), month2.trim()};
        String select = "select count(tbOrders.id),sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrders.order_date > ?  and tbOrders.order_date <? ";
        Cursor cs = db.rawQuery(select, whereArgs);
        if (cs.moveToFirst()) {
            statisticalDTO.setNumberOrder(cs.getInt(0));
            statisticalDTO.setSumPrice(cs.getFloat(1));
        }
        return statisticalDTO;
    }

    public ArrayList<DoctorDTO> getListSumPriceByOrderMonth(String month1, String month2) {
        ArrayList<DoctorDTO> list = new ArrayList<>();
        String[] whereArds = {month1.trim(), month2.trim()};
        String select="select tbOrderDoctor.doctor_id ,sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id inner join tbDoctor on tbOrderDoctor.doctor_id = tbDoctor.id   where tbOrderDoctor.start_date >=? and  tbOrderDoctor.start_date<= ?  group by tbDoctor.id order by sum(tbOrderDoctor.total) desc";
        Cursor cs = db.rawQuery(select, whereArds);
        if (cs.moveToFirst()) {
            while (!cs.isAfterLast()) {
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(cs.getInt(0));
                doctorDTO.setSumPrice(cs.getFloat(1));

                list.add(doctorDTO);
                cs.moveToNext();
            }
        }
        cs.close();
        return list;
    }

    public ArrayList<AllDTO> getListPriceByDayDoctor(String date, int id) {
        ArrayList<AllDTO> list = new ArrayList<>();
        String select = "select tbDoctor.id,tbOrderDoctor.start_time,tbOrderDoctor.total from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                "inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id \n" +
                "where tbOrderDoctor.doctor_id= "+id+" and tbOrderDoctor.start_date ='"+date+"' and tbOrders.status ='Đã khám xong'";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AllDTO obj = new AllDTO();
                obj.setIdDoctor(cursor.getInt(0));
                obj.setTime(cursor.getString(1));
                obj.setTotal(cursor.getFloat(2));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public ArrayList<AllDTO> getListPriceByMonthDoctor(String startDate, String endDate, int id) {
        ArrayList<AllDTO> list = new ArrayList<>();
        String select = "select tbDoctor.id,tbOrderDoctor.start_date,sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                "inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id \n" +
                "where tbOrderDoctor.doctor_id = "+id+" and tbOrderDoctor.start_date>= '"+startDate+"' and tbOrderDoctor.start_date<= '"+endDate+"' and tbOrders.status ='Đã khám xong' group by tbOrderDoctor.start_date having sum(tbOrderDoctor.total)";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AllDTO obj = new AllDTO();
                obj.setIdDoctor(cursor.getInt(0));
                obj.setDay(cursor.getString(1));
                obj.setTotal(cursor.getFloat(2));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public double getSumPriceByMonthDoctor(String startDate, String endDate, int id) {
        AllDTO obj = new AllDTO();
        String select = "select sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                " inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id join tbServices on tbDoctor.service_id=tbServices.id\n" +
                "  where tbOrderDoctor.doctor_id= " + id + " and tbOrderDoctor.start_date>= '" + startDate + "' and tbOrderDoctor.start_date<= '" + endDate + " ' and tbOrders.status ='Đã khám xong' ";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                obj.setTotal(cursor.getFloat(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return obj.getTotal();
    }

    public double getSumPriceByDayDoctor(String startDate, int id) {
        AllDTO obj = new AllDTO();
        String select = "select sum(tbOrderDoctor.total) from tbOrderDetail inner join tbOrders on tbOrderDetail.order_id = tbOrders.id\n" +
                " inner join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id join tbDoctor on tbDoctor.id = tbOrderDoctor.doctor_id join tbServices on tbDoctor.service_id=tbServices.id\n" +
                "  where tbOrderDoctor.doctor_id= " + id + " and tbOrderDoctor.start_date= '" + startDate + "' and tbOrders.status ='Đã khám xong'";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                obj.setTotal(cursor.getFloat(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return obj.getTotal();
    }
    public ArrayList<AllDTO> getListOrderByIdFile(int id){
        ArrayList<AllDTO> list = new ArrayList<>();
        String select ="select tbFile.fullname, tbOrderDoctor.doctor_id,tbOrderDoctor.start_date,tbOrderDoctor.start_time,tbOrderDetail.orderDoctor_id,tbOrderDetail.order_id  from tbOrders join tbFile on tbOrders.file_id=tbFile.id join tbOrderDetail on tbOrders.id=tbOrderDetail.order_id join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.file_id= "+id+" and tbOrders.status='Chờ ngày khám' ";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                AllDTO obj = new AllDTO();
                obj.setFullameUser(cursor.getString(0));
                obj.setIdDoctor(cursor.getInt(1));
                obj.setStartDate(cursor.getString(2));
                obj.setStartTime(cursor.getString(3));
                obj.setOrderDoctor_id(cursor.getInt(4));
                obj.setOrder_id(cursor.getInt(5));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }
    public ArrayList<AllDTO> getListOrderByIdFileYesComfrim(int id){
        ArrayList<AllDTO> list = new ArrayList<>();
        String select ="select tbFile.fullname, tbOrderDoctor.doctor_id,tbOrderDoctor.start_date,tbOrderDoctor.start_time, tbOrders.note from tbOrders join tbFile on tbOrders.file_id=tbFile.id join tbOrderDetail on tbOrders.id=tbOrderDetail.order_id join tbOrderDoctor on tbOrderDetail.orderDoctor_id = tbOrderDoctor.id where tbOrderDoctor.file_id= "+id+" and tbOrders.status='Đã khám xong' ";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                AllDTO obj = new AllDTO();
                obj.setFullameUser(cursor.getString(0));
                obj.setIdDoctor(cursor.getInt(1));
                obj.setStartDate(cursor.getString(2));
                obj.setStartTime(cursor.getString(3));
                obj.setNote(cursor.getString(4));
                list.add(obj);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

}
