package tmp;

import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class NodeInterfaceClass {
	//�ڽ� ������ ���� ������� ó���ϴ� ���
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
		//ù��° �ڽ� ��忡�� ����Ͽ� ���� ���� ���� �̵���. null�� �� ������
	}
	
	NodeList childNodes = node.getChildNodes();
	for(int i=0; i<childNodes.getLength(); i++) {
		Node child = childNodes.item(i);
		//�ڽ� ��� ����Ʈ�� ������ �� ���������� ����
	}
	//reverse order
	for(Node child = node.getLastChild(); child != null; child = child.getPreviousSibling()) {
	
	}
	
	NodeList childNodes = node.getChildNodes();
	for(int i=childNodes.getLength(); i>=0; i--) {
		Node child = childNodes.item(i);
	}
	
	//tree traversal(Ʈ�� Ž��)
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
		processNodeRecursively(child);
	}
	
	//Ʈ�� Ž��2 : �ݺ��� �������̽� �̿�
	DocumentTraversal trav = (DocumentTraversal) node.getOwnerDocument();
	NodeIterator iterator = trav.createNodeIterator(node, NodeFilter.SHOW_ALL, null, false);
	while((Node child = iterator.nextNode()) != null) {
		
	}
	iterator.detach();   //�ش� ��Ҹ� �������� ������
	
	
}
