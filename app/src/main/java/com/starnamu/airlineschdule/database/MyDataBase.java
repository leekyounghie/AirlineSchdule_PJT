package com.starnamu.airlineschdule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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
    private static final String dbName = "AirlineSchdule.db";
    private static final String tableName = "SchduleTable";
    private static final String AlarmTableName = "AlarmTableName";
    public static final int dbVersion = 1;

    ArrayList<AirlineItem> items;

    // DB관련 객체 선언
    private OpenHelper opener; // DB opener
    private SQLiteDatabase db; // DB controller

    // 부가적인 객체들
    private Context mContext;

    // 생성자
    public MyDataBase(Context context) {
        this.mContext = context;
        this.opener = new OpenHelper(context, dbName, null, dbVersion);
        db = opener.getWritableDatabase();
    }

    // Opener of DB and Table
    private class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
            super(context, name, null, version);
            // TODO Auto-generated constructor stub
        }

        // 생성된 DB가 없을 경우에 한번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase db) {
            // String dropSql = "drop table if exists " + tableName;
            // db.execSQL(dropSql);

            onCreateSchduleDataTable(db);
//            onCreateAlarmDataTable(db);

            Toast.makeText(mContext, "DB is opened", Toast.LENGTH_LONG).show();
        }

        private void onCreateSchduleDataTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + tableName + "("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "airline TEXT, "
                    + "airport TEXT, "
                    + "airportCode TEXT, "
                    + "flightId TEXT, "
                    + "scheduleDateTime TEXT, "
                    + "estimatedDateTime TEXT, "
                    + "chkinrange TEXT, "
                    + "gatenumber TEXT, "
                    + "remark TEXT, "
                    + "carousel TEXT, "
                    + "ADStat TEXT"
                    + ")");
        }

        private void onCreateAlarmDataTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + AlarmTableName + "("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "remark TEXT, "
                    + "carousel TEXT, "
                    + "ADStat TEXT"
                    + ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
        }
    }

    // 데이터 추가
    public void insertData(ArrayList<AirlineItem> items) {
        allRemoveData();

        this.items = items;
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

            db.insert(tableName, null, values);

//            }
        }
    }

    // 데이터 갱신

    public void updateData(AirlineItem item, int index) {
        String sql = "update " + tableName
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
        String sql = "delete from " + tableName + " where id = " + index + ";";
        db.execSQL(sql);
    }

    // 데이터 전체 삭제
    private void allRemoveData() {
        db.delete(tableName, null, null);
    }

    public void myDatabaseDelete() {
        String dbname = "AirlineSchdule.db";
        File dbpath = mContext.getDatabasePath(dbname);
        Log.i("MYDatabase", dbpath.toString());
        mContext.deleteDatabase(dbname);
    }

    // 데이터 검색
    public AirlineItem selectData(int index) {
        String sql = "select * from " + tableName + " where id = " + index
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
    public ArrayList<AirlineItem> selectAll() {

        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        results.moveToFirst();
        items = new ArrayList<AirlineItem>();
        String[] str = new String[PARSERITEMGROUP.length];
        while (!results.isAfterLast()) {
            for (int i = 0; i < str.length; i++) {
                str[i] = results.getString(i);
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