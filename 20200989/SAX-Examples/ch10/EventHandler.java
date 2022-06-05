package ch10;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class EventHandler extends DefaultHandler {
	
	int Scount = 0;
	int Ecount = 0;
    //Constructor
    public EventHandler() { }

    public void startDocument() {
    	System.out.println("startDocument() method ȣ���");
    	System.out.println("XML ������ ���۵�");
    }
    
    public void endDocument() {
    	System.out.println("endDocument() method ȣ���");
    	System.out.println("XML ������ �����");
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	Scount++;
    	System.out.println("startElement() method ȣ���");
    	System.out.println(Scount + "��° ���ۿ��: " + qName);
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch,start,length).trim();
        System.out.println("characters() method ȣ���");
    	System.out.println("Content:" + str);
    }
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Ecount++;
    	System.out.println("endElement() method ȣ���");
    	System.out.println(Ecount + "��° ���� ���: " + qName);
    }
}

