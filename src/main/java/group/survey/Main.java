package group.survey;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource("main-menu.fxml"));

        stage.setScene(new Scene(loader.load(), 700, 500));
        stage.setTitle("Survey");
        stage.show();
    }

    public static void switchScene(String fxml) throws Exception {
        FXMLLoader loader =
                new FXMLLoader(Main.class.getResource(fxml));
        stage.setScene(new Scene(loader.load(), 700, 500));
    }

    public static void main(String[] args) {
        launch();
    }
}
