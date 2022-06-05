package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingDTDHandler {
	public static void main(String args[]) throws Exception {	
		// 파서 생성
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		// DTDHandler 객체 생성
		MyDTDHandler dtdHandler = new MyDTDHandler();

		// contentHandler 객체 등록
		reader.setDTDHandler(dtdHandler);

		// 파싱 지시
		reader.parse("ch10/bml.xml");

		// 책이미지 보기
		dtdHandler.showImage();
	}	
}