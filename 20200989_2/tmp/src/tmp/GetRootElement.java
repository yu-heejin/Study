package tmp;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class GetRootElement {
	//xml ���� parsing �� ��Ʈ ������Ʈ ��ü ���� + Node �������̽� ��� ��
	public static void main(String[] args) throws Exception {
		//DOM parser create
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//xml document parsing
		Document document = builder.parse("tmp/bml.xml");  //Relative Path
		
		//root element reference
		Element eRoot = (Element) document.getDocumentElement();
		System.out.println(((Node) eRoot).getNodeName());   //print root element name
		
		//print root element name
		System.out.println(((org.w3c.dom.Element) eRoot).getTagName());
		
		//ù��° �ڽ� ������Ʈ ���� ���ϱ�
		Element firstChildNode = (Element) ((Node) eRoot).getFirstChild();
		
		//ù��° �ڽ� ������Ʈ�� �����Ͽ� ������ �ڽ����� �߰��ϱ�
		Element cloneNode = firstChildNode.cloneNode(false);
		eRoot.appendChild(cloneNode);
		
		//ù��° �ڽ� ������Ʈ�� �����Ͽ� �� �տ� �߰�(�� �� �տ� �߰���)
		cloneNode = firstChildNode.cloneNode(false);
		eRoot.insertBefore(cloneNode, firstChildNode);
		
		//ù���� �ڽ� ������Ʈ�� �����Ͽ� �ι�° �ڽ� ������Ʈ�� ��ü
		cloneNode = firstChildNode.cloneNode(false);
		eRoot.replaceChild(cloneNode, firstChildNode.getNextSibling());
		
	}
}
