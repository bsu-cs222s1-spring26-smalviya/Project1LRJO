package edu.bsu.cs;

import java.io.InputStream;

public class App {
    static void main(String[] args) {
        InputChecker checker = new InputChecker();
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        RevisionParser revisionParser = new RevisionParser();
        RevisionFormatter Formatter = new RevisionFormatter();

        try {
            String articleTitle = checker.validateAndGetArticleTitle(args);
            InputStream jsonStream = revisionReader.fetchRevisions(articleTitle);
            revisionParser.Parseresult result = revisionParser.parse(jsonStream);

            if (result.wasRedirected()) {
                System.out.println("Redirected to " + result.getRedirectedTitle());
            }

            List<Revision> revisions = result.getRevisions();
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
