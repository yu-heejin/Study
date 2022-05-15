package mondial;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.io.*;

public class processMondial {
	static final String inputFile = "mondial/mondial-sample.xml";  	
									// 나중에 "mondial/mondial.xml"로 변경해서 테스트 
	static final String outputFile = "mondial/result.xml";
	
	public static void main(String[] args) throws Exception {
		// DOM 파서 생성
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		// XML 문서 파싱하기
		Document document = builder.parse(inputFile);

		Element mondial = document.getDocumentElement();
		
		// 대륙별 인구를 계산 및 출력 (3번)
		computePopulationsOfContinents(mondial);					
		
		// 종교별 신자 수를 계산 및 출력 (4번)
		// computeBelieversOfReligions(mondial);	
				
		// 국가별 정보 검색 및 출력 (1번)
		processCountries1(mondial);	
		
		// 국가별 정보 검색 및 DOM 트리 수정 (2번)
		processCountries2(mondial);	
		
		// 처리 결과 출력을 위한 변환기 생성
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// 출력 포맷 설정: XML 선언과 문서 유형 선언 내용 설정하기
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		// transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
		// "mondial.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		// DOMSource 객체 생성
		DOMSource source = new DOMSource(document);

		// StreamResult 객체 생성
		StreamResult result = new StreamResult(new File(outputFile));

		// 파일로 저장하기
		transformer.transform(source, result);
		
		System.out.println();
		System.out.println(outputFile + "로 저장되었습니다.");
	}

	public static void processCountries1(Element mondial) {
				
		
	} 
	
	public static void processCountries2(Element mondial) {
				
		
	} 
	
	public static void computePopulationsOfContinents(Node mondial) {

		// ...
		
		// 계산된 각 대륙의 인구를 출력
		printPopulationsOfContinents();
	}

	public static void printPopulationsOfContinents() {
	
	}
	
}