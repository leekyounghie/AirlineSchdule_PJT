package com.starnamu.airlineschdule.httpconn;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.starnamu.projcet.airlineschedule.R;

import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpConn {

    String URLHADE = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights/getPassengerArrivals";
    String SERVICEKEY = "ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u" +
            "3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";

    public HttpConn(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = URLHADE;
        StringBuilder sb = new StringBuilder();
        String params = sb.append(SERVICEKEY).toString();

        HttpClient hc = new HttpClient();
        try {
            hc.get(url, params, new HttpClient.Fail<Void, Request, IOException>() {
                        @Override
                        public Void call(Request request, IOException e) throws Exception {
                            System.out.println("Failed!");
                            return null;
                        }
                    },
                    new HttpClient.Success<Void, Response>() {
                        StringBuilder builder = new StringBuilder();

                        @Override
                        public Void call(Response response) throws Exception {
                            if (response.isSuccessful()) {
                                InputStream is = response.body().byteStream();
                                InputStreamReader ISR = new InputStreamReader(is);
                                BufferedReader BR = new BufferedReader(ISR);

                                String line = null;
                                while (true) {
                                    line = BR.readLine();
                                    if (line == null) {
                                        break;
                                    }

                                    builder.append(line + "\n");
                                }

                                String output = builder.toString();

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
