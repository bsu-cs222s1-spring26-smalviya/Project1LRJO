package edu.bsu.cs;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaRevisionReader {
    static void main() {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            String timestamp = revisionReader.getLatestRevisionOf(Line);
            System.out.println(timestamp);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa&rvprop=timestamp|user&rvlimit=4&redirects",
                articleTitle);
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            URL url = new URL(encodedUrlString);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionReader parser = new WikipediaRevisionParser();
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }
}
