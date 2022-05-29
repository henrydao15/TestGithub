package saxparser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PrintAllHandlerSax extends DefaultHandler {
    StringBuilder content = new StringBuilder();
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        if("staff".equals(qName)) {
            String id = attributes.getValue("id");
            System.out.println("Staff id: "+ id);
        } else if ("salary".equals(qName)){
            String id = attributes.getValue("currency");
            System.out.println("currency: " + id);
        }
        content.setLength(0);
    }

    @Override
    public void endElement(String uri,  String localName, String qName) {
        if("firstname".equals(qName)) {
            System.out.println("firstname: " + content.toString());
        } else if("lastname".equals(qName)) {
            System.out.println("lastname: " + content.toString());
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        content.append(ch, start,length);
    }

}
