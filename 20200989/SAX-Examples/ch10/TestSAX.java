package ch10;


import javax.xml.parsers.*;

public class TestSAX {
    public static void main(String args[]) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
                    
            EventHandler handler = new EventHandler();
            parser.parse("ch10/bml.xml", handler);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
