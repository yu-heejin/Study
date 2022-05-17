package ch9;

import javax.xml.parsers.*;
import org.w3c.dom.*;

public class AppendNewBook {

	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true); 
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse("ch9/bml.xml");

		//��Ʈ ������Ʈ ��ü���� ���
		Element eRoot = document.getDocumentElement();

			//book ������Ʈ ��ü ����
			Element eBook = document.createElement("book");

				//title ������Ʈ ��ü ���� �� ���̱�
				Element eTitle = document.createElement("title");
					Text tTitle = document.createTextNode("���ΰ�����");
					eTitle.appendChild(tTitle);

				//author ������Ʈ ��ü ���� �� ���̱�
				Element eAuthor = document.createElement("author");
					Text tAuthor = document.createTextNode("�赵��");
					eAuthor.appendChild(tAuthor);

				//publisher ������Ʈ ��ü ���� �� ���̱�
				Element ePublisher = document.createElement("publisher");
					Text tPublisher = document.createTextNode("�������ǻ�");
					ePublisher.appendChild(tPublisher);

				//price ������Ʈ ��ü ���� ���� �� ���̱�
				Element ePrice = document.createElement("price");
					Text tPrice = document.createTextNode("9000");
					ePrice.appendChild(tPrice);

			//�ڽ� ������Ʈ ��ü�� book ������Ʈ ��ü�� ���̱�
			eBook.appendChild(eTitle);
			eBook.appendChild(eAuthor);
			eBook.appendChild(ePublisher);
			eBook.appendChild(ePrice);

			//�Ӽ� ��ü�� book ������Ʈ ��ü�� ���̱�
			eBook.setAttribute("kind", "�Ҽ�");

		//book ������Ʈ ��ü�� ��Ʈ ������Ʈ ��ü�� ���̱�
		eRoot.appendChild(eBook);

		System.out.println("�߰� ����");
	}

}
