package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    private Object JsonPath;
    private Object JsonStream;

    public List<Revision> parse(InputStream jsonStream) {
        List<Revision> revisions = new ArrayList<>();

        List<Map<String, String>> revisionData = JsonPath(JsonStream, "$.query.pages.*.revisions[*]");

        for (Map<String, String> entry : revisionData) {
            String user = entry.get("user");
            String timestamp = entry.get("timestamp");

            revisions.add(new Revision(timestamp, user));
        }
        return revisions;
    }

    private List<Map<String, String>> JsonPath(Object jsonStream, String s) {
    }
}
