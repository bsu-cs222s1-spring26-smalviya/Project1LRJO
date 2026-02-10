package edu.bsu.cs;
import java.util.List;

public class RevisionFormatter {
    public String format(List<Revision> revisions) {
     if (revisions == null || revisions.isEmpty()) {
         return "";
     }
     StringBuilder builder = new StringBuilder();
     int count = 1;
     for (Revision revision : revisions) {
         builder.append(String.format("%d   %s  %s\n",
                 count++, revision.getTimestamp(), revision.getUser()));
     }
     return builder.toString();
    }
}