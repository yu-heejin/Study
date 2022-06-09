package mondial;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;

public class processMondial {
	static final String inputFile = "mondial/mondial-sample.xml";  	
									// ���߿� "mondial/mondial.xml"�� �����ؼ� �׽�Ʈ 
	static final String outputFile = "mondial/result.xml";
	
	public static void main(String[] args) throws Exception {
		// DOM �ļ� ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML ���� �Ľ��ϱ�
		Document document = builder.parse(inputFile);

		Element mondial = document.getDocumentElement();
		
		// ����� �α��� ��� �� ��� (3��)
		computePopulationsOfContinents(mondial);					
		
		// ������ ���� ���� ��� �� ��� (4��)
		// computeBelieversOfReligions(mondial);	
				
		// ������ ���� �˻� �� ��� (1��)
		processCountries1(mondial);	
		
		// ������ ���� �˻� �� DOM Ʈ�� ���� (2��)
		processCountries2(mondial);	
		
		// ó�� ��� ����� ���� ��ȯ�� ����
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// ��� ���� ����: XML ����� ���� ���� ���� ���� �����ϱ�
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		// transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
		// "mondial.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// DOMSource ��ü ����
		DOMSource source = new DOMSource(document);

		// StreamResult ��ü ����
		StreamResult result = new StreamResult(new File(outputFile));

		// ���Ϸ� �����ϱ�
		transformer.transform(source, result);
		
		System.out.println();
		System.out.println(outputFile + "�� ����Ǿ����ϴ�.");
	}

	public static void processCountries1(Element mondial) {
				
		
	} 
	
	public static void processCountries2(Element mondial) {
				
		
	} 
	
	public static void computePopulationsOfContinents(Node mondial) {

		// ...
		
		// ���� �� ����� �α��� ���
		printPopulationsOfContinents();
	}

	public static void printPopulationsOfContinents() {
	
	}
	
}