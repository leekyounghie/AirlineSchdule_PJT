package com.starnamu.airlineschdule.httpconn;

import android.os.StrictMode;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.starnamu.airlineschdule.comm.CommonConventions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpConn implements CommonConventions {

    String URLHADE = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights";
    String URLAIRCRAFT = "/getPassengerDepartures";
    String SERVICEKEY = "?ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u" +
            "3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";

    public HttpConn() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = URLHADE;
        StringBuilder sb = new StringBuilder();
        sb.append(URLAIRCRAFT);
        sb.append(SERVICEKEY);
        String params = sb.toString();

        HttpClient hc = new HttpClient();
        try {
            hc.get(url, params,
                    new HttpClient.Fail<Void, Request, IOException>() {
                        @Override
                        public Void call(Request request, IOException e) throws Exception {
                            System.out.println("Failed!");
                            return null;
                        }
                    },
                    new HttpClient.Success<Void, Response>() {

                        @Override
                        public Void call(Response response) throws Exception {
                            if (response.isSuccessful()) {
                                InputStream is = response.body().byteStream();
                                InputStreamReader ISR = new InputStreamReader(is);
                                BufferedReader BR = new BufferedReader(ISR);


                                is.close();
                                ISR.close();
                                BR.close();
                            } else {
                                System.out.println("Not successful!");
                            }
                            return null;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
