package example.openapi.naver.search.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class 
 */
@WebServlet("/naver/newsSearch")
@SuppressWarnings("serial")
public class NaverNewsSearcher extends HttpServlet {
	
	private static final String naverNewsUrl_xml = "https://openapi.naver.com/v1/search/news.xml";
	private static final String naverNewsUrl_json = "https://openapi.naver.com/v1/search/news.json";
	private static final String clientId = "8ap3ahKvlPnBYb8BlV7D";
	private static final String clientSecret = "TCHbkCW4Cx";
	
	public NaverNewsSearcher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		String encKeyword = URLEncoder.encode(keyword, "UTF-8");
		
		String display = request.getParameter("display");
		
		String responseType = request.getParameter("responseType");		
		String url = (responseType.equals("html")) ? naverNewsUrl_xml : naverNewsUrl_json;		
		url += "?query=" + encKeyword + "&start=1&display=" + display;
		
		HttpClient httpClient = HttpClients.createDefault();
	
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("X-Naver-Client-Id", clientId);
        getRequest.addHeader("X-Naver-Client-Secret", clientSecret);
        
        System.out.println("Executing request: " + getRequest.getRequestLine());
        HttpResponse resp = httpClient.execute(getRequest);

        System.out.println("----------------------------------------");
        System.out.println(resp.getStatusLine());
        System.out.println("----------------------------------------");

        HttpEntity entity = resp.getEntity();
        List<News> newsList = null;
        if (entity != null) {
            InputStream content = entity.getContent();
            if (entity.getContentType().getValue().contains("xml")) {
            	newsList = parseXmlContent(content);
            	
            	// keyword 및 newsList의 참조를 result.jsp로 전달
         		request.setAttribute("keyword", keyword);
         		request.setAttribute("newsList", newsList);	
         		request.getRequestDispatcher("/WEB-INF/naver/searchResult.jsp")
         			   .forward(request, response);
         	}
            else if (entity.getContentType().getValue().contains("json")) { 
                String jsonText = readBody(content);
                System.out.println(jsonText);	// 결과 확인
                
 				response.setContentType("application/json; charset=utf-8"); 
 				response.getWriter().println(jsonText);	// JSON 형식 텍스트를 response body에 출력
            }
            content.close();
        }  				
 	}
	
	private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

	private List<News> parseXmlContent(InputStream content) { 
        System.out.println("***** Parsing by DOM *****");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);		
		Document document = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Response Message Body:");
		printDomTree(document);
       		
		NodeList itemList = document.getElementsByTagName("item");
		List<News> newsList = new ArrayList<News>(itemList.getLength());	
		
		for (int i = 0; i < itemList.getLength(); i++) {
			Element item = (Element)itemList.item(i);
			News news = new News();
			news.setNo(i+1);
			for (Node ch = item.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
				String nodeName = ch.getNodeName();
				String text = ch.getTextContent();
				switch (nodeName) {
					case "title": news.setTitle(text); break;
					case "originallink": news.setOriginallink(text); break;
					case "link": news.setLink(text); break;
					case "description": news.setDescription(text); break;
					case "pubDate": news.setPubDate(text); 					
				}				
			}			
			newsList.add(news);
		}
		return newsList;
	}

	public void printDomTree(Document doc) {
		// Transformer를 이용한 DOM tree -> XML 문서 변환(출력)
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// 출력 포맷 설정  
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "");
	
			// DOMSource 객체 생성
			DOMSource source = new DOMSource(doc);	
			// StreamResult 객체 생성
			StreamResult result =  new StreamResult(System.out);			
			// 변환 실행
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

