package mondial;

import org.w3c.dom.*;

import java.text.NumberFormat;

public class ProcessMondial2 {
    static final String inputFile = "mondial/mondial.xml";
    // ���߿� "mondial/mondial.xml"�� �����ؼ� �׽�Ʈ
    static final String outputFile = "mondial/result.xml";

    static final String continent[] = new String[] { "Europe", "Asia", "America", "Africa", "Australia" };
    static long pop[] = new long[5]; // �� ����� �α��� ��

    public static void processCountries1(Element mondial) { // root element �Ѱ���
        for (Node ch = mondial.getFirstChild(); ch != null; ch = ch.getNextSibling()) {
            if (ch.getNodeName().equals("country")) { // ch: <country> �ڽ� ����� �̸��� country�ΰ�?
                //Element country = (Element) ch; // �̸� Element Ÿ�� ������ ����
                Node country = ch; // ���� Node type -> Element type ���� �ʿ�

                // 1-1 ���� �̸�
                Node name = country.getFirstChild(); // <name>Albania</name>
                Text txt = (Text) name.getFirstChild(); // <name>�� �ڽ��� text node
                String countryName = txt.getData(); // "Albania"
                // �Ǵ� = name.getFirstChild().getNodeValue();   //ù��° �ڽ� ����� ��尪
                // �Ǵ� = name.getTextContent();   //name element�� String value (text ����� ���� ��� ���ؼ� �ϳ��� �̾���)
                System.out.println(countryName);

                //1-2 ���� (79p ����)
                String areaValue = ((Element) country).getAttribute("area");
                //Attr area = country.getAttributeNode("area");
                System.out.println("���� : " + areaValue);

                //1-3 �α�
                //�α��� ������ �� ����(dtd�� ?) -> �ٸ� ��尡 ���� �� �ֱ� ������ Ȯ�� �ʿ�
                Node popNode = name.getNextSibling();
                if (popNode.getNodeName().equals("population")) {
                    String population = popNode.getTextContent();
                    System.out.println("�α� : " + population);
                } else {
                    System.out.println("�α� ������ �����ϴ�.");
                }
                //popNode.getFirstChild().getNodeValue();
                //((Text)popNode).getData();

                //1-4 ����
                //capital �Ӽ��� ���� id�� ���� city�� ������
                //Document doc = country.getOwnerDocument();
                String capitalId = ((Element) country).getAttribute("capital");
                //������ ���� �ʴ� ���? -> null���� ���� �� ����(NullPointerExeption)
                if (!capitalId.isEmpty()) {
                    //document ��ü�� �������� �ʱ� ������ ���� ��尡 ���� ������ ��ȯ�ϴ� getOwnerDocument ���
                    Element capital = country.getOwnerDocument().getElementById(capitalId);
                    name = capital.getFirstChild();
                    txt = (Text) name.getFirstChild();
                    String capitalName = txt.getData();
                    System.out.println("������ �̸� : " + capitalName);
                }
                //�ٸ� ��� : is_country_cap="yes" �� ���� ã���� ��
                //NodeList cityList = country.getElementsByTagName("city");   //city��� �̸��� ���� �ڼ� element�� ã�ƶ�
                //for(int i=00; i<cityList.getLength(); i++) {
                //Node city = cityList.item(i);   //city�� ���� -> is_country_cap Ȯ��
                //}
            }
        }

    }

