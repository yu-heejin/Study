package ch9;

//JAXP ��Ű��
import javax.xml.parsers.*;

public class CreateDOMParser {
	public static void main(String[] args) throws Exception {
		//DOM �ļ� ���� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DOM �ļ� ����
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		System.out.println("DOM �ļ� ��ü ���� ����:" + parser);
	}
}
