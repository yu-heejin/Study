package mondial;

import java.text.NumberFormat;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* 
 * mondial.xml���� ����� �α��� ���� ����ϴ� ���α׷� (SAX API �̿�)
 * - �� ����� ���� �װͿ� ���� �������� �α� ���� �ջ� 
 * - ��, ���� ����� ���ÿ� ���� ������ ���(���þ�, ��Ű ��) �Ҽ� ������ ���� ū ������� ���ϴ� ������ ������  
 */
public class ComputePopulations extends DefaultHandler {	// DefaultHandler�� ���: 4����  SAX interface�鿡 ���Ե� �޼ҵ�� �� �ʿ��� �͵鸸 ������ ���� 

	static final String inputFile = "mondial/mondial.xml";
	static final String continent[] = new String[] {"Europe", "Asia", "America", "Africa", "Australia"};
	long pop[] = new long[5];	// �� continent �迭�� ����� ������� �α� ������ ���ʴ�� �����ϱ� ���� �迭
	private long population;	// �� ������ population ���� �����ϱ� ���� ����
	private boolean inCountry = false, inProv = false, inCity = false, inCountryPop = false;    // flag ������
	private double maxPer;			// �� ������ ���� ����� percentage�� max �� 
	private String maxContinent;	// �� ������ ���� ���� ���� ���
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("country")) {		// <country> ���� �±׸� �߰�
			inCountry = true;
			maxPer = 0.0;	// maxPer �ʱ�ȭ
		} 
		else if (qName.equals("province")) {   // <province> ���� �±׸� �߰�
			inProv = true;
		} 
		else if (qName.equals("city")) { 		// <city> ���� �±׸� �߰�
			inCity = true;
		} 
		else if (qName.equals("population")) {	// <population> ���� �±׸� �߰�
			if (inCountry && !inProv && !inCity) {	// <country>�� �ڽ��� <population>
				inCountryPop = true;		
			}
		} 
		else if (qName.equals("encompassed")) {   // <country>�� �ڽ��� <encompassed>�� �߰�
			findMaxContinent(attributes);	 // <encompassed>�� ��� �Ӽ����� �Ķ���ͷ� ����
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (inCountryPop) {		// <country>�� �ڽ��� <population>�� ����Ʈ�� ���ϴ� �ؽ�Ʈ �����͸� �߰��� ���
			population = Long.parseLong(new String(ch, start, length));   // �α� �� ���� (long type ���ڷ� ��ȯ)
			inCountryPop = false;     //  flag down
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("country")) {  // ����  country ������Ʈ�� ����Ʈ ������ ������
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
		else if (qName.equals("province")) { // ����  province ������Ʈ�� ����Ʈ ������ ������
			inProv = false;  
		} 
		else if (qName.equals("city")) {  // ����  city ������Ʈ�� ����Ʈ ������ ������
			inCity = false; 
		}
	}

	public void findMaxContinent(Attributes attributes) {
		// percentage�� max�� continent ã��
		double per = Double.parseDouble(attributes.getValue("percentage"));
		if (per > maxPer) {
			maxPer = per;
			maxContinent = attributes.getValue("continent");
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		// ������ ���� �������� �� �������� ���� �α� ������ ���� ����̹Ƿ� ���
		long total = 0L;
		for (int i = 0; i < pop.length; i++) {
			total += pop[i];
		}
		NumberFormat formatter = NumberFormat.getInstance();

		System.out.println("Populations of the Continents:");
		System.out.println("================================");
		for (int i = 0; i < pop.length; i++) {
			System.out.println(continent[i] + ": " + formatter.format(pop[i]) + "��(" + formatter.format(pop[i] * 100.0 / total) + "%)");
		}
		System.out.println("================================");
		System.out.println("Total: " + formatter.format(total) + "��");
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
