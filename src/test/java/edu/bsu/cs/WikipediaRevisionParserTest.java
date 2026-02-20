package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class WikipediaRevisionParserTest {

    @Test
    public void testParse(){
        RevisionParser parser = new RevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wikiresource.json");
        String timestamp = parser.parse(testDataStream).toString();
        Assertions.assertEquals("2026-01-17T21:17:59Z", timestamp);
    }
}
