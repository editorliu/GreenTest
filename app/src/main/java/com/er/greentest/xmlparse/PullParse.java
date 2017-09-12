package com.er.greentest.xmlparse;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.xmlpull.v1.XmlPullParser.START_TAG;

/**
 * Created by Administrator on 2017/8/11.
 */

public class PullParse {
    private ArrayList<Book> list;
    private Book book;

    public ArrayList<Book> getList() {
        return list;
    }

    public void parse(InputStream in) {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(in,"utf-8");
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i("XML_SAX", "PULL START_DOCUMENT");
                        list = new ArrayList<>();
                        break;
                    case START_TAG:
                        Log.i("XML_SAX", "PULL START_TAG");
                        String name = xmlPullParser.getName().trim();
                        if ("book".equals(name)) {
                            book = new Book();
                        } else if ("id".equals(name)) {
                            book.setId(Integer.parseInt(xmlPullParser.nextText()));
                        } else if ("name".equals(name)) {
                            book.setName(xmlPullParser.nextText());
                        } else if ("price".equals(name)) {
                            book.setPrice(Double.parseDouble(xmlPullParser.nextText()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        Log.i("XML_SAX", "PULL END_TAG");
                        String endName = xmlPullParser.getName().trim();
                        if ("book".equals(endName)) {
                            list.add(book);
                            book = null;
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
            for (int i = 0; i < list.size(); i++) {
                Book book = list.get(i);
                Log.i("XML_SAX", "PULL ----  book:"+book.getId()+"  "+book.getName()+"  "+book.getPrice());
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
