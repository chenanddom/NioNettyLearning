package com.itdom.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * DOM解析
 *优点：
 * 1.形成了树形结构，只管好理解，代码更易编写
 * 2.解析的过程中保留在内存中，方便修改
 * 缺点：
 * 1.当xml文件较大的时候对内存消耗比较大，容易影响解析性能并造成内存溢出.
 */
public class DOMParse {
    public static void node(NodeList list){
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int i1 = 0; i1 < childNodes.getLength(); i1++) {
                if (childNodes.item(i1).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(i1).getNodeName() + ":");
                    System.out.println(childNodes.item(i1).getFirstChild().getNodeValue());
                }
            }
        }

    }

    public static void element(NodeList list){
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int i1 = 0; i1 < childNodes.getLength(); i1++) {
                if (childNodes.item(i1).getNodeType()==Node.ELEMENT_NODE){
                    System.out.print(childNodes.item(i1).getNodeName()+":");
                    System.out.println(childNodes.item(i1).getFirstChild().getNodeValue());
                }
            }

        }
    }




    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse("./netty-http/files/data.xml");
            NodeList nodeList = document.getElementsByTagName("student");
            System.out.println(nodeList);
            node(nodeList);
//            element(nodeList);
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }
}
