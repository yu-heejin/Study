package tmp;

import java.io.InputStream;
import java.net.URL;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class CreateDomParser {
	public static void main(String[] args) throws Exception {
		//DOM parser factory ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		//��� ���� �κ�
		//whiteSpace(��������)�� text ��ü�� �������� �ʵ��� ��
		factory.setIgnoringElementContentWhitespace(true);
		//validation(��ȿ��) �˻縦 �ǽ��ϵ��� �Ѵ�
		factory.setValidating(true);
		//Namespace�� �ν��ϵ��� �Ѵ�
		factory.setNamespaceAware(true);
		
		//DOM parser ����
		DocumentBuilder parser = factory.newDocumentBuilder();
		
		//XML ������ parsing
		//ex1. ���� ��ũ�� �ִ� xml ���� parsing
		Document xmlDoc = (Document) parser.parse("C:/Tmp/bml/xml");  //��θ� ã�Ƽ� parsing
		//ex2. �� ������ �ִ� XML ������ ���� parsing
		URL url = new URL("http://www.example.com/bml.xml");  //url�� �����´�
		InputStream is = url.openStream();   //�Է� ��Ʈ���� ����
		//�����Ͱ� ���۵Ǵ� ��� ������ ��, �����͸� byte ������ �о���̴� ��ΰ� ��
		Document xmlDoc1 = (Document) parser.parse(is);
	}
}
