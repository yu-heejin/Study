package ch9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;


public class XMLJFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	//�ʵ�
	public JTextField txtURL,txtTitle,txtKind,txtAuthor,txtPublisher,txtPrice;
	public JButton btnSearch, btnLoad;
	public JButton btnFirst, btnPrevious, btnNext, btnLast;
	public JButton btnNew, btnModify, btnAdd, btnSave, btnClose;

	public Document xmlDoc;
	public Element ePrevious, eCurrent, eNext;
	public boolean isAddReady;	
	
	//������
	public XMLJFrame() {
		this.setTitle("XML ���� ���");
		
		//���� �г� �����ϱ�
		JPanel pNorth = new JPanel(new BorderLayout());
		pNorth.setBackground(Color.white);

		JLabel pNorthlblNorth = new JLabel("BML ���� ���");
		pNorthlblNorth.setFont(new Font("����",Font.PLAIN|Font.BOLD, 18));
		pNorthlblNorth.setBorder(new EmptyBorder(20,20,20,20));
		pNorthlblNorth.setHorizontalAlignment(JLabel.CENTER);

		JPanel pNorthpCenter = new JPanel(new BorderLayout());
		pNorthpCenter.setBorder(new EmptyBorder(20,20,20,20));
		
		JPanel pNorthpCenterpCenter = new JPanel();
		TitledBorder tb1 = new TitledBorder("���� ��� �Է�");
		tb1.setTitleFont(new Font("����",Font.PLAIN, 13));
		pNorthpCenterpCenter.setBorder(tb1);				
		
		txtURL = new JTextField(25);
		btnSearch = new JButton("ã�ƺ���...");
		btnLoad = new JButton("�ε�");
		
		pNorthpCenterpCenter.add(txtURL);
		pNorthpCenterpCenter.add(btnSearch);
		pNorthpCenterpCenter.add(btnLoad);
		
		pNorthpCenter.add("Center", pNorthpCenterpCenter);

		pNorth.add("North", pNorthlblNorth);
		pNorth.add("Center", pNorthpCenter);

		//�߾� �г� �����ϱ�
		JPanel pCenter = new JPanel(new BorderLayout());
		pCenter.setBorder(new EmptyBorder(0,20,20,0));			
		
		JPanel pCenterpCenter = new JPanel(new BorderLayout());
		TitledBorder tb2 = new TitledBorder("å ����");
		tb2.setTitleFont(new Font("����",Font.PLAIN, 13));
		pCenterpCenter.setBorder(tb2);
		
		JPanel pCenterpCenterpWest = new JPanel(new GridLayout(5,1,10,10));
		pCenterpCenterpWest.setBorder(new EmptyBorder(10,10,10,10));	
		
		JLabel lblTitle = new JLabel("å����");
		JLabel lblKind = new JLabel("����");
		JLabel lblAuthor = new JLabel("����");
		JLabel lblPublisher = new JLabel("���ǻ�");
		JLabel lblPrice = new JLabel("����");
		
		pCenterpCenterpWest.add(lblTitle);
		pCenterpCenterpWest.add(lblKind);
		pCenterpCenterpWest.add(lblAuthor);
		pCenterpCenterpWest.add(lblPublisher);
		pCenterpCenterpWest.add(lblPrice);
			
		JPanel pCenterpCenterpCenter = new JPanel(new GridLayout(5,1,10,10));
		pCenterpCenterpCenter.setBorder(new EmptyBorder(10,10,10,10));
	
		txtTitle = new JTextField(20);
		txtKind = new JTextField(20);
		txtAuthor = new JTextField(20);
		txtPublisher = new JTextField(20);
		txtPrice = new JTextField(20);
		
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);
		
		pCenterpCenterpCenter.add(txtTitle);
		pCenterpCenterpCenter.add(txtKind);
		pCenterpCenterpCenter.add(txtAuthor);
		pCenterpCenterpCenter.add(txtPublisher);
		pCenterpCenterpCenter.add(txtPrice);					
		
		pCenterpCenter.add("West", pCenterpCenterpWest);
		pCenterpCenter.add("Center", pCenterpCenterpCenter);
			
		pCenter.add("Center", pCenterpCenter);

		//���� �г� �����ϱ�	
		JPanel pSouth = new JPanel(new BorderLayout());	
			
		JPanel pSouthpCenter = new JPanel(new GridLayout(1,4));	
		pSouthpCenter.setBorder(new EmptyBorder(0,20,20,55));	
					
		btnFirst = new JButton("ó��");
		btnPrevious = new JButton("����");
		btnNext = new JButton("����");
		btnLast = new JButton("������");
		
		pSouthpCenter.add(btnFirst);
		pSouthpCenter.add(btnPrevious);
		pSouthpCenter.add(btnNext);
		pSouthpCenter.add(btnLast);

		JPanel pSouthpEast = new JPanel(new GridLayout(1,1));
		pSouthpEast.setBorder(new EmptyBorder(0,1,20,20));
			
		btnClose = new JButton("�ݱ�");				
		pSouthpEast.add(btnClose);
		
		pSouth.add("Center", pSouthpCenter);
		pSouth.add("East", pSouthpEast);	
		

		//���� �г� �����ϱ�
		JPanel pEast = new JPanel(new GridLayout());
		pEast.setBorder(new EmptyBorder(0,20,20,20));	
		
		JPanel pEastpCenter = new JPanel(new GridLayout(4,1,0,30));	
		btnNew = new JButton("�� XML ����");
		btnModify = new JButton("����");
		btnAdd = new JButton("�߰�");
		btnSave = new JButton("����");
			
		pEastpCenter.add(btnNew);
		pEastpCenter.add(btnModify);
		pEastpCenter.add(btnAdd);
		pEastpCenter.add(btnSave);			
		
		pEast.add("Center", pEastpCenter);

		getContentPane().add("North", pNorth);
		getContentPane().add("Center", pCenter);
		getContentPane().add("South", pSouth);
		getContentPane().add("East",pEast);

		eventControl();				
	}

	//�޼���
	public void eventControl() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);	
			}	
		});	
		
		btnSearch.addActionListener(this);
		btnLoad.addActionListener(this);
		
		btnFirst.addActionListener(this);
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		btnLast.addActionListener(this);
		
		btnNew.addActionListener(this);
		btnModify.addActionListener(this);
		btnAdd.addActionListener(this);
		btnSave.addActionListener(this);
		btnClose.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj==btnSearch)			btnSearch_Click();
		if(obj==btnLoad) 			btnLoad_Click();
		if(obj==btnFirst) 			btnFirst_Click();
		if(obj==btnPrevious)		btnPrevious_Click();
		if(obj==btnNext)			btnNext_Click();
		if(obj==btnLast)			btnLast_Click();
		if(obj==btnNew)				btnNew_Click();
		if(obj==btnModify)			btnModify_Click();
		if(obj==btnAdd)				btnAdd_Click();
		if(obj==btnSave)			btnSave_Click();
		if(obj==btnClose)			btnClose_Click();
	}

	public void btnSearch_Click() {
		// ���� ������ ã������ ���� ���̾�α� ����
		JFileChooser fc = new JFileChooser();
		int buttonKind = fc.showOpenDialog(this);
		if(buttonKind==JFileChooser.CANCEL_OPTION) {
			return;
		}
		// ������ ������ ��Ÿ����
		txtURL.setText(fc.getSelectedFile().getAbsolutePath());
	}
	
	public void btnLoad_Click() {
		try {
			// �ļ� ���� ����
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// WhiteSpace�� ������(XML������ �������������� �־�� ������)
			factory.setIgnoringElementContentWhitespace(true);
			// �ļ� ����
			DocumentBuilder parser = factory.newDocumentBuilder();

			// ���� �������� ����
			if(txtURL.getText().indexOf("http://")!=-1) {
				// ���������� XML ���� ������ ���
				URL url = new URL(txtURL.getText());
				InputStream is = url.openStream();			
				xmlDoc = parser.parse(is);
			} else if(!txtURL.getText().trim().equals("")) {
				// ���� XML ���� ������ ���			
				xmlDoc = parser.parse(txtURL.getText());
			} else {
				return;
			}
			
			// ��Ʈ������Ʈ ��ü���� ���
			Element eRoot = xmlDoc.getDocumentElement();			
			// ù��° book ������Ʈ ��ü���� ���
			Element eBook = (Element) eRoot.getFirstChild();
			
			// �׺���̼� �ʱ�ȭ
			ePrevious = null;
			eCurrent = null;
			eNext = null;
			txtTitle.setText("");
			txtKind.setText("");
			txtAuthor.setText("");
			txtPublisher.setText("");
			txtPrice.setText("");
			
			// å������ ���÷����ϱ� ���� displayBook �޼ҵ� ȣ��
			displayBook(eBook);			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "��ΰ� �߸��Ǿ��ų�, �������� ������ ���� �ʾҽ��ϴ�.");	
		}
	}
		
	public void btnFirst_Click() {
		if(xmlDoc==null) return;
	    // ��Ʈ ������Ʈ ��ü���� ���
	    Element eRoot = xmlDoc.getDocumentElement();  	      
	   	// ù��° book ������Ʈ ��ü���� ���
	   	Element eBook = (Element)eRoot.getFirstChild();	   	 
	    // å������ ���÷����ϱ� ���� displayBook �޼ҵ� ȣ��
	    displayBook(eBook);		
	}
	
	public void btnPrevious_Click() {
		// å������ ���÷����ϱ� ���� displayBook �޼ҵ� ȣ��
		displayBook(ePrevious);	
	}
	
	public void btnNext_Click() {
		displayBook(eNext);	
	}
	
	public void btnLast_Click() {
		if(xmlDoc==null) return;
	    // ��Ʈ ������Ʈ ��ü���� ���
	    Element eRoot = xmlDoc.getDocumentElement();   
	    // ù��° book ������Ʈ ��ü���� ���
	   	Element eBook = (Element)eRoot.getLastChild();    
	   	// å������ ���÷����ϱ� ���� displayBook �޼ҵ� ȣ��
	    displayBook(eBook);			
	}

	public void btnNew_Click() {
		try {
			// �ļ� ���� ����
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			// �ļ� ����
			DocumentBuilder parser = dbFactory.newDocumentBuilder();
			// ���ο� Document ��ü ����
			Document newDoc = parser.newDocument();
			
			// ��Ʈ ������Ʈ ��ü ����
			Element eRoot = newDoc.createElement("booklist");
			newDoc.appendChild(eRoot);
			
			// �������� DOMSource ��ü �����ϱ�
			DOMSource source = new DOMSource(newDoc);
			
			// ������ ���Ͽ� ���� StreamResult ��ü �����ϱ�
			JFileChooser fc = new JFileChooser();
			int buttonKind = fc.showSaveDialog(this);
			if(buttonKind==JFileChooser.CANCEL_OPTION) {
				return;
			}
			String strNewFile = fc.getSelectedFile().getAbsolutePath();
			StreamResult result = new StreamResult(new File(strNewFile));

			// ��ȭ�� ���� ����
			TransformerFactory tFactory = TransformerFactory.newInstance();
			// ��ȯ�� ����
			Transformer transformer = tFactory.newTransformer();
			
			// XML ����� ���� ���� ���� ���� �����ϱ�
			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://localhost:8080/ch9/bml.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");				
				
			// DOM ��ü���� XML ���� ���Ϸ� ��ȯ�ϱ�(�����ϱ�)
			transformer.transform(source, result);
			
			//������ ���� �ε�
			txtURL.setText(strNewFile);
			btnLoad_Click();			
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void btnModify_Click() {
		//���� ���� 
		if(eCurrent==null) {
			return;
		} else if(!txtTitle.isEditable()) {
		    txtTitle.setEditable(true);
		    txtKind.setEditable(true);
		    txtAuthor.setEditable(true);
		    txtPublisher.setEditable(true);
		    txtPrice.setEditable(true);
		    return;
		}
		
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);

		// kind �Ӽ��� ����
		eCurrent.setAttribute("kind", txtKind.getText());		
		
		// title ������Ʈ�� ����Ʈ ���� ����
		Element eTitle = (Element)eCurrent.getFirstChild();
		Text tTitle = (Text)eTitle.getFirstChild();
		tTitle.setData(txtTitle.getText());		
		
		// author ������Ʈ�� ����Ʈ ���� ����
		Element eAuthor = (Element)eTitle.getNextSibling();
		Text tAuthor = (Text)eAuthor.getFirstChild();
		tAuthor.setData(txtAuthor.getText());		
		
		// publisher ������Ʈ�� ����Ʈ ���� ����
		Element ePublisher = (Element)eAuthor.getNextSibling();
		Text tPublisher = (Text)ePublisher.getFirstChild();
		tPublisher.setData(txtPublisher.getText());		
			
		// price ������Ʈ�� ����Ʈ ���� ����
		Element ePrice = (Element)ePublisher.getNextSibling();
		Text tPrice = (Text)ePrice.getFirstChild();
		tPrice.setData(txtPrice.getText());			
	}
	
	public void btnAdd_Click() {
		//�߰� ���� ����
		if(xmlDoc==null) return;
		
	    //ó�� Ŭ������ �� ������ ���� �޼ҵ� ����
	    if(isAddReady==false) {
	      txtTitle.setEditable(true);
	      txtTitle.setText("���� �Է�");
	      
	      txtKind.setEditable(true);
	      txtKind.setText("���� �Է�");
	      
	      txtAuthor.setEditable(true);
	      txtAuthor.setText("���� �Է�");
	      
	      txtPublisher.setEditable(true);
	      txtPublisher.setText("���ǻ� �Է�");
	      
	      txtPrice.setEditable(true);
	      txtPrice.setText("���� �Է�");
	      
	      isAddReady = true;
	      return;
	  	}    
	  	
	    //�ι�° Ŭ������ ��� �Ʒ� �߰� �ڵ� ����
	    txtTitle.setEditable(false);
	    txtKind.setEditable(false);
	    txtAuthor.setEditable(false);
	    txtPublisher.setEditable(false);
	    txtPrice.setEditable(false);
	    isAddReady = false;       
	    
	    //title ������Ʈ ��ü ����
	    Element eTitle = xmlDoc.createElement("title");	    
    	Text tTitle = xmlDoc.createTextNode(txtTitle.getText());	    
    	eTitle.appendChild(tTitle);
	    
	    //author ������Ʈ ��ü ����
	    Element eAuthor = xmlDoc.createElement("author");
    	Text tAuthor = xmlDoc.createTextNode(txtAuthor.getText());
    	eAuthor.appendChild(tAuthor);
	    
	    //publisher ������Ʈ ��ü ����
	    Element ePublisher = xmlDoc.createElement("publisher");
    	Text tPublisher = xmlDoc.createTextNode(txtPublisher.getText());
	    ePublisher.appendChild(tPublisher);
	    
	    //price ������Ʈ ��ü ����
	    Element ePrice = xmlDoc.createElement("price");
    	Text tPrice = xmlDoc.createTextNode(txtPrice.getText());
    	ePrice.appendChild(tPrice);
	    
	    //book ������Ʈ ��ü ����
	    Element eBook = xmlDoc.createElement("book");
	    
	    //�Ӽ� �߰�
	    eBook.setAttribute("kind", txtKind.getText());
	    
	    //book ������Ʈ ��ü�� �ڽ� ������Ʈ ��ü ���̱�
	    eBook.appendChild(eTitle);
	    eBook.appendChild(eAuthor);
	    eBook.appendChild(ePublisher);
	    eBook.appendChild(ePrice);
	    
	    //��Ʈ ������Ʈ ��ü���� ���
	    Element eRoot = xmlDoc.getDocumentElement();
	    
	    //book ������Ʈ ��ü�� ��Ʈ ������Ʈ ��ü�� ���̱�
	    eRoot.appendChild(eBook);
	    
	    //�߰��ϱ� ���� ���Ҵ� å ������ ���÷���
	    displayBook(eBook);
	}
	
	public void btnSave_Click() {
		try {
			// ��ȭ�� ���� ����			
			TransformerFactory factory = TransformerFactory.newInstance();
			// ��ȯ�� ����
			Transformer transformer = factory.newTransformer();
			
			// XML ����� ���� ���� ���� ���� �����ϱ�
			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-kr");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://localhost:8080/ch9/bml.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			// �������� DOMSource ��ü �����ϱ�	
			DOMSource source = new DOMSource(xmlDoc);
				
			// ������ ���Ͽ� ���� StreamResult ��ü �����ϱ�				
			if(txtURL.getText().indexOf("http://")!=-1) {
				JFileChooser fc = new JFileChooser();
				fc.showSaveDialog(this);
				txtURL.setText(fc.getSelectedFile().getAbsolutePath());
			} else if(txtURL.getText().trim().equals("")) {
				return;
			}
			StreamResult result = new StreamResult(new File(txtURL.getText()));	
				
			// DOM ��ü���� XML ���� ���Ϸ� ��ȯ�ϱ�(�����ϱ�)
			transformer.transform(source, result);
		} catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void btnClose_Click() {
		System.exit(0);	
	}
	
	public void displayBook(Element eBook) {	
		if(eBook==null) {
			//book ������Ʈ ��ü������ ���� ��� �޼ҵ� ����
			return;			
		} else {
			//���� book ������Ʈ�� ���� ���� book ������Ʈ ��ü������
			//ePrevious�� ���� -- ���� ��ư �ڵ忡�� ���
			ePrevious = (Element)eBook.getPreviousSibling();
			
			//���� book ������Ʈ�� ��ü������ eCurrent�� ����
			eCurrent = eBook;
			
			//���� book ������Ʈ�� ���� ���� book ������Ʈ ��ü������
			//eNext�� ���� -- ���� ��ư �ڵ忡�� ���
			eNext = (Element)eBook.getNextSibling();
		}

		//kind �Ӽ����� ���� ���÷���
		txtKind.setText(eCurrent.getAttribute("kind"));

	    //title ������Ʈ�� ����Ʈ ������ ���� ���÷���
	    Element eTitle = (Element)eCurrent.getFirstChild();
	    Text tTitle = (Text)eTitle.getFirstChild();
	    txtTitle.setText(tTitle.getData());
    
	    //author ������Ʈ�� ����Ʈ ������ ���� ���÷���
	    Element eAuthor = (Element)eTitle.getNextSibling();
	    Text tAuthor = (Text)eAuthor.getFirstChild();
	    txtAuthor.setText(tAuthor.getData());
    
	    //publisher ������Ʈ�� ����Ʈ ������ ���� ���÷���
	    Element ePublisher = (Element)eAuthor.getNextSibling();
	    Text tPublisher = (Text)ePublisher.getFirstChild();
	    txtPublisher.setText(tPublisher.getData());
    
	    //price ������Ʈ�� ����Ʈ ������ ���� ���÷���
	    Element ePrice = (Element)ePublisher.getNextSibling();
	    Text tPrice = (Text)ePrice.getFirstChild();
	    txtPrice.setText(tPrice.getData());		
	}

	//���� �޼���
	public static void main(String args[]) {
		// XMLJFrame ��ü ����
		XMLJFrame frame = new XMLJFrame();
		// ������ ����ʷ� ���� 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
	    } catch(Exception e) {
	    }
		// Frame ������ �ֱ�
		frame.setSize(550,470);
		// Frame ������ ����
		frame.setResizable(false);
		// Frame ���� �ֱ�
		frame.setVisible(true);
	}
}