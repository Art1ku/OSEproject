module group.survey {
    requires javafx.controls;
    requires javafx.fxml;


    opens group.survey to javafx.fxml;
    exports group.survey;
    exports group.survey.model;
    opens group.survey.model to javafx.fxml;
    exports group.survey.controllers;
    opens group.survey.controllers to javafx.fxml;
    exports group.survey.util;
    opens group.survey.util to javafx.fxml;
}