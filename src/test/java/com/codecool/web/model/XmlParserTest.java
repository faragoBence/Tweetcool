package com.codecool.web.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlParserTest {
    XmlParser xmlParser = new XmlParser();
    Tweet tweet1;
    Tweet tweet2;

    @BeforeEach
    void setUp() {
        tweet1 = new Tweet("Bence", "I'm fine", 111111, 11111111);
        tweet2 = new Tweet("Goat", "MEEEEE", 111112, 11112211);
    }


    @Test
    void readXML() throws ParserConfigurationException, SAXException, IOException {
        xmlParser.writeToXML("src/main/resources/test.xml", tweet1);
        xmlParser.writeToXML("src/main/resources/test.xml", tweet2);
        assertEquals("Bence", xmlParser.readXML("src/main/resources/test.xml").get(0).getPoster());
        assertEquals("I'm fine", xmlParser.readXML("src/main/resources/test.xml").get(0).getContent());
        assertEquals(111111, xmlParser.readXML("src/main/resources/test.xml").get(0).getId());
        assertEquals(11111111, xmlParser.readXML("src/main/resources/test.xml").get(0).getDate());
    }
}
