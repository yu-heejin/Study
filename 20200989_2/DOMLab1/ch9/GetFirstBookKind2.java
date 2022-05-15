package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookKind2 {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// ù��° book ������Ʈ��  kind �Ӽ���  ����
		Element eBook = (Element) eRoot.getFirstChild();
		String strKind = eBook.getAttribute("kind");
		System.out.println(strKind);
		eBook.setAttribute("kind", "computer");
		// == eBook.getAttributeNode("kind").setNodeValue("computer"); 
		System.out.println(eBook.getAttribute("kind"));

		// ù��° book ������Ʈ��  ���ο� �Ӽ� �߰�
		eBook.setAttribute("publishDate", "20090529");
		System.out.println(eBook.getAttribute("publishDate"));

	}

}
