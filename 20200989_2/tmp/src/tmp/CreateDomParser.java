package tmp;

import java.io.InputStream;
import java.net.URL;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CreateDomParser {
	public static void main(String[] args) throws Exception {
		//DOM parser factory 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		//기능 설정 부분
		//whiteSpace(공백형태)를 text 객체로 생성하지 않도록 함
		factory.setIgnoringElementContentWhitespace(true);
		//validation(유효성) 검사를 실시하도록 한다
		factory.setValidating(true);
		//Namespace를 인식하도록 한다
		factory.setNamespaceAware(true);
		
		//DOM parser 생성
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		//XML 문서의 parsing
		//ex1. 로컬 디스크에 있는 xml 문서 parsing
		Document xmlDoc = (Document) parser.parse("C:/Tmp/bml/xml");  //경로를 찾아서 parsing
		//ex2. 웹 서버에 있는 XML 문서에 대한 parsing
		URL url = new URL("http://www.example.com/bml.xml");  //url을 가져온다
		InputStream is = url.openStream();   //입력 스트림을 연다
		//데이터가 전송되는 통로 역할을 함, 데이터를 byte 단위로 읽어들이는 통로가 됨
		Document xmlDoc1 = (Document) parser.parse(is);
	}
}