    public static void processCountries2(Element mondial) {
        NodeList list = mondial.getElementsByTagName("country"); // country ��带 ��� ����
        for (int i = 0; i < list.getLength(); i++) {
            Element country = (Element) list.item(i); // ���� country�� �����Ѵ�
            //�Ӽ� ���� ���ϱ� ���� Element type Ÿ��ĳ����

            Document doc = country.getOwnerDocument(); // country�� ���� ���� ��ȯ
            Element area = doc.createElement("area"); // area element�� �����ϰ� �� ����� �����Ѵ�
            String areaValue = country.getAttribute("area"); // i��° country�� area �Ӽ� ��
            Text areaText = doc.createTextNode(areaValue);
            area.appendChild(areaText); // areaText�� �ڽĳ��� �̾���<area>areaText</area>
            Node pop = country.getFirstChild().getNextSibling(); // country�� ù��° �ڽĳ���� ���� ���(population)
            country.insertBefore(area, pop);
            country.removeAttribute("area");
            //area�� country�� �ι�° �ڽ����� �־��ش� -> insertBefore(population)
            //country.insertBefore(area, country.getFirstChild().getNextSibling());

            //capital�� ã�� �θ� ���κ��� ����(�ӽ�) -> name, area, population�� ���� �� ������ ��� ����
            Element capital = null;
            String capitalId = country.getAttribute("capital");
            //������ ���� �ʴ� ���? -> null���� ���� �� ����(NullPointerExeption)
            if (!capitalId.isEmpty()) {
                //document ��ü�� �������� �ʱ� ������ ���� ��尡 ���� ������ ��ȯ�ϴ� getOwnerDocument ���
                capital = country.getOwnerDocument().getElementById(capitalId);
                //�ڽ� ��带 �����ϰ� �� ��ü�� ��ȯ�ϱ� ������ capital�� �����
                //���� country�� ���� ������ ��ȯ�ϰ�, capitalId�� ���� element�� ��ȯ��
                //country.removeChild(capital);
                country.removeAttribute("capital");
            }

            //71page
            Node ch = pop.getNextSibling(); // population�� ���� ���
            while (ch != null) {
                country.removeChild(ch);
                ch = pop.getNextSibling();
            }

            //������ �� capital ������ ���� ����
            //country.appendChild(capital);

            
            if(!capitalId.isEmpty()) {
                Element capitalTmp = doc.createElement("capital");
                capitalTmp.setAttribute("country", country.getAttribute("car_code"));
                capitalTmp.setAttribute("id", capitalId);
                country.removeAttribute("car_code");
                country.removeAttribute("memberships");
                
                Node ch2 = capital.getFirstChild();
                while (ch2 != null) {
                    capitalTmp.appendChild(ch2);
                    ch2 = capital.getFirstChild();
                }

                country.appendChild(capitalTmp);
            }
            
        }

    }

    public static void computePopulationsOfContinents(Element mondial) {
        NodeList list = mondial.getElementsByTagName("country");
        for (int i = 0; i < pop.length; i++) {
            pop[i] = 0;
        }
        long popNum = 0L;
        String continent2 = null;
        int max = -1;
        int perTmp;
        for (int i = 0; i < list.getLength(); i++) {
            Element country = (Element) list.item(i); // �̸� element Ÿ�� ������ ����

            //<population>�� ã�� ���� �� ���� �� ���� (���� ���� ����)
            //population�� ����ó�� �������� �����̱� ������ ���� ��ȯ�� �ʿ��ϴ�
            Node popNode = country.getFirstChild().getNextSibling();
            Text txt = (Text) popNode.getFirstChild();
            String tmpPop = txt.getData();
            //System.out.println(tmpPop);
            if (popNode.getNodeName().equals("population")) {
                popNum = Long.parseLong(tmpPop);
                //System.out.println(popNum);
            }

            //�� ���� ���� ������ �����ؼ� <encompassed>�� ó�� �߰ߵ� ������ ��� ����(while/for)
            //���� : encompassed�� �ƴϰ� null�� �ƴϸ� ��� ����
            //percentage�� �ִ밪�� percentage�� �ִ��� ���� continent ���� ������ ���� ���� �� �ʱ�ȭ
            //<encompassed>�� ��������� �����ϰ� �� <encompassed> ���� percentage�� ���� ���� ���ݱ����� �ִ밪�� ������� ����
            for (Element ch = (Element) country.getFirstChild(); ch != null; ch = (Element) ch.getNextSibling()) {
                if (ch.getNodeName().equals("encompassed")) {
                    perTmp = Integer.parseInt(ch.getAttribute("percentage"));
                    continent2 = ch.getAttribute("continent");
                    
                    if (max < perTmp) {
                          continent2 = ch.getAttribute("continent");
                          //System.out.println(continent2);
                          max = perTmp;
                          //System.out.println(max);
                       }
                }
            }

            //System.out.println(popNum);
            //{"Europe", "Asia", "America", "Africa", "Australia"};
            //if-else �Ǵ� switch���� Ȱ���ؼ�, �α��� �ִ��� ����� �ش��ϴ� �迭 ���ҿ� �α� �� ����
            if (continent2.equals("Europe")) {
                pop[0] += popNum;
                System.out.println(continent2);
            } else if (continent2.equals("Asia")) {
                pop[1] += popNum;
            } else if (continent2.equals("America")) {
                pop[2] += popNum;
            } else if (continent2.equals("Africa")) {
                pop[3] += popNum;
            } else if (continent2.equals("Australia")) {
                pop[4] += popNum;
            }
        }

        // ���� �� ����� �α��� ���
        //��� �� ���ڸ����� , �����
        printPopulationsOfContinents();
    }

    public static void printPopulationsOfContinents() {
        System.out.println("Populations of the Continents");
        System.out.println("---------------------------");
        for (int i = 0; i < continent.length; i++) {
            NumberFormat formatter = NumberFormat.getInstance();
            System.out.println(continent[i] + "�� �α� : " + formatter.format(pop[i]));
        }
        System.out.println();
    }

}