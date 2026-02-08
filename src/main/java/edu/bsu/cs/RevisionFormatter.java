package edu.bsu.cs;
import java.util.List;

public class RevisionFormatter {
    public String format(List<Revision> revisions) {
     if (revisions == null || revisions.isEmpty()) {
         return "No revisions.";
     }
     StringBuilder builder = new StringBuilder();
     int count = 1;
     for (Revision revision : revisions) {
         builder.append(String.format("%d. User: %-15s | Timestamp: %s\n",
                 count++, revision.user, revision.timestamp));
     }
     return builder.toString();
    }
}