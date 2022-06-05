package ch10;


import org.xml.sax.*;

public class MyErrorHandler implements ErrorHandler {

	// Well-formed 에러와 Validation 에러 이외의 경고 이벤트가 발생했을 때 실행
	public void warning(SAXParseException exception) throws SAXException {
		throw new SAXException("warning 이벤트 처리");
	}

	// Validation 에러 이벤트가 발생했을 때 실행
	public void error(SAXParseException exception) throws SAXException {
		System.out.println("DTD 또는 XML Schema 문서 구조에 위배됩니다.");
		System.out.println("유효하지 않는 문서 입니다.");   
		System.out.println("오류 원인 -> " + exception.getMessage());
		System.out.println("오류 발생 행 -> "+ exception.getLineNumber());
		System.out.println("오류 발생 열 -> "+ exception.getColumnNumber());		
		throw new SAXException(exception);
	}

	// Well-formed 에러 이벤트가 발생했을 때 실행
	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("XML 권고안의 내용을 지키지 않았습니다.");
		System.out.println("잘 짜여진 XML 문서가 아닙니다."); 
		System.out.println("오류 원인 -> " + exception.getMessage());
		System.out.println("오류 발생 행 -> "+ exception.getLineNumber());
		System.out.println("오류 발생 열 -> "+ exception.getColumnNumber());
		throw new SAXException(exception);
	}	

}
