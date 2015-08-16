package com.starnamu.airlineschdule.comm;

/**
 * Created by starnamu on 2015-05-08.
 */
public interface CommonConventions {

    String URLHADE = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights";
//  String SERVICEKEY = "?ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";

    String SERVICEKEY = "?ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx" +
            "4u3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";
    String PARRIVALS = "/getPassengerArrivals";
    String PDEPARTURES = "/getPassengerDepartures";

    String[] AIRLINENAME = {"중국동방항공", "사천항공", "싱가포르항공", "에바항공", "중국산동항공", "에어캐나다", "사할린", "델타항공"
            , "제스트항공", "ANA항공 전일본송수", "에어부산", "마카오항공", "블라디보스톡"};

    String[] PARSERITEMGROUP = {"airline", "airport", "airportCode", "flightId", "scheduleDateTime",
            "estimatedDateTime", "chkinrange", "gatenumber", "remark", "carousel", "ADStat"};

    String SaveSettingFileName = "/mnt/sdcard/setting.txt";

    String AlarmTableName = "AlarmTableName";
    String SchduleDbName = "AlarmDB.db";
    int dbVersion = 1;
}
