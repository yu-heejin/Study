package tmp;

import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class NodeInterfaceClass {
	//자식 노드들을 문서 순서대로 처리하는 방법
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
		//첫번째 자식 노드에서 출발하여 다음 형제 노드로 이동함. null이 될 때까지
	}
	
	NodeList childNodes = node.getChildNodes();
	for(int i=0; i<childNodes.getLength(); i++) {
		Node child = childNodes.item(i);
		//자식 노드 리스트를 가져온 후 순차적으로 접근
	}
	//reverse order
	for(Node child = node.getLastChild(); child != null; child = child.getPreviousSibling()) {
	
	}
	
	NodeList childNodes = node.getChildNodes();
	for(int i=childNodes.getLength(); i>=0; i--) {
		Node child = childNodes.item(i);
	}
	
	//tree traversal(트리 탐색)
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
		processNodeRecursively(child);
	}
	
	//트리 탐색2 : 반복자 인터페이스 이용
	DocumentTraversal trav = (DocumentTraversal) node.getOwnerDocument();
	NodeIterator iterator = trav.createNodeIterator(node, NodeFilter.SHOW_ALL, null, false);
	while((Node child = iterator.nextNode()) != null) {
		
	}
	iterator.detach();   //해당 요소를 문서에서 제거함
	
	
}
