package ch10;


import java.net.*;
import java.util.*;

import org.xml.sax.*;

public class MyDTDHandler implements DTDHandler {

	// Notation ���� ������ ����
	public Hashtable<String, NotationDecl> hNotationDecl;
	// �ܺ� ���Ľ��� ��ƼƼ ������ ����
	public Vector<UnparsedEntityDecl> vUnparsedEntityDecl;

	// ������
	public MyDTDHandler() {
		hNotationDecl = new Hashtable<String, NotationDecl>();
		vUnparsedEntityDecl = new Vector<UnparsedEntityDecl>();
	}

	// Notation ������ ������ �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� �޼ҵ�
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {
		NotationDecl nd = new NotationDecl(name, publicId, systemId);
		hNotationDecl.put(name, nd);
	}

	// �ܺ� ���Ľ��� ��ƼƼ ������ ������ �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� �޼ҵ�
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
		UnparsedEntityDecl ued = new UnparsedEntityDecl(name, publicId, systemId, notationName);
		vUnparsedEntityDecl.addElement(ued);
	}

	// �������α׷����� �̹��� ����� �ϱ� ���� �޼ҵ�
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

