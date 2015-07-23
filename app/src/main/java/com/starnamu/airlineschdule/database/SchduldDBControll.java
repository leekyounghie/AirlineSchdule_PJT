package com.starnamu.airlineschdule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by starnamu on 2015-07-20.
 */
public class SchduldDBControll implements CommonConventions {

    // DB관련 상수 선언
    private String SchduleDbName = "AirlineSchdule.db";
    private String SchduleTableName = "SchduleTable";

    ArrayList<AirlineItem> SchduleItems;
    private final SchdulOpenHelper opener; // DB opener
    private Context mContext;

    // 생성자
    public SchduldDBControll(Context context, ArrayList<AirlineItem> items) {
        this.mContext = context;
        this.opener = SchdulOpenHelper.getNewInstance(context);
        this.SchduleItems = items;

        init();
    }

    private void init() {
        thread.start();
    }

    /*테이블에 새로운 Data를 삽입한다.*/
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            /*테이블의 모든 Data를 삭제한다.*/
            allRemoveData(SchduleTableName);
             /*테이블에 새로운 Data를 삽입한다.*/
            insertData();
        }
    });

    // 데이터 추가
    public void insertData() {
        SQLiteDatabase db = opener.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < SchduleItems.size(); i++) {
            AirlineItem item = SchduleItems.get(i);
            for (int j = 0; j < PARSERITEMGROUP.length; j++) {
                values.put(PARSERITEMGROUP[j], item.getStriItem(j));
            }
            db.insert(SchduleTableName, null, values);
        }
        db.close();
    }

    // 데이터 전체 삭제
    private void allRemoveData(String tableName) {
        SQLiteDatabase db = opener.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    /*SQLiteDatabase를 스토리지에서 삭제한다.*/
    public void myDatabaseDelete() {
        File dbpath = mContext.getDatabasePath(SchduleDbName);
        Log.i("MYDatabase", dbpath.toString());
        mContext.deleteDatabase(SchduleDbName);
    }

    // 데이터 전체 검색
    public ArrayList<AirlineItem> selectAll(String tableName) {
        SQLiteDatabase db = opener.getReadableDatabase();
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);

        if (tableName.equals(SchduleTableName)) {
            SchduleItems = selectSchduleTable(results);
        } else {
            Log.e("AlarmDbControll", "selectAll() Errer");
            db.close();
            return null;
        }
        db.close();
        return SchduleItems;
    }

    private ArrayList<AirlineItem> selectSchduleTable(Cursor results) {
        results.moveToFirst();
        SchduleItems = new ArrayList<>();
        String[] str = new String[PARSERITEMGROUP.length];
        while (!results.isAfterLast()) {
            for (int i = 0; i < str.length; i++) {
                str[i] = results.getString(i);
            }
            AirlineItem item = new AirlineItem(str);
            SchduleItems.add(item);
            results.moveToNext();
        }

        Toast.makeText(mContext, "SchduleDBControll의 DB가 저장 되었습니다", Toast.LENGTH_LONG).show();

       /* DB확인용 Method
        printLog(SchduleItems);*/
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
