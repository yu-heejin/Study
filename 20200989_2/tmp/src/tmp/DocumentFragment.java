package tmp;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class DocumentFragment {
	public static void main(String[] args) throws Exception {
		//DOM parser 생성하기
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		//xml 문서 parsing
		Document document = parser.parse("hello");
		
		//Document Fragment 생성
		DocumentFragment docFrag = (DocumentFragment) document.createDocumentFragment();
		Element e = (Element) document.createElement("출판일");
		Text t = document.createTextNode("2013/05/01");
		e.appendChild(t);
		docFrag.appendChild(e);    //서브트리 생성
		
		//DocumentFragment를 첫번째 book의 마지막 child로 삽입
		document.getDocumentElement().getFirstChild().appendChild(docFrag);
	}

}
