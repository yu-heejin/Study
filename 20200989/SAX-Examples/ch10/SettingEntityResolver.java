package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingEntityResolver {
	public static void main(String args[]) throws Exception {	
		// �ļ� ����
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		// EntityResolver ��ü ����
		EntityResolver entityResolver = new MyEntityResolver();

		// EntityResolver ��ü ���
		reader.setEntityResolver(entityResolver);

		// �Ľ� ����
		reader.parse("ch10/bml.xml");
	}	
}