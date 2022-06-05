package ch10;


import org.xml.sax.*;

public class MyErrorHandler implements ErrorHandler {

	// Well-formed ������ Validation ���� �̿��� ��� �̺�Ʈ�� �߻����� �� ����
	public void warning(SAXParseException exception) throws SAXException {
		throw new SAXException("warning �̺�Ʈ ó��");
	}

	// Validation ���� �̺�Ʈ�� �߻����� �� ����
	public void error(SAXParseException exception) throws SAXException {
		System.out.println("DTD �Ǵ� XML Schema ���� ������ ����˴ϴ�.");
		System.out.println("��ȿ���� �ʴ� ���� �Դϴ�.");   
		System.out.println("���� ���� -> " + exception.getMessage());
		System.out.println("���� �߻� �� -> "+ exception.getLineNumber());
		System.out.println("���� �߻� �� -> "+ exception.getColumnNumber());		
		throw new SAXException(exception);
	}

	// Well-formed ���� �̺�Ʈ�� �߻����� �� ����
	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("XML �ǰ���� ������ ��Ű�� �ʾҽ��ϴ�.");
		System.out.println("�� ¥���� XML ������ �ƴմϴ�."); 
		System.out.println("���� ���� -> " + exception.getMessage());
		System.out.println("���� �߻� �� -> "+ exception.getLineNumber());
		System.out.println("���� �߻� �� -> "+ exception.getColumnNumber());
		throw new SAXException(exception);
	}	

}
