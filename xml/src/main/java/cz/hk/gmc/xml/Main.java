package cz.hk.gmc.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String argv[]) {
        try {
            String filepath = "/home/kuba/tmp/conf.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node descriptorProviders = doc.getElementsByTagName("descriptor-providers").item(0);
            Node remoteComponents = getNodeChild(descriptorProviders, "remote-components");
            Node server = getNodeChild(remoteComponents, "server");
            Node username = getNodeChild(server, "username");
            Node password = getNodeChild(server, "password");

            System.out.println("values=" + username.getTextContent() + ":" + password.getTextContent());

            username.setTextContent("demo");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getNodeChild(Node parentNode, String childNodeName) {
        NodeList nodeList = parentNode.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeName().equals(childNodeName)) {
                return childNode;
            }
        }

        return null;
    }
}
