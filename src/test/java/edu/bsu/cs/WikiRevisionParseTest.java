package edu.bsu.cs;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class WikiRevisionParseTest {

    @Test
    public void testWikiParse() {
        WikiRevisionParse parser = new WikiRevisionParse();
        InputStream tesDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wikiresource.json");

    }



}
