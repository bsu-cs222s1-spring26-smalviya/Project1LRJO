package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.List;

public class WikipediaRevisionTest {

    @Test
    public void testWikiParse() {
        WikipediaRevisionTest  parser = new WikipediaRevisionTestParse();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wikiresource.json");
        List<Revision> revisions;
        revisions = parser.testWikiParse(testDataStream);
        Assertions.assertNotNull(revisions);
        Assertions.assertEquals("TheCharlevoix", revisions.get(0).user);
        Assertions.assertEquals(15, revisions.size());
    }

}