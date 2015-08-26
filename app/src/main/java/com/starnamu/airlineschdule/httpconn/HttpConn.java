package com.starnamu.airlineschdule.httpconn;

import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class HttpConn implements CommonConventions {

    Element[] element;
    String[] itemStr;
    ArrayList<AirlineItem> TempList;
    ArrayList<AirlineItem> itemLists;
    String ADstate;
    Handler handler;


    String URLHADE = "http://openapi.airport.kr/openapi/service/StatusOfPassengerFlights";
    String URLAIRCRAFT = "/getPassengerDepartures";
    String SERVICEKEY = "?ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u" +
            "3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";

    public HttpConn() {

        this.element = new Element[PARSERITEMGROUP.length];
        this.itemStr = new String[PARSERITEMGROUP.length];
        handler = new Handler();
        TempList = new ArrayList<>();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = URLHADE;
        StringBuilder sb = new StringBuilder();
        sb.append(URLAIRCRAFT);
        sb.append(SERVICEKEY);
//        String params = sb.toString();
        String params = "http://openapi.airport.kr/openapi/service/" +
                "StatusOfPassengerFlights/getPassengerArrivals?" +
                "ServiceKey=RN5il12RYM%2FXFWaIm8otCbez%2B5W1YxN91ZzBtYx4u" +
                "3hh24IgLuMAr5LEvByuM62KPv7l8Y4qbNUy0AgE2YtWHw%3D%3D";

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
                               /* InputStreamReader ISR = new InputStreamReader(is);
                                BufferedReader BR = new BufferedReader(ISR);*/
                                airportparser(is);
                                is.close();
                                /*ISR.close();
                                BR.close();*/
                            } else {
                                System.out.println("Whom Not successful!");
                            }
                            return null;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*외부로 부터이 요청에 ArrayList 반환 */
    public ArrayList<AirlineItem> getArrayList() {
        return this.itemLists;
    }

    private void airportparser(InputStream inStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inStream);
        this.itemLists = parserDocument(document);
    }

    private ArrayList<AirlineItem> parserDocument(Document document) {
        Element element = document.getDocumentElement();
        element.getElementsByTagName("item");
        NodeList nodeList = element.getElementsByTagName("item");

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (parseItemNode(nodeList, i) != null) {
                    AirlineItem item = parseItemNode(nodeList, i);
                    TempList.add(item);
                }
            }
        }
        return TempList;
    }

    private AirlineItem parseItemNode(NodeList nodeList, int index) {
        Element elem = (Element) nodeList.item(index);
        for (int i = 0; i < PARSERITEMGROUP.length; i++) {
            element[i] = (Element) elem.getElementsByTagName(PARSERITEMGROUP[i]).item(0);
            if (element[i] == null) {
                itemStr[i] = " ";
            } else if (element[i] != null) {
                Node firstchild = element[i].getFirstChild();
                if (firstchild != null) {
                    itemStr[i] = firstchild.getNodeValue();
                }
            }
        }

        if (ADstate.equals("A")) {
            itemStr[10] = "A";
        } else if (ADstate.equals("D")) {
            itemStr[10] = "D";
        } else {
            itemStr[10] = "";
        }

        for (int i = 0; i < itemStr.length; i++) {
            Log.i("", itemStr[i]);
        }

        AirlineItem item = new AirlineItem(itemStr);
        return item;
    }
}
