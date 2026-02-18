package edu.bsu.cs;
import java.util.List;

/**
 * Displays a formatter list revision.
 * Outputs the format : time stamp username
 */
public class RevisionFormatter {
    /**
     * Formats the revisions into a numbered list.
     * Each line includes the line number, timestamp and username with double spaces for separation.
     * @param revisions list of revsions to format
     * @return the formatted string with each revision
     */
    public String format(List<Revision> revisions) {
     if (revisions == null || revisions.isEmpty()) {
         return "";
     }
     StringBuilder builder = new StringBuilder();
     int count = 1;
     for (Revision revision : revisions) {
         builder.append(String.format("%d  %s  %s\n",
                 count++, revision.getTimestamp(), revision.getUser()));
     }
     return builder.toString();
    }
}