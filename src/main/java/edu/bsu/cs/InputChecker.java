package edu.bsu.cs;

public class InputChecker {
    public String validateAndGetArticleTitle(String [] args) {
        //
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No Article name given.");
        }
        String articleTitle = args[0].trim();

        if (articleTitle.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be blank.");
        }
        return articleTitle;
    }
}
