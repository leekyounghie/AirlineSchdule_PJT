package com.starnamu.airlineschdule.parser;

import android.os.Handler;
import android.util.Log;

import com.starnamu.airlineschdule.comm.CommonConventions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class AirlineParser implements CommonConventions {

    Element[] element = new Element[PARSERITEMGROUP.length];
    String[] itemStr = new String[PARSERITEMGROUP.length];
    ArrayList<AirlineItem> TempList;
    ArrayList<AirlineItem> itemLists;
    String DepartureAirlineRequest;
    String ArrivalAirlineRequest;
    Handler handler;
    String Url;
    String ADstate;

    public AirlineParser(String UrlD, String UrlA) {
        this.DepartureAirlineRequest = UrlD;
        this.ArrivalAirlineRequest = UrlA;

        handler = new Handler();
        TempList = new ArrayList<>();
        ParserThread thread = new ParserThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ParserThread extends Thread {
        int i = 0;

        /*Thread로 URL 연결*/
        public void run() {
            Url = URLHADE + DepartureAirlineRequest + SERVICEKEY;
            DAurl(Url, "D");

            Url = URLHADE + ArrivalAirlineRequest + SERVICEKEY;
            DAurl(Url, "A");
        }

        /*URL 연결 Method*/
        private void DAurl(String Url, String state) {
            ADstate = state;
            try {
                URL url = new URL(Url);
                InputStream inStream = url.openStream();
                i++;
                Log.i("URL호출 횟수 : ", Integer.toString(i));
                airportparser(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*외부로 부터이 요청에 ArrayList 반환 */
    public ArrayList<AirlineItem> getArrayList() {
        return itemLists;
    }

    private void airportparser(InputStream inStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(inStream);
        itemLists = parserDocument(document);
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

        AirlineItem item = new AirlineItem(itemStr);
        return item;
    }
}