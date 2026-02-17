package edu.bsu.cs;

import java.io.InputStream;
import java.util.List;

public class App {
    static void main(String[] args) {
        InputChecker checker = new InputChecker();
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        RevisionFormatter Formatter = new RevisionFormatter();

        try {
            String articleTitle = checker.validateAndRetrieveArticleTitle(args);
            InputStream jsonStream = revisionReader.fetchRevisions(articleTitle);
            List<Revision> revisions = revisionReader.testWikiParse(jsonStream);
            int lineNumber = 1;
            for (Revision revision : revisions) {
                System.out.println(formatter.format(lineNumber, revision));
                lineNumber++;
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            } catch (WikipediaRevisionReader.PageNotFoundException e) {
                System.err.println("No Wikipedia page found for the article given.");
                System.exit(1);
            } catch (WikipediaRevisionReader.NetworkException e) {
                System.err.println("A network error occurred trying to retrieve the article.");
                System.exit(1);
            } catch (Exception e) {
                System.err.println("An unexpected error occured.");
                System.exit(1);
            }
        }
    }
}
