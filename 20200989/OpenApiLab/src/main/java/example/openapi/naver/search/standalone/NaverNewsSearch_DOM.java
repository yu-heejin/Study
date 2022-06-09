package example.openapi.naver.search.standalone;

import java.io.InputStream;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NaverNewsSearch_DOM {

	private static final String clientId = "8ap3ahKvlPnBYb8BlV7D";
	private static final String clientSecret = "TCHbkCW4Cx";
    
	public static void main(String[] args) throws Exception {
   
		String keyword = "인공지능";
		String text = URLEncoder.encode(keyword, "UTF-8");
	    String url = "https://openapi.naver.com/v1/search/news.xml?query="+ text  
	    		+ "&start=1&display=21";

	    HttpClient httpClient = HttpClients.createDefault();
	
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("X-Naver-Client-Id", clientId);
        getRequest.addHeader("X-Naver-Client-Secret", clientSecret);
        
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
	
	private static void parseContent(InputStream content) throws Exception { 
        System.out.println("***** Parsed by DOM *****");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);		
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(content);
		
		System.out.println("Response Message Body:");
		printDomTree(document);
       			
		System.out.println("\nParsed Results:");
		NodeList itemList = document.getElementsByTagName("item");
		for (int i = 0; i < itemList.getLength(); i++) {
			Element item = (Element)itemList.item(i);
			NodeList nl = item.getChildNodes();
			System.out.println("News #" + (i+1)); 
			for (int j = 0; j < nl.getLength(); j++) {
				Element elm = (Element)nl.item(j);
				System.out.println(elm.getNodeName() + ": " + elm.getTextContent());	        
			}
			System.out.println("--------------------------------");
		}
	}
	
	public static void printDomTree(Document doc) throws Exception {
		// 변환기 생성
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// 출력 포맷 설정: XML 선언과 문서 유형 선언 내용 설정하기 
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "");

		// DOMSource 객체 생성
		DOMSource source = new DOMSource(doc);
		// StreamResult 객체 생성
		StreamResult result =  new StreamResult(System.out);
		transformer.transform(source, result);
	}	
}
	
