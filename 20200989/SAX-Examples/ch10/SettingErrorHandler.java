package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingErrorHandler {
	public static void main(String args[]) throws Exception {	
		//파서 공장 생성
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//Namespace 인식 기능 사용
		factory.setNamespaceAware(true);
		//DTD Validating 기능 사용
		factory.setValidating(true);
		//XML Schema Validating(Namespace 인식 기능 사용이 전제)
		factory.setFeature("http://apache.org/xml/features/validation/schema",true);	
					 
		//파서 생성
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		
		//이벤트 핸들러 생성 
		ErrorHandler errorHandler = new MyErrorHandler();
		
		//이벤트 핸들러를 파서에 등록
		reader.setErrorHandler(errorHandler);
		
		//하드 디스크에 저장된 XML 문서 해석
		reader.parse("ch10/bml.xml");	
		
		System.out.println("유효한 문서 입니다.");
	}	
}