
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlReader {
    public static void main(String args[]) throws SAXException {
        //�Ľ̿� ����� XMLReader ��ü ����
        //������ ���������� �ѹ��� �� ���� xml ���ϸ��� �Ľ��ؾ��Ѵ�.
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        
        MyHandler myHandler = new MyHandler();
        //ContentHandler�� ErrorHandler�� ������ class
        //���� �������� �� ����
        
        parser.setContentHandler(myHandler);
        parser.setErrorHandler(myHandler);
        
        parser.parse("input.xml");
        
        //------------------------------------------------------------------------------------------------------
        
        //1. xmlReader�� ����� ContentHandler ��� �� parsing
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser parser2 = factory.newSAXParser();
        
        ContentHandler contentHandler = new MyContentHandler();
        //contentHandler ��ü ����(ContentHandler���� Ŭ����)
        
        //ContentHandler ��ü ��ϰ� parsing
        XMLReader reader = parser2.getXMLReader();
        reader.setContentHandler(contentHandler);
        reader.parse("ch10/bml.xml");
        
    }

}
