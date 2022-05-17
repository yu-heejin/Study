package tmp;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class DocumentFragment {
	public static void main(String[] args) throws Exception {
		//DOM parser �����ϱ�
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		//xml ���� parsing
		Document document = parser.parse("hello");
		
		//Document Fragment ����
		DocumentFragment docFrag = (DocumentFragment) document.createDocumentFragment();
		Element e = (Element) document.createElement("������");
		Text t = document.createTextNode("2013/05/01");
		e.appendChild(t);
		docFrag.appendChild(e);    //����Ʈ�� ����
		
		//DocumentFragment�� ù��° book�� ������ child�� ����
		document.getDocumentElement().getFirstChild().appendChild(docFrag);
	}

}
