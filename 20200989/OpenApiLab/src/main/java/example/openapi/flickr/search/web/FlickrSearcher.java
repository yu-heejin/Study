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
  	  	conn.connect();									// Flickr API ȣ��
  	  	InputStream content = conn.getInputStream();	// ȣ�� ���(XML data)�� �б� ���� ��Ʈ�� ����		
  	  	
  	  	// DOM Parser ���� �� parsing ����
  	  	Document document = null;
  	  	try {	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true); 
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(content);			// DOM tree ����			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println("Response Message Body:");	 
		printDomTree(document);		// ��� Ȯ��
		
		NodeList nl = document.getElementsByTagName("photo");
		List<Photo> photoList = new ArrayList<Photo>(nl.getLength());
		for (int i = 0; i < nl.getLength(); i++) {
			// �� <photo>�� ���� Photo ��ü ���� �� ����
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
			photoList.add(photo);	// Photo ��ü�� list�� ����
		}
		
		if (responseType.equals("html")) {			
			// �˻� ����� searchResult.jsp�� forwarding -> HTML ���� ���� �� ��ȯ		
			request.setAttribute("tag", tag); 
			request.setAttribute("photoList", photoList); 
			request.getRequestDispatcher(
					"/WEB-INF/flickr/searchResult.jsp")
					.forward(request, response);			
		}
		else {		// respType.equals("json")			
			// �˻� ����� �����ϴ� JSON text ���� �� ��ȯ(REST API)
			// Jacson2�� �̿��� object-to-json ��ȯ ����
			ObjectMapper mapper = new ObjectMapper();
			String jsonText = mapper.writeValueAsString(photoList);
			System.out.println(jsonText);	// ��ȯ ��� Ȯ��
			
			// JSON ���� �����͸� response body��  ���
			response.setContentType("application/json;charset=UTF-8"); 
			response.getWriter().println(jsonText);				
		}	
	}
	
	public void printDomTree(Document doc) {
		// Transformer�� �̿��� DOM tree -> XML ���� ��ȯ(���)
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// ��� ���� ����  
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "");
	
			// DOMSource ��ü ����
			DOMSource source = new DOMSource(doc);	
			// StreamResult ��ü ����
			StreamResult result =  new StreamResult(System.out);			
			// ��ȯ ����
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
