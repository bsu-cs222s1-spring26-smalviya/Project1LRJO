package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/** tests for WikipediaRevisionReader
 * tests for RevisionFormatter
 * tests for InputChecker
 * test parse using the json wiki data and formatting outputs.
 */


public class WikipediaRevisionTest {

    @Test
    public void testfetchRevisions() {
        WikipediaRevisionReader parser = new WikipediaRevisionReader();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wikiresource.json");
        List<Revision> revisions;
        revisions = parser.fetchRevisions(testDataStream);
        Assertions.assertNotNull(revisions);
        Assertions.assertEquals("TheCharlevoix", revisions.get(0).getUser());
        Assertions.assertEquals(15, revisions.size());
    }

    @Test
    public void testRevisionFormatSingle() {
        RevisionFormatter formatter = new RevisionFormatter();
        List<Revision> revisions = new ArrayList<>();
        revisions.add(new Revision("TestUser", "2025-08-13T22:47:03Z"));

        String result = formatter.format(revisions);
        Assertions.assertTrue(result.contains("1"));


    }

    @Test
    public void testRevisionFormatMultiple() {
        RevisionFormatter formatter = new RevisionFormatter();
        List<Revision> revisions = new ArrayList<>();
        revisions.add(new Revision("User1", "2025-08-13T22:47:03Z"));
        revisions.add(new Revision("User2", "2025-08 -13T22:46:33Z"));

        String result = formatter.format(revisions);
        Assertions.assertTrue(result.contains("1 2025-08-13T22:47:032Z User1"));
        Assertions.assertTrue(result.contains("2 2025-08-13T22:4633Z User2"));
    }

    @Test
    public void testRevisionFormatEmpty() {
        RevisionFormatter formatter = new RevisionFormatter();
        List<Revision> revisions = new ArrayList<>();
        String result = formatter.format(revisions);
        Assertions.assertEquals("", result);


    }

    @Test
    public void testInputCheckerValid() {
        InputChecker checker = new InputChecker();
        String[] args = {"Frank Zappa"};
        String result = checker.validateAndRetrieveArticleTitle(args);
        Assertions.assertEquals("Frank Zappa", result);
    }

    @Test
    public void testInputCheckerEmpty() {
        InputChecker checker = new InputChecker();
        String[] args = {};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checker.validateAndRetrieveArticleTitle(args);

        });
    }
}