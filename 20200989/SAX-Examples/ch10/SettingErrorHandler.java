package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingErrorHandler {
	public static void main(String args[]) throws Exception {	
		//�ļ� ���� ����
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//Namespace �ν� ��� ���
		factory.setNamespaceAware(true);
		//DTD Validating ��� ���
		factory.setValidating(true);
		//XML Schema Validating(Namespace �ν� ��� ����� ����)
		factory.setFeature("http://apache.org/xml/features/validation/schema",true);	
					 
		//�ļ� ����
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		
		//�̺�Ʈ �ڵ鷯 ���� 
		ErrorHandler errorHandler = new MyErrorHandler();
		
		//�̺�Ʈ �ڵ鷯�� �ļ��� ���
		reader.setErrorHandler(errorHandler);
		
		//�ϵ� ��ũ�� ����� XML ���� �ؼ�
		reader.parse("ch10/bml.xml");	
		
		System.out.println("��ȿ�� ���� �Դϴ�.");
	}	
}