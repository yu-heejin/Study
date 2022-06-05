package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingDTDHandler {
	public static void main(String args[]) throws Exception {	
		// �ļ� ����
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		// DTDHandler ��ü ����
		MyDTDHandler dtdHandler = new MyDTDHandler();

		// contentHandler ��ü ���
		reader.setDTDHandler(dtdHandler);

		// �Ľ� ����
		reader.parse("ch10/bml.xml");

		// å�̹��� ����
		dtdHandler.showImage();
	}	
}