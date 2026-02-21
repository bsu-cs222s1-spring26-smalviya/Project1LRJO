package edu.bsu.cs;
import com.jayway.jsonpath.JsonPath;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class WikipediaRevisionReader {

public List<Revision> fetchRevisions(String articleTitle) {
    try {
        String urlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + articleTitle + "&rvprop=timestamp|user&rvlimit=30";
        java.net.URL url = new java.net.URL(urlString);
        java.net.URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222P1/1.0(lromero1019)");

        return fetchRevisions(connection.getInputStream());
    } catch (Exception e) {
        throw new RuntimeException("Offline network error: " + e.getMessage());
    }
}
    public List<Revision> fetchRevisions(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        String jsonString = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        List<Revision> revisions = new ArrayList<>();

        try {
            List<Map<String, Object>> revisionMaps =
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
}