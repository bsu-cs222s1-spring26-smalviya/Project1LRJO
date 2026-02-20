package edu.bsu.cs;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.awt.Insets;
import java.util.List;

public class GUI  extends Main {

    private TextField articleInput;
    private TextArea resultsArea;
    private Button searchButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label ("Wikipedia Revision Tracer");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label inputLabel = new Label("Enter Wikipedia Article Name:");
        articleInput = new TextField();

        searchButton = new Button(("Revision Search");
        searchButton.setOnAction(e -> handleSearch());

        resultsArea = new TextArea();
        resultsArea.setEdible(false);
        resultsArea.setPrefRowCount(15) ;


        Vbox root = new Vbox(10);
        root.setPadding(new java.awt.Insets((15)));
        root.getChildren().addAll(
                titleLabel,
                inputLabel,
                articleInput,
                searchButton,
                new Label( "Results Found:")
                resultsArea
                );
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScence(scene);
        primaryStage.setTitle("Wikipedia Revision");
        primaryStage.show();

        private void searchingHandle () {
            String articleTitle = articleInput.getText().trim();
            if articleTitle.isEmpty()) {
            showError("Please enter article title.");
            return;
            }
        }

        searchButton.setDisable(true);
        resultsArea.setText("Searching...");

        try {
            WikipediaRevisionReader  reader = new WikipediaRevisionReader();
            java.io.InputStream stream = reader.fetcgRevisions(articleTitle);
            List<Revision> revisions = reader.testWikiParse(stream);

            StringBuilder output = new StringBuilder();
            int lineNumber = 1;
            for (Revision revision : revisions) {
                output.append(String.format("%d %s %s%n",
                        lineNumber, revision.getTimestamp(),revision.getUser()));
                lineNumber++;
            }
            resultsArea.setText(output.toString());
        } catch (Exception e) {
            showError("Error:" + e.getMessage());
            resultsArea.setText("");

        } finally {
            searchButton.setDisable(false);
        }


    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.Alert.Type.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContextText(message);
        alert.showAndWait();
    }
}
