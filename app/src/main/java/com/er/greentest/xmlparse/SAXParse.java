package com.er.greentest.xmlparse;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2017/8/11.
 */

public class SAXParse {
    public void parse(InputStream in) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler dh = new MyHandler();
            saxParser.parse(in, dh);
            ArrayList<Book> books = dh.getmList();
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                Log.i("XML_SAX", "book:"+book.getId()+"  "+book.getName()+"  "+book.getPrice());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    class MyHandler extends DefaultHandler {
        private ArrayList<Book> mList = new ArrayList<>();
        private Book book;
        private StringBuffer stringBuffer = new StringBuffer();

        public ArrayList<Book> getmList() {
            return mList;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            Log.i("XML_SAX","-------------startDocument");
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            Log.i("XML_SAX","-------------endDocument");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            Log.i("XML_SAX","-------------startElement uri="+uri+"  localName="+localName+"  qName="+qName);
            stringBuffer.setLength(0);
            if ("book".equals(localName)) {
                book = new Book();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            Log.i("XML_SAX","-------------endElement uri="+uri+"  localName="+localName+"  qName="+qName);
            String content = stringBuffer.toString();
            if("id".equals(localName)){
                book.setId(Integer.parseInt(content));
            }else if("name".equals(localName)){
                book.setName(content);
            }else if("price".equals(localName)){
                book.setPrice(Double.parseDouble(content));
            }else if("book".equals(localName)){
                mList.add(book);
            }
            stringBuffer.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            stringBuffer.append(ch, start, length);
            Log.i("XML_SAX","-------------characters start="+start+"  length="+length+"  content:"+ stringBuffer);
        }
    }
}
