package com.itdom.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * SAX解析DOM
 * 优点：
 * 1.采用时间驱动的模式，对内存消耗比较少
 * 2.适用于只需要处理xml中数据时
 * 缺点：
 * 1.不易编码
 * 2. 很难同时访问同一个xml中多处不同的数据
 */
public class SAXParse {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //获取SAXparser示例
            SAXParser saxParser = factory.newSAXParser();

            SAXDemoHandler saxDemoHandler = new SAXDemoHandler();
            saxParser.parse("./netty-http/files/data.xml", saxDemoHandler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class SAXDemoHandler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("sax解析开始");

        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("sax解析结束");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (qName.equals("student")) {
                System.out.println("开始便利student");
            } else if (!qName.equals("student") && !qName.equals("class")) {
                System.out.println("节点名称:" + qName + "----------");
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (qName.equals("student")) {
                System.out.println(qName + "遍历结束");
                System.out.println("==================结束遍历student================");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            String value = new String(ch, start, length).trim();
            if (!value.equals("")) {
                System.out.println(value);
            }
        }
    }
}
