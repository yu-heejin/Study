import java.io.InputStream;
import java.net.URL;
//import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class FinalTestClass {
    public static void main(String[] args) throws Exception {
        //DOM parser factory ����
        //DocumentBuilderFactory : DocumentBuilder ��ü�� �����ϴ� factory class
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        //DOM parser�� ��� ����
        factory.setIgnoringElementContentWhitespace(true);   //������ text ��ü�� �������� �ʵ��� ��
        factory.setValidating(true);   //Validation(��ȿ��) �˻縦 �ǽ��ϵ��� ��
        factory.setNamespaceAware(true);   //Namespace�� �ν��ϵ��� ��
        
        //DOM parser ����
        //DocumentBuilder : DOM parser ��ü�� ��Ÿ���� Ŭ����
        DocumentBuilder parser = factory.newDocumentBuilder();
        
        //���� ��ũ�� �ִ� XML ���� parsing
        Document xmlDoc1 = parser.parse("C:/Temp/bml.xml");
        //�� ������ �ִ� XML ���� parsing
        URL url = new URL("http://www.example.com/bml.xml");
        InputStream is = url.openStream();
        Document xmlDoc2 = parser.parse(is);
        
        //xml ���� �Ľ� �� root element ���� ���ϱ�
        Element eRoot = xmlDoc1.getDocumentElement();
        //��Ʈ ������Ʈ �̸� ���
        System.out.println(eRoot.getTagName());
        
        //ù��° �ڽ� ������Ʈ ���� ���ϱ�
        Element firstChildNode = (Element)eRoot.getFirstChild();
        
        //ù��° �ڽ� ������Ʈ�� �����Ͽ� ������ �ڽ����� �߰�(shallow clone : �ش� ��常 ����)
        //Element cloneNode = firstChildNode.cloneNode(false);   -> �̷��� �ϸ� ������ ���� �� ����
        //������ ���� �ΰ��� ����� ����� �� �ִ�
        Element cloneNode1 = (Element) firstChildNode.cloneNode(false);
        Node cloneNode2 = firstChildNode.cloneNode(false);
        eRoot.appendChild(cloneNode1);   //Ʈ���� ��带 �߰�, �� ��ġ�� �ٸ� ���� ��尡 ������ �������� ��� �߰�
        
        //ù��° �ڽ� ������Ʈ�� �����Ͽ� �� �տ� �߰�(��, �� �տ� �߰�)
        cloneNode2 = firstChildNode.cloneNode(false);    //NodeŸ���̶� Ÿ��ĳ���� ������ �� ���� �� ����
        eRoot.insertBefore(cloneNode2, firstChildNode);
        
        //ù��° �ڽ� ������Ʈ�� �����Ͽ� �ι�° �ڽ� ������Ʈ�� ��ü
        cloneNode2 = firstChildNode.cloneNode(false);
        eRoot.replaceChild(cloneNode2, firstChildNode.getNextSibling());
        
        
        //-------------------------------------------------------------------------------------------------
        
        Node node;
        //�ڽ� ������ ���� ������� ó���ϴ� ���
        for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            //� ����� ù��° �ڽĺ��� �����ؼ� �ڽĳ�尡 null�� ������ ���� �ڽ� ���� ��� �̿�(���� ������� ó��)
        }
        NodeList childNodes = node.getChildNodes();   //�ڽ� ������ ��� ����
        for(int i=0; i<childNodes.getLength(); i++) {   //�� ���̸�ŭ �ݺ�
            Node child = childNodes.item(i);    //i��° �ڽ� ��� ó��
        }
        
        //reverse order
        for(Node child = node.getLastChild(); child != null; child = child.getPreviousSibling()) {
            //� ����� ������ �ڽĺ��� �����ؼ� �ڽĳ�尡 null�� ������ ���� �ڽ� ���� ��� �̿�(���� ���� �ݴ�� ó��)
        }
        NodeList childNodes2 = node.getChildNodes();   //�ڽ� ������ ��� ����
        for(int i=childNodes2.getLength(); i >= 0; i--) {   //�� ���̸�ŭ �ݺ�
            Node child = childNodes.item(i);    //i��° �ڽ� ��� ó��(����)
        }
        
        //----------------------------------------------------------------------------------------------------
        
        //Ʈ�� Ž��
        for(Node child = node.getFirstChild(); child != null; child.getNextSibling()) {
            //��� �Լ��� �̿��Ͽ� ��� ��带 Ž��
            //processNodeRecursively(child);
            //�̶� �Ű������� �ڱ� �ڽ��̸� �ȵ�
        }
        
        //Ʈ��Ž��2 : �ݺ���(iterator) �������̽� �̿�
        DocumentTraversal trav = (DocumentTraversal) node.getOwnerDocument();   //getOwnerDocument : ���� ��尡 ���� ������ ��ȯ
        NodeIterator iterator = trav.createNodeIterator(node, NodeFilter.SHOW_ALL, null, false);
        while((Node child = iterator.nextNode()) != null) {
            
        }
        iterator.detach();
    }
}
