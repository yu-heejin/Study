package example.openapi.naver.search.standalone;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class NaverNewsSearch_SAX extends DefaultHandler {
	
	private static final String clientId = "8ap3ahKvlPnBYb8BlV7D";
	private static final String clientSecret = "TCHbkCW4Cx";
    
	public static void main(String args[]) throws Exception {	
	
		String keyword = "인공지능";
		String text = URLEncoder.encode(keyword, "UTF-8");
	    String url = "https://openapi.naver.com/v1/search/news.xml?query="+ text  
	    		+ "&start=1&display=5";

	    HttpClient httpClient = HttpClients.createDefault();
	
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeader(new BasicHeader("X-Naver-Client-Id", clientId));
        getRequest.setHeader(new BasicHeader("X-Naver-Client-Secret", clientSecret));
        
        System.out.println("Executing request " + getRequest.getRequestLine());
        HttpResponse response = httpClient.execute(getRequest);

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        System.out.println("----------------------------------------");

        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream content = entity.getContent();
            try {
            	parseContent(content);            	
            } catch (Exception ex) {
                throw ex;
            } finally {
            	content.close();
            }            
        } 
	}
	
	private static void parseContent(InputStream content) throws SAXException, IOException, ParserConfigurationException { 
        System.out.println("***** Parsed by SAX *****");
        
        // 파서 생성
		SAXParserFactory factory =  SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		NaverNewsSearch_SAX handler = new NaverNewsSearch_SAX();

        parser.parse(content, handler);
	}
	
	private static int number = 1;
	private static boolean inItem = false;
	
	// 시작 태그를 만났을 때 발생하는 이벤트를 처리하는 메소드
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if(qName.equals("item")) {
			System.out.println("News #" + number++); 
			inItem = true;
		} else if (inItem) {
			System.out.print(qName + ": ");
		}		
	}

	// 문자 데이터를 만났을 때 발생하는 이벤트를 처리하는 메소드
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (inItem) {
			String content = new String(ch, start, length);
			System.out.print(content);
		}
	}

	// 끝 태그를 만났을 때 발생하는 이벤트를 처리하는 메소드
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (inItem) {
			System.out.println();
			if (qName.equals("item")) {
				System.out.println("--------------------------------");
				inItem = false;
			}
		}
	}
}