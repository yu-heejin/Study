package example.openapi.naver.search.standalone;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NaverNewsSearch_JSON {

	private static final String clientId = "8ap3ahKvlPnBYb8BlV7D";
	private static final String clientSecret = "TCHbkCW4Cx";
    
	public static void main(String[] args) throws Exception {
   
		String keyword = "인공지능";
		String text = URLEncoder.encode(keyword, "UTF-8");
	    String url = "https://openapi.naver.com/v1/search/news.json?query="+ text  
	    		+ "&start=1&display=5";

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
		
        System.out.println("***** Parsing JSON text *****");
         
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject)parser.parse(new InputStreamReader(content, "UTF-8"));       		
        JSONArray items = (JSONArray) jsonObj.get("items");

    	System.out.println("\nParsed Results:");
        for (int i = 0; i < items.size(); i++) {
        	JSONObject item = (JSONObject)items.get(i);
			System.out.println("News #" + (i+1));
			Set<String> propertySet = item.keySet();
			Iterator<String> iter = propertySet.iterator();
			while (iter.hasNext()) {		        
				String propertyName = iter.next();
		        System.out.println(propertyName + ": " + item.get(propertyName));	 
			}
			System.out.println("--------------------------------");
		}	
	}	
}
