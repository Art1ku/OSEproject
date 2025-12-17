package group.survey.controllers;

import group.survey.Main;
import group.survey.util.SurveyFileManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HistoryController {

    @FXML private TextArea area;

    @FXML
    public void initialize() {
        area.setText(SurveyFileManager.loadAll());
    }

    public void back() throws Exception {
        Main.switchScene("main-menu.fxml");
    }
}
