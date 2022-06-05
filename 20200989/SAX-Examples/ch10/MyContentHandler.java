package ch10;


import org.xml.sax.*;

public class MyContentHandler implements ContentHandler {

	public void setDocumentLocator(Locator locator) {}
	public void startDocument() throws SAXException {}
	public void endDocument() throws SAXException {}
	public void startPrefixMapping(String prefix, String uri) throws SAXException {}
	public void endPrefixMapping(String prefix) throws SAXException {}

	// ���� �±׸� ������ �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� �޼ҵ�
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if(qName.equals("booklist")) {
			System.out.println(qName);
		} else if(qName.equals("book")) {
			System.out.println("--------------------------------");
			System.out.println("kind: " + atts.getValue("kind"));
		} else {
			System.out.print(qName + ": ");
		}		
	}

	// �� �±׸� ������ �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� �޼ҵ�
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println();
	}

	// ���� �����͸� ������ �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� �޼ҵ�
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		System.out.print(content);
	}

	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
	public void processingInstruction(String target, String data) throws SAXException {}
	public void skippedEntity(String name) throws SAXException {}

}