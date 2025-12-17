# Survey JavaFX Application

## Overview
This is a desktop application built with **JavaFX** for creating, taking, and managing surveys. Users can create surveys with multiple questions, answer them, and view a history of completed surveys.

The project demonstrates the use of JavaFX for GUI, file I/O for persistent storage, and basic OOP principles.

---

## Features

1. **Main Menu**
    - Displays all available surveys.
    - Button to create a new survey.
    - Button to view survey history.

2. **Survey Creation**
    - Add multiple questions with options.
    - Save surveys to a file.
    - Validate input to ensure questions and options exist.

3. **Taking Surveys**
    - Display one question at a time.
    - Radio buttons for selecting answers.
    - Progress indicator.
    - Save answers to history file.

4. **Survey History**
    - View all completed surveys with their answers.

5. **Persistent Storage**
    - Surveys stored in `surveys.txt`.
    - Completed answers stored in `survey_history.txt`.

---

## Project Structure

src/

└─ group/survey/

├─ Main.java # Entry point of the application

├─ controllers/

│ ├─ MainMenuController.java # Handles main menu UI

│ ├─ SurveyController.java # Handles survey taking UI

│ ├─ SurveyCreatorController.java # Handles survey creation UI

│ └─ HistoryController.java # Handles survey history UI

├─ model/

│ ├─ Survey.java # Survey object containing title and questions

│ └─ Question.java # Question object with text and options

└─ util/

├─ SurveyFileManager.java # Saves and loads survey answers

└─ SurveyStorage.java # Saves and loads survey templates


resources/

└─ group/survey/

├─ main-menu.fxml

├─ survey.fxml

├─ survey-creator.fxml

└─ history.fxml



---

## Getting Started

### Prerequisites
- Java 17+
- JavaFX 22+ (or compatible version)

### Running the Project
1. Clone the repository:

```bash
git clone 
```

Open the project in IntelliJ IDEA or another Java IDE with JavaFX support.

Run Main.java:


File Formats
surveys.txt

Stores survey templates.

## Format:

SURVEY:Survey Title
Q:Question Text
O:Option 1
O:Option 2
END

survey_history.txt

Stores completed survey answers.

## Format:

Survey Title
- Answer 1
- Answer 2
-----

## Implementation Details
## Controllers

MainMenuController: Loads surveys, handles navigation.

SurveyCreatorController: Adds questions, validates input, saves surveys.

SurveyController: Displays questions, manages answers, updates progress.

HistoryController: Loads and displays completed survey answers.

## Models

Survey: Holds survey title and list of questions.

Question: Holds question text and list of options.

## Utilities

SurveyStorage: Handles saving/loading surveys from surveys.txt.

SurveyFileManager: Handles saving/loading completed survey answers from survey_history.txt.

## Possible Improvements

Support multiple question types (checkbox, dropdown).

Allow editing existing surveys.

Filter or search in survey history.

Add CSS styling for modern UI.

Add user authentication and multiple user support.

Export survey results to CSV or Excel.