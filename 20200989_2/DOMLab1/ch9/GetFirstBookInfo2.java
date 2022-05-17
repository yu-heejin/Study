package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookInfo2 {

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

		for(Node ch = eBook.getFirstChild(); ch != null; ch = ch.getNextSibling())
		{			
			if (ch.getNodeName().equals("title"))	
		         System.out.print("����: ");
			else if (ch.getNodeName().equals("author"))	
		         System.out.print("����: ");
			else if (ch.getNodeName().equals("publisher"))	
		         System.out.print("���ǻ�: ");
			else if (ch.getNodeName().equals("price"))	
		         System.out.print("����: ");		
			System.out.println(ch.getFirstChild().getNodeValue());
		}

	}

}
