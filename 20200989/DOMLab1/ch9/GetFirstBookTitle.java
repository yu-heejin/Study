package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookTitle {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// ù��° book ������Ʈ ���� ���
		Element eBook = (Element) eRoot.getFirstChild();
		Element eTitle = (Element) eBook.getFirstChild();
		Text tTitle = (Text) eTitle.getFirstChild();
		String strTitle = tTitle.getData();

		System.out.println(strTitle);
	}

}
