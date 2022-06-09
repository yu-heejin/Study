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
        //DOM parser factory 생성
        //DocumentBuilderFactory : DocumentBuilder 객체를 생성하는 factory class
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        //DOM parser의 기능 설정
        factory.setIgnoringElementContentWhitespace(true);   //공백을 text 객체로 생성하지 않도록 함
        factory.setValidating(true);   //Validation(유효성) 검사를 실시하도록 함
        factory.setNamespaceAware(true);   //Namespace를 인식하도록 함
        
        //DOM parser 생성
        //DocumentBuilder : DOM parser 객체를 나타내는 클래스
        DocumentBuilder parser = factory.newDocumentBuilder();
        
        //로컬 디스크에 있는 XML 문서 parsing
        Document xmlDoc1 = parser.parse("C:/Temp/bml.xml");
        //웹 서버에 있는 XML 문서 parsing
        URL url = new URL("http://www.example.com/bml.xml");
        InputStream is = url.openStream();
        Document xmlDoc2 = parser.parse(is);
        
        //xml 문서 파싱 후 root element 참조 구하기
        Element eRoot = xmlDoc1.getDocumentElement();
        //루트 엘리먼트 이름 출력
        System.out.println(eRoot.getTagName());
        
        //첫번째 자식 엘리먼트 참조 구하기
        Element firstChildNode = (Element)eRoot.getFirstChild();
        
        //첫번째 자식 엘리먼트를 복사하여 마지막 자식으로 추가(shallow clone : 해당 노드만 복사)
        //Element cloneNode = firstChildNode.cloneNode(false);   -> 이렇게 하면 오류가 나는 것 같음
        //다음과 같은 두가지 방법을 사용할 수 있다
        Element cloneNode1 = (Element) firstChildNode.cloneNode(false);
        Node cloneNode2 = firstChildNode.cloneNode(false);
        eRoot.appendChild(cloneNode1);   //트리에 노드를 추가, 그 위치에 다른 형제 노드가 있으면 마지막에 노드 추가
        
        //첫번째 자식 엘리먼트를 복사하여 그 앞에 추가(즉, 맨 앞에 추가)
        cloneNode2 = firstChildNode.cloneNode(false);    //Node타입이라 타입캐스팅 오류가 안 나는 것 같다
        eRoot.insertBefore(cloneNode2, firstChildNode);
        
        //첫번째 자식 엘리먼트를 복사하여 두번째 자식 엘리먼트를 대체
        cloneNode2 = firstChildNode.cloneNode(false);
        eRoot.replaceChild(cloneNode2, firstChildNode.getNextSibling());
        
        
        //-------------------------------------------------------------------------------------------------
        
        Node node;
        //자식 노드들을 문서 순서대로 처리하는 방법
        for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            //어떤 노드의 첫번째 자식부터 시작해서 자식노드가 null일 때까지 다음 자식 노드로 계속 이용(문서 순서대로 처리)
        }
        NodeList childNodes = node.getChildNodes();   //자식 노드들을 모두 구함
        for(int i=0; i<childNodes.getLength(); i++) {   //그 길이만큼 반복
            Node child = childNodes.item(i);    //i번째 자식 노드 처리
        }
        
        //reverse order
        for(Node child = node.getLastChild(); child != null; child = child.getPreviousSibling()) {
            //어떤 노드의 마지막 자식부터 시작해서 자식노드가 null일 때까지 이전 자식 노드로 계속 이용(문서 순서 반대로 처리)
        }
        NodeList childNodes2 = node.getChildNodes();   //자식 노드들을 모두 구함
        for(int i=childNodes2.getLength(); i >= 0; i--) {   //그 길이만큼 반복
            Node child = childNodes.item(i);    //i번째 자식 노드 처리(역순)
        }
        
        //----------------------------------------------------------------------------------------------------
        
        //트리 탐색
        for(Node child = node.getFirstChild(); child != null; child.getNextSibling()) {
            //재귀 함수를 이용하여 모든 노드를 탐색
            //processNodeRecursively(child);
            //이때 매개변수는 자기 자신이면 안됨
        }
        
        //트리탐색2 : 반복자(iterator) 인터페이스 이용
        DocumentTraversal trav = (DocumentTraversal) node.getOwnerDocument();   //getOwnerDocument : 현재 노드가 속한 문서를 반환
        NodeIterator iterator = trav.createNodeIterator(node, NodeFilter.SHOW_ALL, null, false);
        while((Node child = iterator.nextNode()) != null) {
            
        }
        iterator.detach();
    }
}
