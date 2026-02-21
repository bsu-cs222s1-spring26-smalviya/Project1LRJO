package edu.bsu.cs;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class GUI  extends Application  {

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

        searchButton = new Button("Revision Search");
        searchButton.setOnAction(e -> handleSearch());

        resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setPrefRowCount(15) ;

        VBox root = new VBox(10);
        root.setPadding(new Insets(10,10,10,10));
        root.getChildren().addAll(
                titleLabel,
                inputLabel,
                articleInput,
                searchButton,
                new Label( "Results Found:"),
                resultsArea
                );
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Wikipedia Revision");
        primaryStage.show();


        }
        private void handleSearch() {
        String articleTitle = articleInput.getText().trim();

            if (articleTitle.isEmpty()) {
                showError("Please enter article title.");
                return;
            }
        searchButton.setDisable(true);
        resultsArea.setText("Searching...");

        try {
            WikipediaRevisionReader  reader = new WikipediaRevisionReader();
           List<Revision> revisions = reader.fetchRevisions(articleTitle);

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
