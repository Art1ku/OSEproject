package group.survey.controllers;

import group.survey.Main;
import group.survey.model.Question;
import group.survey.model.Survey;
import group.survey.util.SurveyStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    public static Survey selectedSurvey;
    public static List<Survey> surveys = new ArrayList<>();

    private static boolean loaded = false;

    @FXML
    private VBox surveysBox;

    @FXML
    public void initialize() {
        if (!loaded) {
            surveys.clear();
            surveys.addAll(SurveyStorage.loadAll());

            if (surveys.isEmpty()) {
                surveys.add(new Survey("Programming Survey", List.of(
                        new Question("Favorite language?", List.of("Java", "Python", "C++")),
                        new Question("Level?", List.of("Beginner", "Intermediate", "Advanced")),
                        new Question("Like JavaFX?", List.of("Yes", "No"))
                )));
                surveys.add(new Survey("Cooking Survey", List.of(
                        new Question("Favorite cuisine?", List.of("Italian", "Asian", "Local")),
                        new Question("How often cook?", List.of("Daily", "Sometimes", "Never"))
                )));
                surveys.add(new Survey("Gaming Survey", List.of(
                        new Question("Favorite genre?", List.of("RPG", "Shooter", "Strategy")),
                        new Question("Play often?", List.of("Yes", "No"))
                )));

                SurveyStorage.saveAll(surveys);
            }

            loaded = true;
        }

        refreshSurveyButtons();
    }

    private void refreshSurveyButtons() {
        surveysBox.getChildren().clear();

        for (Survey s : surveys) {
            Button btn = new Button(s.getTitle());
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                selectedSurvey = s;
                try {
                    Main.switchScene("survey.fxml");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            surveysBox.getChildren().add(btn);
        }
    }

    public void openHistory() throws Exception {
        Main.switchScene("history.fxml");
    }

    public void openSurveyCreator() throws Exception {
        Main.switchScene("survey-creator.fxml");
    }
}
