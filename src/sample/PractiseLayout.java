package sample;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PractiseLayout {
    private Dictionary dictionary;
    private String randomWord;
    private int score;

    public PractiseLayout(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.score = 0;
    }

    public Parent getLayout() {
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);

        randomWord = dictionary.getRandomWord();
        Label questionLabel = new Label("Translate the word : '" + randomWord + "'");
        questionLabel.setFont(new Font("Calibri", 20));
        TextField answerTextField = new TextField();

        Button checkButton = new Button("Check");
        checkButton.setFont(new Font("Calibri", 18));
        checkButton.disableProperty().bind(Bindings.isEmpty(answerTextField.textProperty()));

        Label resultLabel = new Label();
        resultLabel.setFont(new Font("Calibri", 30));
        resultLabel.setTextFill(Color.web("#008000"));

        layout.add(questionLabel, 0, 0);
        layout.add(answerTextField, 0, 1);
        layout.add(checkButton, 0, 3);
        layout.add(resultLabel, 0, 7);

        checkButton.setOnAction(actionEvent -> {
            if (dictionary.getTranslation(randomWord).equals(answerTextField.getText().toLowerCase())) {
                score++;
                resultLabel.setText("Score : " + score);
            } else {
                resultLabel.setText("Game Over");
                resultLabel.setTextFill(Color.web("#FF0000"));
                score = 0;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("Incorrect Answer");
                alert.setContentText("Translation of '" + randomWord + " ' is " + dictionary.getTranslation(randomWord));
                alert.showAndWait();
                resultLabel.setTextFill(Color.web("#008000"));
                resultLabel.setText(null);

            }

            randomWord = dictionary.getRandomWord();
            questionLabel.setText("Translate the word : '" + randomWord + "'");
            answerTextField.clear();

        });

        return layout;
    }
}
