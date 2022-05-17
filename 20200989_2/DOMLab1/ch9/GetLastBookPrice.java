package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetLastBookPrice {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// ��������° book ������Ʈ ���� ���
		Element eBook = (Element) eRoot.getLastChild();
		Element ePrice = (Element) eBook.getLastChild();
		Text tPrice = (Text) ePrice.getFirstChild();
		String strPrice = tPrice.getData();

		System.out.println(strPrice);
	}

}
