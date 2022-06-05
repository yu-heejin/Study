package ch10;


import javax.xml.parsers.*;

public class FindBook {
    ///Field
    ///Constructor
    ///Method
    public static void main(String args[]) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            
            System.out.println("########################################");
            System.out.println("책 제목으로 검색");
            System.out.println("########################################");
            FindBookEventHandler handler = new FindBookEventHandler("title", "XML");
            parser.parse("ch10/bml.xml", handler);
            
            System.out.println("########################################");
            System.out.println("책 종류으로 검색");
            System.out.println("########################################");
            handler = new FindBookEventHandler("kind", "소설");
            parser.parse("ch10/bml.xml", handler);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
