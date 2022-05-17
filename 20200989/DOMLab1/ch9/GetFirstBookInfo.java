package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetFirstBookInfo {

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
		System.out.println("����: " + strTitle);

		Element eAuthor = (Element) eTitle.getNextSibling();
		Text tAuthor = (Text) eAuthor.getFirstChild();
		String strAuthor = tAuthor.getData();
		System.out.println("����: " + strAuthor);

		Element ePublisher = (Element) eAuthor.getNextSibling();
		Text tPublisher = (Text) ePublisher.getFirstChild();
		String strPublisher = tPublisher.getData();
		System.out.println("���ǻ�: " + strPublisher);

		Element ePrice = (Element) ePublisher.getNextSibling();
		Text tPrice = (Text) ePrice.getFirstChild();
		String strPrice = tPrice.getData();
		System.out.println("����: " + strPrice);
	}

}
