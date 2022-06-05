package ch10;


import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;

public class FindBookEventHandler extends DefaultHandler {
    ///Field
	boolean isBook, isTitle, isAuthor, isPublisher, isPrice;
    String condition, keyWord;
    Hashtable<String, String> hash;
    
    ///Constructor
    public FindBookEventHandler(String condition, String keyWord) {
        //�˻� ������Ʈ
    	this.condition = condition;
        //�˻� ���ڿ�
    	this.keyWord = keyWord;
    	//�˻� ������ ������ Hashtable ��ü
        this.hash = new Hashtable<String, String>();
    }

	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if(qName.equals("book")) {
            isBook = true;
            String kind = attributes.getValue("kind");
            hash.put("kind", kind);
        } else if(qName.equals("title")) {
            isTitle = true;
        } else if(qName.equals("author")) {
            isAuthor = true;
        } else if(qName.equals("publisher")) {
            isPublisher = true;
        } else if(qName.equals("price")) {
            isPrice = true;
        } 
    }

	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length).trim();
        if(isTitle) {
            hash.put("title", str);
            isTitle = false;
        } else if(isAuthor) {
            hash.put("author", str);
            isAuthor = false;
        } else if(isPublisher) {
            hash.put("publisher", str);
            isPublisher = false;
        } else if(isPrice) {
            hash.put("price", str);
            isPrice = false;
        }
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("book")) {
        	if(condition.equals("kind")) {
                String kind = (String) hash.get("kind");
                if(kind.indexOf(keyWord)!=-1)	displayBookInfo();
        	} else if(condition.equals("title")) {
                String title = (String) hash.get("title");
                if(title.indexOf(keyWord)!=-1)	displayBookInfo();
            } else if(condition.equals("author")) {
                String author = (String) hash.get("author");
                if(author.indexOf(keyWord)!=-1) displayBookInfo();
            } else if(condition.equals("publisher")) {
                String publisher = (String) hash.get("publisher");
                if(publisher.indexOf(keyWord)!=-1) displayBookInfo();
            }
            hash.clear();
            isBook = false;
        }
    }

	@Override
    public void warning(SAXParseException exception) throws SAXException {
		throw new SAXException("warning �̺�Ʈ ó��");
	}

	@Override
    public void error(SAXParseException exception) throws SAXException {
		System.out.println("DTD �Ǵ� XML Schema ���� ������ ����˴ϴ�.");
		System.out.println("��ȿ���� �ʴ� ���� �Դϴ�.");   	
		throw new SAXException("error �̺�Ʈ ó��");
	}

	@Override
    public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("XML �ǰ���� ������ ��Ű�� �ʾҽ��ϴ�.");
		System.out.println("�� ¥���� XML ������ �ƴմϴ�."); 
		throw new SAXException("fatalError �̺�Ʈ ó��");
	}	    
    
    public void displayBookInfo() {
        System.out.println("title: " + (String) hash.get("title"));
        System.out.println("author: " + (String) hash.get("author"));
        System.out.println("publisher: " + (String) hash.get("publisher"));
        System.out.println("price: " + (String) hash.get("price"));
        System.out.println("----------------------");        
    }    
}
