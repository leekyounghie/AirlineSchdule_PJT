package com.starnamu.airlineschdule.database_old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by starnamu on 2015-07-19.
 */
public class OpenHelper extends SQLiteOpenHelper {


    String dbName = null;
    private static final String tableName = "SchduleTable";
    private static final String AlarmTableName = "AlarmTableName";
    int dbVersion;
    // Opener of DB and Table

    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, name, null, version);

        this.dbName = name;
        this.dbVersion = version;
        // TODO Auto-generated constructor stub
    }

    // 생성된 DB가 없을 경우에 한번만 호출됨
    @Override
    public void onCreate(SQLiteDatabase db) {
        // String dropSql = "drop table if exists " + tableName;
        // db.execSQL(dropSql);

        onCreateSchduleDataTable(db);
        onCreateAlarmDataTable(db);
//
//        Toast.makeText(mContext, "DB is opened", Toast.LENGTH_LONG).show();
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
                + "flightId TEXT, "
                + "estimatedDateTime TEXT, "
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
