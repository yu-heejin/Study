package ch10;


import org.xml.sax.*;
import java.net.*;
import java.io.*;

public class MyEntityResolver implements EntityResolver {

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		URL urlFileName = new URL(systemId);
		String fileName = URLDecoder.decode(urlFileName.getFile(), "euc-kr");
		fileName = fileName.replaceAll("/C:", "C:");

		System.out.println("\n\n\n**************************************************");
		System.out.println(fileName);
		System.out.println("**************************************************");

		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String rowData = null;
		while((rowData = br.readLine())!=null) {
			System.out.println(rowData);
		}
		br.close();
		return null;
	}

}

