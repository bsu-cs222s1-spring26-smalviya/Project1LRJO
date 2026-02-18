package edu.bsu.cs;

import java.io.InputStream;
import java.util.List;

public class Main {
    private static InputStream jsonStream;

    public static void main(String[] args) {
        InputChecker checker = new InputChecker();

        if (!checker.isValid(args)) {
            System.err.println("Error: Please provide a Wikipedia article name.");
            return;
        }

        String articleTitle = args[0];

        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        RevisionParser parser = new RevisionParser();
        RevisionFormatter formatter = new RevisionFormatter();

        try (InputStream jasonStream = reader.fetchRevisions(articleTitle)) {
            if (reader.wasRedirected()) {
                System.out.println("Redirected to " + reader.getRedirectedTarget());
            }

            List<Revision> revisions = parser.parse(jsonStream);

            int lineNumber = 1;
            for (Revision revision : revisions) {
                System.out.println(formatter.format(lineNumber++, revision));
            }
        }

        catch (Exception e) {
            System.err.println("Error: Unable to retrieve Wikipedia data.");
        }
    }
}
