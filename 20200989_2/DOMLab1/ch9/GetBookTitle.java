package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetBookTitle {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// å ����鸸 �̾ƿ���
		NodeList nlTitles = eRoot.getElementsByTagName("title");
		int items = nlTitles.getLength();
		for(int i=0; i<items; i++) {
			Element eTitle = (Element) nlTitles.item(i);
			Text tTitle = (Text) eTitle.getFirstChild();
			//String strTitle = tTitle.getData();
			System.out.println(tTitle.getNodeValue());
		}
	}

}
