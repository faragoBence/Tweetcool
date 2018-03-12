package com.codecool.web.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public void writeToXML(String path, Tweet tweet) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Element rootElement;
            Document doc = docBuilder.newDocument();

            if (!new File(path).exists()) {
                rootElement = doc.createElement("tweets");
                doc.appendChild(rootElement);
            } else {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(path);
                Element ror = document.getDocumentElement();
                rootElement = (Element) doc.importNode(ror, true);
                doc.appendChild(rootElement);
            }

            Element tweetElement = doc.createElement("tweet");
            rootElement.appendChild(tweetElement);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(Integer.toString(tweet.getId())));
            tweetElement.appendChild(id);

            Element poster = doc.createElement("poster");
            poster.appendChild(doc.createTextNode(tweet.getPoster()));
            tweetElement.appendChild(poster);

            Element message = doc.createElement("message");
            message.appendChild(doc.createTextNode(tweet.getContent()));
            tweetElement.appendChild(message);

            Element time = doc.createElement("time");
            time.appendChild(doc.createTextNode(Long.toString(tweet.getDate())));
            tweetElement.appendChild(time);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));


            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
    }

    public List<Tweet> readXML(String path) throws IOException, SAXException, ParserConfigurationException {
        List<Tweet> tweets = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(path);
        NodeList nList = document.getElementsByTagName("tweet");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                int id = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                String poster = eElement.getElementsByTagName("poster").item(0).getTextContent();
                String content = eElement.getElementsByTagName("message").item(0).getTextContent();
                long time = Long.parseLong(eElement.getElementsByTagName("time").item(0).getTextContent());

                tweets.add(new Tweet(poster, content, id, time));

            }
        }
        return tweets;
    }
}
