package sample;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Dictionary dictionary;

    @Override
    public void init() throws Exception {
        this.dictionary = new Dictionary();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane homeLayout = new BorderPane();
        homeLayout.setPrefSize(800, 600);

        HBox buttonsPane = new HBox();
        buttonsPane.setSpacing(10);
        buttonsPane.setAlignment(Pos.CENTER);
        Button addButton = new Button("Add Words");
        addButton.setFont(new Font("Calibri", 20));
        addButton.setPrefSize(380, 50);
        Button practiseButton = new Button("Practise");
        practiseButton.setPrefSize(380, 50);
        practiseButton.setFont(new Font("Calibri", 20));
        buttonsPane.getChildren().addAll(addButton, practiseButton);
        homeLayout.setTop(buttonsPane);

        AddLayout addLayout = new AddLayout(dictionary);
        PractiseLayout practiseLayout = new PractiseLayout(dictionary);

        addButton.setOnAction(actionEvent -> {
            homeLayout.setCenter(addLayout.getLayout());
        });
        practiseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (dictionary.getSize() > 0)
                    homeLayout.setCenter(practiseLayout.getLayout());
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Please Add Words First");
                    alert.showAndWait();
                }

            }

        });

        Label welcomeMsg = new Label("Welcome to Vocabulary and Dictionary App");
        welcomeMsg.setFont(new Font("Calibri", 30));
        homeLayout.setCenter(welcomeMsg);
        homeLayout.setBottom(new Label(" © Pratyush Sharma"));

        Scene scene = new Scene(homeLayout);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Vocabulary And Dictionary");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
