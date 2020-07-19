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
import javafx.scene.text.Font;


public class AddLayout {

    private Dictionary dictionary;

    public AddLayout(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Parent getLayout() {
        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.CENTER);
        Label wordLabel = new Label("Word:");
        wordLabel.setFont(new Font("Calibri", 20));
        TextField wordTextField = new TextField();
        Label translationLabel = new Label("Translation:");
        translationLabel.setFont(new Font("Calibri", 20));
        TextField translationTextField = new TextField();
        Button addPairButton = new Button("Add Pair");
        addPairButton.setFont(new Font("Calibri", 18));
        addPairButton.disableProperty().bind(Bindings.isEmpty(wordTextField.textProperty()));
        addPairButton.disableProperty().bind(Bindings.isEmpty(translationTextField.textProperty()));

        layout.add(wordLabel, 0, 0);
        layout.add(wordTextField, 0, 1);
        layout.add(translationLabel, 0, 3);
        layout.add(translationTextField, 0, 4);
        layout.add(addPairButton, 0, 6);



        addPairButton.setOnAction(actionEvent -> {
            if (dictionary.addWordTranslationPair(wordTextField.getText().toLowerCase(), translationTextField.getText().toLowerCase()) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Duplicate Pair");
                alert.showAndWait();
            }
            wordTextField.setText(null);
            translationTextField.setText(null);

        });

        return layout;
    }
}
