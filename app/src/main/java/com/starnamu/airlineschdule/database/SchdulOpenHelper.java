package com.starnamu.airlineschdule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by starnamu on 2015-07-19.
 */
public class SchdulOpenHelper extends SQLiteOpenHelper {

    private static SchdulOpenHelper instance;

    private static final String SchduleDbName = "SchduleDB.db";
    private static final String tableName = "SchduleTable";
    private static final int dbVersion = 1;

    static final Object lock = new Object();

    Context mContext;

    private SchdulOpenHelper(Context context) {
        super(context, SchduleDbName, null, dbVersion);
        mContext = context;

        /*myDatabaseDelete(mContext);
        Log.i("OpenHelper", "DB를 삭제 했습니다");*/
    }

    public static SchdulOpenHelper getNewInstance(Context context) {
        if (instance == null) {
            synchronized (lock) {
                instance = new SchdulOpenHelper(context);
            }
        }
        return instance;
    }

   /* //    SQLiteDatabase를 스토리지에서 삭제한다.
    public void myDatabaseDelete(Context mContext) {
        File dbpath = mContext.getDatabasePath("AirlineSchdule.db");
        Log.i("MYDatabase", dbpath.toString());
        mContext.deleteDatabase(SchduleDbName);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateSchduleDataTable(db);
    }

    private void onCreateSchduleDataTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "("
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

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
