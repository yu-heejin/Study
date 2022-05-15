package tmp;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class GetRootElement {
	//xml 문서 parsing 후 루트 엘리먼트 객체 참조 + Node 인터페이스 사용 예
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
		
		//첫번째 자식 엘리먼트 참조 구하기
		Element firstChildNode = (Element) ((Node) eRoot).getFirstChild();
		
		//첫번째 자식 엘리먼트를 복사하여 마지막 자식으로 추가하기
		Element cloneNode = firstChildNode.cloneNode(false);
		eRoot.appendChild(cloneNode);
		
		//첫번째 자식 엘리먼트를 복사하여 그 앞에 추가(즉 맨 앞에 추가함)
		cloneNode = firstChildNode.cloneNode(false);
		eRoot.insertBefore(cloneNode, firstChildNode);
		
		//첫번쨰 자식 엘리먼트를 복사하여 두번째 자식 엘리먼트를 대체
		cloneNode = firstChildNode.cloneNode(false);
		eRoot.replaceChild(cloneNode, firstChildNode.getNextSibling());
		
	}
}
