package ch10;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class EventHandler extends DefaultHandler {
	
	int Scount = 0;
	int Ecount = 0;
    //Constructor
    public EventHandler() { }

    public void startDocument() {
    	System.out.println("startDocument() method 호출됨");
    	System.out.println("XML 문서가 시작됨");
    }
    
    public void endDocument() {
    	System.out.println("endDocument() method 호출됨");
    	System.out.println("XML 문서가 종료됨");
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	Scount++;
    	System.out.println("startElement() method 호출됨");
    	System.out.println(Scount + "번째 시작요소: " + qName);
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length).trim();
        System.out.println("characters() method 호출됨");
    	System.out.println("Content:" + str);
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Ecount++;
    	System.out.println("endElement() method 호출됨");
    	System.out.println(Ecount + "번째 종료 요소: " + qName);
    }
}

