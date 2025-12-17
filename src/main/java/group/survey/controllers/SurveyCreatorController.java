package group.survey.controllers;

import group.survey.Main;
import group.survey.model.Question;
import group.survey.model.Survey;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import group.survey.util.SurveyStorage;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SurveyCreatorController {

    @FXML private TextField titleField;
    @FXML private VBox questionsBox;

    private final List<QuestionInput> questionInputs = new ArrayList<>();

    @FXML
    public void addQuestion() {
        QuestionInput qi = new QuestionInput();
        questionsBox.getChildren().add(qi.getNode());
        questionInputs.add(qi);
    }

    @FXML
    public void saveSurvey() throws Exception {

        String title = titleField.getText().trim();
        if (title.isEmpty() || questionInputs.isEmpty()) return;

        List<Question> questions = new ArrayList<>();
        for (QuestionInput qi : questionInputs) {
            Question q = qi.toQuestion();
            if (q != null) questions.add(q);
        }

        Survey newSurvey = new Survey(title, questions);

        MainMenuController.surveys.add(newSurvey);

        SurveyStorage.saveAll(MainMenuController.surveys);

        Main.switchScene("main-menu.fxml");
    }


    @FXML
    public void back() throws Exception {
        Main.switchScene("main-menu.fxml");
    }

    private static class QuestionInput {
        private final VBox node;
        private final TextField questionField;
        private final TextField optionsField;

        public QuestionInput() {
            node = new VBox(5);
            questionField = new TextField();
            questionField.setPromptText("Question text");
            optionsField = new TextField();
            optionsField.setPromptText("Options (comma separated)");
            node.getChildren().addAll(questionField, optionsField);
        }

        public VBox getNode() {
            return node;
        }

        public Question toQuestion() {
            String text = questionField.getText().trim();
            String[] opts = optionsField.getText().split(",");
            List<String> options = new ArrayList<>();
            for (String o : opts) {
                if (!o.trim().isEmpty()) options.add(o.trim());
            }
            return text.isEmpty() || options.isEmpty() ? null : new Question(text, options);
        }
    }
}
