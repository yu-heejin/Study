
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {
    public static void main(String args[]) throws SAXException {
        //파싱에 사용할 XMLReader 객체 생성
        //재사용이 가능하지만 한번에 한 개의 xml 파일만을 파싱해야한다.
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        
        MyHandler myHandler = new MyHandler();
        //ContentHandler와 ErrorHandler를 구현한 class
        //따로 만들어야할 것 같음
        
        parser.setContentHandler(myHandler);
        parser.setErrorHandler(myHandler);
        
        parser.parse("input.xml");
        
        //------------------------------------------------------------------------------------------------------
        
        //1. xmlReader를 사용해 ContentHandler 등록 및 parsing
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser2 = factory.newSAXParser();
        
        ContentHandler contentHandler = new MyContentHandler();
        //contentHandler 객체 생성(ContentHandler구현 클래스)
        
        //ContentHandler 객체 등록과 parsing
        XMLReader reader = parser2.getXMLReader();
        reader.setContentHandler(contentHandler);
        reader.parse("ch10/bml.xml");
        
    }

}
