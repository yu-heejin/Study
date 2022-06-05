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
        //검색 엘리먼트
    	this.condition = condition;
        //검색 문자열
    	this.keyWord = keyWord;
    	//검색 내용을 저장할 Hashtable 객체
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
		throw new SAXException("warning 이벤트 처리");
	}

	@Override
    public void error(SAXParseException exception) throws SAXException {
		System.out.println("DTD 또는 XML Schema 문서 구조에 위배됩니다.");
		System.out.println("유효하지 않는 문서 입니다.");   	
		throw new SAXException("error 이벤트 처리");
	}

	@Override
    public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("XML 권고안의 내용을 지키지 않았습니다.");
		System.out.println("잘 짜여진 XML 문서가 아닙니다."); 
		throw new SAXException("fatalError 이벤트 처리");
	}	    
    
    public void displayBookInfo() {
        System.out.println("title: " + (String) hash.get("title"));
        System.out.println("author: " + (String) hash.get("author"));
        System.out.println("publisher: " + (String) hash.get("publisher"));
        System.out.println("price: " + (String) hash.get("price"));
        System.out.println("----------------------");        
    }    
}
