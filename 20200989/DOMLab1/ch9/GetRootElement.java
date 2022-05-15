package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class GetRootElement {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		// ��Ʈ ������Ʈ ���� ���
		Element eRoot = document.getDocumentElement();

		// ��Ʈ ������Ʈ �̸� ���
		System.out.println(eRoot.getTagName());
	}

}
