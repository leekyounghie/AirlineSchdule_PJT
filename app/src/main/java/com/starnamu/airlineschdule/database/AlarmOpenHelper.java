package com.starnamu.airlineschdule.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.starnamu.airlineschdule.MainActivity;
import com.starnamu.airlineschdule.comm.CommonConventions;

/**
 * Created by starnamu on 2015-07-19.
 */
public class AlarmOpenHelper extends SQLiteOpenHelper implements CommonConventions {

    private static AlarmOpenHelper instance;

    private AlarmOpenHelper(Context context) {
        super(context, SchduleDbName, null, dbVersion);

    }

    public static AlarmOpenHelper getNewInstance() {
        if (instance == null) {
            Context context = MainActivity.getMainActivityContext();
            instance = new AlarmOpenHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateAlarmDataTable(db);
    }

    private void onCreateAlarmDataTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + AlarmTableName + "("
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
