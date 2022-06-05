package ch10;


import javax.xml.parsers.*;
import org.xml.sax.*;

public class SettingEntityResolver {
	public static void main(String args[]) throws Exception {	
		// 颇辑 积己
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		// EntityResolver 按眉 积己
		EntityResolver entityResolver = new MyEntityResolver();

		// EntityResolver 按眉 殿废
		reader.setEntityResolver(entityResolver);

		// 颇教 瘤矫
		reader.parse("ch10/bml.xml");
	}	
}