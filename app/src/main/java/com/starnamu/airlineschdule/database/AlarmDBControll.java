package com.starnamu.airlineschdule.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-07-20.
 */
public class AlarmDBControll implements CommonConventions {

    private String AlarmTableName = "AlarmTableName";
    private ArrayList<AirlineItem> AlarmItems;
    private final AlarmOpenHelper opener;

    public AlarmDBControll() {
        this.opener = AlarmOpenHelper.getNewInstance();
        this.AlarmItems = new ArrayList<>();
        init();
    }

    public void setDataTable(AirlineItem item) {
        insertData(item);
    }

    public void setDataTable(ArrayList<AirlineItem> items) {
        for (int i = 0; i < items.size(); i++) {
            AirlineItem AlarmItem = items.get(i);
            insertData(AlarmItem);
        }
    }

    private void init() {
        //테이블의 모든 Data를 삭제한다.
//        allRemoveData(AlarmTableName);
    }

    // 데이터 추가
    private void insertData(AirlineItem AlarmItem) {
        SQLiteDatabase db = opener.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int j = 0; j < PARSERITEMGROUP.length; j++) {
            values.put(PARSERITEMGROUP[j], AlarmItem.getStriItem(j));
        }
        db.insert(AlarmTableName, null, values);
        db.close();
    }

    // 데이터 전체 삭제
    public void allRemoveData(String tableName) {
        SQLiteDatabase db = opener.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    /*public void removeData(int position) {
        SQLiteDatabase db = opener.getReadableDatabase();

        db.delete(AlarmTableName, "_id" + " = " + position, null);
        Log.i("AlarmDBControll", position + "RemoveData Sucess");
        db.close();
    }*/

    public void removeData(int position) {
        String str = "'" + AlarmItems.get(position).getStriItem(3) + "'";
        SQLiteDatabase db = opener.getReadableDatabase();
        String sql = "delete from " + AlarmTableName + " where flightId = " + str + ";";
        db.execSQL(sql);

        Log.i("Database", "delete data");
    }

    public void removeData(String FlightId) {
        SQLiteDatabase db = opener.getReadableDatabase();
        String sql = "delete from " + AlarmTableName + " where flightId = " + FlightId + ";";
        db.execSQL(sql);

        Log.i("Database", "delete data");
    }


    /*DB에 있는 시간을 업데이트 하는중*/
/*
    public void updateData(String FlightId) {
        removeData(FlightId);
        SQLiteDatabase db = opener.getWritableDatabase();

        ContentValues values = new ContentValues();
        db.update(AlarmTableName,values,"flight=?",)


        for (int j = 0; j < PARSERITEMGROUP.length; j++) {
            values.put(PARSERITEMGROUP[j], AlarmItem.getStriItem(j));
        }
        db.insert(AlarmTableName, null, values);
        db.close();
    }*/

   /* *//*SQLiteDatabase를 스토리지에서 삭제한다.*//*
    public void myDatabaseDelete() {
        File dbpath = mContext.getDatabasePath(SchduleDbName);
        Log.i("MYDatabase", dbpath.toString());
        mContext.deleteDatabase(SchduleDbName);
        dbpath.exists();
    }
*/

    // 데이터 검색
    public ArrayList<AirlineItem> selectData(String Flight) {
        SQLiteDatabase db = opener.getReadableDatabase();
        if (Flight == null) {
            String sql = "select * from " + AlarmTableName + ";";
            Cursor results = db.rawQuery(sql, null);
            AlarmItems = selectAlarmTable(results);
            db.close();
            return AlarmItems;
        } else {
            String sql = "select * from " + AlarmTableName + " where flightId = "
                    + Flight + ";";
            Cursor result = db.rawQuery(sql, null);
            AlarmItems = selectAlarmTable(result);
            db.close();
            return AlarmItems;
        }
    }

    /*선택된 데이터를 ArrayList에 넣는다.*/
    private ArrayList<AirlineItem> selectAlarmTable(Cursor results) {
        results.moveToFirst();
        ArrayList<AirlineItem> items = new ArrayList<>();
        String[] str = new String[PARSERITEMGROUP.length];
        while (!results.isAfterLast()) {

            int k = 0;
            for (int i = 1; i < str.length; i++) {
                str[k] = results.getString(i);
                k++;
            }
            AirlineItem item = new AirlineItem(str);
            items.add(item);
            results.moveToNext();
        }
        printLog(items);
        results.close();
        return items;
    }

    private void printLog(ArrayList<AirlineItem> items) {
        int j = items.size();
        int k = j / 10;
        for (int i = 0; i < k; i++) {
            AirlineItem item = items.get(i);
            String str = item.getStriItem(1);
            Log.i("MyDataBase.java", str);
        }
    }
}
