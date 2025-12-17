package group.survey.controllers;

import group.survey.Main;
import group.survey.model.*;
import group.survey.util.SurveyFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SurveyController {

    @FXML private Label questionLabel;
    @FXML private Label progressLabel;
    @FXML private VBox optionsBox;

    private Survey survey;
    private int index;
    private List<String> answers;
    private ToggleGroup group;

    @FXML
    public void initialize() {
        survey = MainMenuController.selectedSurvey;

        // Сбрасываем всё при заходе на опрос
        index = 0;
        answers = new ArrayList<>();

        loadQuestion();
    }

    private void loadQuestion() {
        if (index >= survey.getQuestions().size()) return;

        Question q = survey.getQuestions().get(index);

        progressLabel.setText("Question " + (index + 1) +
                " / " + survey.getQuestions().size());
        questionLabel.setText(q.getText());

        optionsBox.getChildren().clear();
        group = new ToggleGroup(); // создаём новый ToggleGroup

        for (String option : q.getOptions()) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(group);
            optionsBox.getChildren().add(rb);
        }
    }

    public void next() throws Exception {
        if (group.getSelectedToggle() == null) return;

        // Добавляем ответ текущего вопроса
        answers.add(((RadioButton) group.getSelectedToggle()).getText());
        index++;

        if (index < survey.getQuestions().size()) {
            loadQuestion();
        } else {
            // Сохраняем ответы и возвращаемся в меню
            SurveyFileManager.save(survey.getTitle(), answers);
            Main.switchScene("main-menu.fxml");
        }
    }
}
