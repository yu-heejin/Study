package mondial;

import java.text.NumberFormat;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* 
 * mondial.xml에서 대륙별 인구를 구해 출력하는 프로그램 (SAX API 이용)
 * - 각 대륙에 대해 그것에 속한 국가들의 인구 값을 합산 
 * - 단, 여러 대륙에 동시에 속한 국가의 경우(러시아, 터키 등) 소속 비율이 가장 큰 대륙에만 속하는 것으로 간주함  
 */
public class ComputePopulations extends DefaultHandler {	// DefaultHandler를 상속: 4개의  SAX interface들에 포함된 메소드들 중 필요한 것들만 재정의 가능 

	static final String inputFile = "mondial/mondial.xml";
	static final String continent[] = new String[] {"Europe", "Asia", "America", "Africa", "Australia"};
	long pop[] = new long[5];	// 위 continent 배열에 저장된 대륙들의 인구 값들을 차례대로 저장하기 위한 배열
	private long population;	// 한 국가의 population 값을 저장하기 위한 변수
	private boolean inCountry = false, inProv = false, inCity = false, inCountryPop = false;    // flag 변수들
	private double maxPer;			// 한 국가가 속한 대륙의 percentage의 max 값 
	private String maxContinent;	// 한 국가가 가장 많이 속한 대륙
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("country")) {		// <country> 시작 태그를 발견
			inCountry = true;
			maxPer = 0.0;	// maxPer 초기화
		} 
		else if (qName.equals("province")) {   // <province> 시작 태그를 발견
			inProv = true;
		} 
		else if (qName.equals("city")) { 		// <city> 시작 태그를 발견
			inCity = true;
		} 
		else if (qName.equals("population")) {	// <population> 시작 태그를 발견
			if (inCountry && !inProv && !inCity) {	// <country>의 자식인 <population>
				inCountryPop = true;		
			}
		} 
		else if (qName.equals("encompassed")) {   // <country>의 자식인 <encompassed>를 발견
			findMaxContinent(attributes);	 // <encompassed>의 모든 속성들을 파라미터로 전달
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (inCountryPop) {		// <country>의 자식인 <population>의 콘텐트에 속하는 텍스트 데이터를 발견한 경우
			population = Long.parseLong(new String(ch, start, length));   // 인구 값 추출 (long type 숫자로 변환)
			inCountryPop = false;     //  flag down
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("country")) {  // 이제  country 엘리먼트의 콘텐트 영역을 지나감
			if (maxPer > 0) {
				switch (maxContinent) {
					case "europe": 
						pop[0] += population; break;        		        	 
					case "asia":
						pop[1] += population; break;       	
					case "america":
			   			pop[2] += population; break;         	
					case "africa":
			   			pop[3] += population; break;        	
					case "australia":
			   			pop[4] += population;     
				}
			}
			inCountry = false;	
		}
		else if (qName.equals("province")) { // 이제  province 엘리먼트의 콘텐트 영역을 지나감
			inProv = false;  
		} 
		else if (qName.equals("city")) {  // 이제  city 엘리먼트의 콘텐트 영역을 지나감
			inCity = false; 
		}
	}

	public void findMaxContinent(Attributes attributes) {
		// percentage가 max인 continent 찾기
		double per = Double.parseDouble(attributes.getValue("percentage"));
		if (per > maxPer) {
			maxPer = per;
			maxContinent = attributes.getValue("continent");
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		// 문서의 끝에 도달했을 때 이제까지 계산된 인구 값들이 최종 결과이므로 출력
		long total = 0L;
		for (int i = 0; i < pop.length; i++) {
			total += pop[i];
		}
		NumberFormat formatter = NumberFormat.getInstance();

		System.out.println("Populations of the Continents:");
		System.out.println("================================");
		for (int i = 0; i < pop.length; i++) {
			System.out.println(continent[i] + ": " + formatter.format(pop[i]) + "명(" + formatter.format(pop[i] * 100.0 / total) + "%)");
		}
		System.out.println("================================");
		System.out.println("Total: " + formatter.format(total) + "명");
	}

	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			ComputePopulations handler = new ComputePopulations();
			parser.parse(inputFile, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
