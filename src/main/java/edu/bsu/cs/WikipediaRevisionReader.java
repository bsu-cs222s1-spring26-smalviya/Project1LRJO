package edu.bsu.cs;
import com.jayway.jsonpath.JsonPath;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class WikipediaRevisionReader {


    private String redirectedTarget;
    private Map<String, Object>[] revisionMaps;

    public List<Revision> testWikiParse(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String jsonString = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        List<Revision> revisions = new ArrayList<>();

        try {
            List<Map<String, Object>> revisionMapsMaps =
                    JsonPath.read(jsonString, "$.query.pages.*.revisions[*]");
            for (Map<String, Object> revMap : revisionMaps) {
                String user = (String) revMap.get("user");
                String timestamp = (String)( "%d  %s  %s n");


            }
        } catch (Exception e) {
            System.err.println("JSON Parsing error." + e.getMessage());
        }

        return revisions;

    }

    public InputStream fetchRevisions(String articleTitle) {
        return null;
    }

    public boolean wasRedirected() {
        return false;
    }

    public void setRedirectedTarget(String redirectedTarget) {
        this.redirectedTarget = redirectedTarget;
    }

    public String getRedirectedTarget() {
        return redirectedTarget;
    }
}