package domparser;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomParser {
    private static final String FILENAME = "C:\\Users\\Big Man\\Desktop\\Xml and Json\\buoi3\\src\\staff.xml";
    public static void main(String[] args) {
        //Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        try {

            //optional but recommend
            //process xml securely, avoid attacks like xml extelnal
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            //parser xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));

            //optional but recommend
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("--------");

            //get staff
            NodeList list = doc.getElementsByTagName("staff");
            for(int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    //get staff atribute
                    String id = element.getAttribute("id");

                    //get text
                    String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                    String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

                    NodeList salaryNodeList = element.getElementsByTagName("salary");
                    String salary = salaryNodeList.item(0).getTextContent();

                    //get salary atribute
                    String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();
                    System.out.println("Current element: " + node.getNodeName());
                    System.out.println("Staff id: " + id);
                    System.out.println("Staff firstname: "+ firstname);
                    System.out.println("Staff lastname: "+ lastname);
                    System.out.println("Staff salary: "+ salary + currency);
                    System.out.println("--------------------------");
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }
}
