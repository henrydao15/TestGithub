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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Whether {
    public static void main(String[] args) throws MalformedURLException {
        try {
//            URL xmlURL = new URL("https://api.openweathermap.org/data/2.5/weather?q=hanoi,vn&APPID=66d9be5f6f21f670ac388097b92353d5&mode=xml");
            URLConnection conn = new URL("https://api.openweathermap.org/data/2.5/weather?q=hanoi,vn&APPID=66d9be5f6f21f670ac388097b92353d5&mode=xml").openConnection();
            //Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //unknown xml better turn on this
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            InputStream is = conn.getInputStream();
            Document doc = documentBuilder.parse(is);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("--------");

            //get temp
            NodeList list = doc.getElementsByTagName("city");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    String nhietDo = element.getElementsByTagName("temperature").item(0).getTextContent();


                    System.out.println(id + "Thanh pho " + nhietDo);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}