package ch10;


import java.net.*;
import java.util.*;

import org.xml.sax.*;

public class MyDTDHandler implements DTDHandler {

	// Notation 선언 정보를 저장
	public Hashtable<String, NotationDecl> hNotationDecl;
	// 외부 언파스드 엔티티 정보를 저장
	public Vector<UnparsedEntityDecl> vUnparsedEntityDecl;

	// 생성자
	public MyDTDHandler() {
		hNotationDecl = new Hashtable<String, NotationDecl>();
		vUnparsedEntityDecl = new Vector<UnparsedEntityDecl>();
	}

	// Notation 선언을 만났을 때 발생하는 이벤트를 처리하는 메소드
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		NotationDecl nd = new NotationDecl(name, publicId, systemId);
		hNotationDecl.put(name, nd);
	}

	// 외부 언파스드 엔티티 선언을 만났을 때 발생하는 이벤트를 처리하는 메소드
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
		UnparsedEntityDecl ued = new UnparsedEntityDecl(name, publicId, systemId, notationName);
		vUnparsedEntityDecl.addElement(ued);
	}

	// 응용프로그램에서 이미지 출력을 하기 위한 메소드
	public void showImage() throws Exception {
		for(int i=0; i<vUnparsedEntityDecl.size(); i++) {
			UnparsedEntityDecl ued = (UnparsedEntityDecl) vUnparsedEntityDecl.elementAt(i);
			URL urlImageFile = new URL(ued.systemId);
			String imageFile = URLDecoder.decode(urlImageFile.getFile(), "euc-kr");
			imageFile = imageFile.replace("/C:", "C:");
			imageFile = imageFile.replace("/", "\\");

			NotationDecl nd = (NotationDecl) hNotationDecl.get(ued.notationName);
			URL urlHelperProgram = new URL(nd.systemId);
			String helperProgram = URLDecoder.decode(urlHelperProgram.getFile(), "euc-kr");
			helperProgram = helperProgram.replace("/C:", "C:");

			String command = helperProgram + " \"" + imageFile + "\"";
			System.out.println(command);
			Runtime.getRuntime().exec(command);
		}
	}

}

