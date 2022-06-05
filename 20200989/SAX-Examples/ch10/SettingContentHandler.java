package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingContentHandler {
	public static void main(String args[]) throws Exception {	
		// �ļ� ����
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		// DTDHandler ��ü ����
		ContentHandler contentHandler = new MyContentHandler();

		// contentHandler ��ü ���
		reader.setContentHandler(contentHandler);

		// �Ľ� ����
		reader.parse("ch10/bml.xml");
	}	
}