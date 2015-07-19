package com.starnamu.airlineschdule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.parser.AirlineItem;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by starnamu on 2015-07-09.
 */

//DB를 총괄관리
public class MyDataBase implements CommonConventions {

    // DB관련 상수 선언
    private static final String SchduleDbName = "AirlineSchdule.db";
    private static final String SchduleTableName = "SchduleTable";
    private static final String AlarmTableName = "AlarmTableName";
    public static final int dbVersion = 1;

    ArrayList<AirlineItem> SchduleItems;

    // DB관련 객체 선언
    private OpenHelper opener; // DB opener
    private SQLiteDatabase db; // DB controller

    // 부가적인 객체들
    private Context mContext;

    // 생성자
    public MyDataBase(Context context) {
        this.mContext = context;
        this.opener = new OpenHelper(context, SchduleDbName, null, dbVersion);
        db = opener.getWritableDatabase();
    }

    // 데이터 추가
    public void insertData(ArrayList<AirlineItem> items) {
        allRemoveData(SchduleTableName);

        this.SchduleItems = items;
        SQLiteDatabase db = opener.getWritableDatabase();
        for (int i = 0; i < items.size(); i++) {
            AirlineItem item = items.get(i);
            ContentValues values = new ContentValues();

          /*  for (int j = 0; j < PARSERITEMGROUP.length; j++) {
                values.put(PARSERITEMGROUP[j], item.getStriItem(j));*/
            values.put("airline", item.getStriItem(0));
            values.put("airport", item.getStriItem(1));
            values.put("airportCode", item.getStriItem(2));
            values.put("flightId", item.getStriItem(3));
            values.put("scheduleDateTime", item.getStriItem(4));
            values.put("estimatedDateTime", item.getStriItem(5));
            values.put("chkinrange", item.getStriItem(6));
            values.put("gatenumber", item.getStriItem(7));
            values.put("remark", item.getStriItem(8));
            values.put("carousel", item.getStriItem(9));
            values.put("ADStat", item.getStriItem(10));

            db.insert(SchduleTableName, null, values);

//            }
        }
    }

    // 데이터 갱신

    public void updateData(AirlineItem item, int index) {
        String sql = "update " + SchduleTableName
                + " set airline = '" + item.getStriItem(0)
                + "', airport = " + item.getStriItem(1)
                + "', airportCode = " + item.getStriItem(2)
                + ", flightId = '" + item.getStriItem(3)
                + ", scheduleDateTime = '" + item.getStriItem(4)
                + ", estimatedDateTime = '" + item.getStriItem(5)
                + ", chkinrange = '" + item.getStriItem(6)
                + ", gatenumber = '" + item.getStriItem(7)
                + ", remark = '" + item.getStriItem(8)
                + ", carousel = '" + item.getStriItem(9)
                + ", ADStat = '" + item.getStriItem(10)
                + "' where id = " + index
                + ";";
        db.execSQL(sql);
    }

    // 데이터 삭제
    public void removeData(int index) {
        String sql = "delete from " + AlarmTableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    // 데이터 전체 삭제
    private void allRemoveData(String tableName) {
        db.delete(tableName, null, null);
    }

    public void myDatabaseDelete() {
        File dbpath = mContext.getDatabasePath(SchduleDbName);
        Log.i("MYDatabase", dbpath.toString());
        mContext.deleteDatabase(SchduleDbName);
    }

    // 데이터 검색
    public AirlineItem selectData(int index) {
        String sql = "select * from " + SchduleTableName + " where id = " + index
                + ";";
        Cursor result = db.rawQuery(sql, null);
        AirlineItem item;
        String[] str = new String[PARSERITEMGROUP.length];

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if (result.moveToFirst()) {

//            java foreach
            for (int i = 0; i < str.length; i++) {
                str[i] = result.getString(i);
            }
            item = new AirlineItem(str);
            result.close();
            return item;
        }
        result.close();
        return null;
    }

    // 데이터 전체 검색
    public ArrayList<AirlineItem> selectAll(String tableName) {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        if (tableName.equals(SchduleTableName)) {
            SchduleItems = selectSchduleTable(results);
        } else {

        }
        return selectSchduleTable(results);
    }

    private ArrayList<AirlineItem> selectSchduleTable(Cursor results) {
        results.moveToFirst();
        SchduleItems = new ArrayList<AirlineItem>();
        String[] str = new String[PARSERITEMGROUP.length];
        while (!results.isAfterLast()) {
            for (int i = 0; i < str.length; i++) {
                str[i] = results.getString(i);
            }
            AirlineItem item = new AirlineItem(str);
            SchduleItems.add(item);
            results.moveToNext();
        }
        printLog(SchduleItems);
        results.close();

        return SchduleItems;
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