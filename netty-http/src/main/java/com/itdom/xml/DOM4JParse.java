package com.itdom.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J解析xml
 * 1. DOM4J是JDOM的一种智能分支，它合并了许多超出基本XML文档的表示的功能
 * 2. DOM4J使用接口和出现基本类方法，是一个优秀的Java XML API
 * 3. 具有性能优异，灵活性好，功能强大和极端易用使用的特点
 * 4.是一个开放源代码的软件
 */
public class DOM4JParse {
    public static void main(String[] args) {
        try {
            //1.创建Reader对象
            SAXReader reader = new SAXReader();
            //2. 加载xml
            Document document = reader.read(new File("./netty-http/files/data.xml"));
            //3. 获取跟节点
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                List<Attribute> attributes = element.attributes();
                System.out.println("===========获取属性值==========");
                for (Attribute attribute : attributes) {
                    System.out.println(attribute.getValue());
                }
                System.out.println("============遍历子节点============");
                Iterator iterator1 = element.elementIterator();
                while (iterator1.hasNext()) {
                    Element element1 = (Element) iterator1.next();
                    System.out.println("节点名：" + element1.getName() + "----节点值:" + element1.getStringValue());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
