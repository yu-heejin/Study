package example.openapi.flickr.search.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class
 */
@WebServlet("/flickr/search")
@SuppressWarnings({"serial"})
public class FlickrSearcher extends HttpServlet {
    private static final String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=";
	private static final String apiKey = "dfccfd290d9e710d952ba7ad296f4e47";
   
    public FlickrSearcher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String tag = request.getParameter("tag");
		String perPage = request.getParameter("perPage");
		String responseType = request.getParameter("responseType");
		
	    String searchUrl = url + apiKey + "&tags=" + tag + "&per_page=" + perPage;
	    System.out.println("search URL: " + searchUrl);
	    
	    URL url = new URL(searchUrl);
  	  	HttpURLConnection conn = (HttpURLConnection)url.openConnection();
  	  	conn.connect();									// Flickr API 호출
  	  	InputStream content = conn.getInputStream();	// 호출 결과(XML data)를 읽기 위한 스트림 생성		
  	  	
  	  	// DOM Parser 생성 및 parsing 수행
  	  	Document document = null;
  	  	try {	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true); 
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(content);			// DOM tree 생성			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println("Response Message Body:");	 
		printDomTree(document);		// 결과 확인
		
		NodeList nl = document.getElementsByTagName("photo");
		List<Photo> photoList = new ArrayList<Photo>(nl.getLength());
		for (int i = 0; i < nl.getLength(); i++) {
			// 각 <photo>에 대해 Photo 객체 생성 및 저장
			Element p = (Element)nl.item(i);			
			Photo photo = new Photo(
							p.getAttribute("id"),
							p.getAttribute("owner"),
							p.getAttribute("secret"),							
							p.getAttribute("title"),
							p.getAttribute("farm"),
							p.getAttribute("server"),
							null, null);
			photo.setPageUrl("http://www.flickr.com/photos/" 
							+ photo.getOwner() + "/" + photo.getId());
			photo.setThumbUrl("http://farm" +  photo.getFarm()
							+ ".static.flickr.com/" +  photo.getServer() 
							+ "/" + photo.getId() + "_" + photo.getSecret() + "_t.jpg");
			photoList.add(photo);	// Photo 객체를 list에 저장
		}
		
		if (responseType.equals("html")) {			
			// 검색 결과를 searchResult.jsp로 forwarding -> HTML 문서 생성 및 반환		
			request.setAttribute("tag", tag); 
			request.setAttribute("photoList", photoList); 
			request.getRequestDispatcher(
					"/WEB-INF/flickr/searchResult.jsp")
					.forward(request, response);			
		}
		else {		// respType.equals("json")			
			// 검색 결과를 포함하는 JSON text 생성 및 반환(REST API)
			// Jacson2를 이용한 object-to-json 변환 실행
			ObjectMapper mapper = new ObjectMapper();
			String jsonText = mapper.writeValueAsString(photoList);
			System.out.println(jsonText);	// 변환 결과 확인
			
			// JSON 형식 데이터를 response body에  출력
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().println(jsonText);				
		}	
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
