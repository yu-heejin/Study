package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookKind {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// ù��° book ������Ʈ �Ӽ��� ���
		Element eBook = (Element) eRoot.getFirstChild();
		String strKind = eBook.getAttribute("kind");

		System.out.println(strKind);
	}

}
