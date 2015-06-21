package com.starnamu.airlineschdule.httpconn;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by starnamu on 2015-05-16.
 */
public class HttpClient {

    // OkHttpClient 객체는 멤버필드에서 final 형태로 한번만 생성해서 newCall 메서드를 통해 재사용한다.
    private final OkHttpClient client = new OkHttpClient();

    public interface Fail<V, O, E> {
        public V call(O request, E e) throws Exception;
    }

    public interface Success<V, O> {
        public V call(O response) throws Exception;
    }

    public Call get(String url, String params, final Fail<Void, Request, IOException> fail, final Success<?, Response> success) throws IOException {
        Request request = new Request.Builder()
                .url(url + "?" + params)
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            @Override
            public void onFailure(final Request request, final IOException e) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fail.call(request, e);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            success.call(response);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        return call;
    }
}
