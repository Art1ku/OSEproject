package group.survey.util;

import group.survey.model.Question;
import group.survey.model.Survey;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyStorage {

    private static final String FILE = "surveys.txt";

    // сохраняем все опросы
    public static void saveAll(List<Survey> surveys) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Survey s : surveys) {
                bw.write("SURVEY:" + s.getTitle());
                bw.newLine();

                for (Question q : s.getQuestions()) {
                    bw.write("Q:" + q.getText());
                    bw.newLine();
                    for (String o : q.getOptions()) {
                        bw.write("O:" + o);
                        bw.newLine();
                    }
                }
                bw.write("END");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // загружаем все опросы
    public static List<Survey> loadAll() {
        List<Survey> result = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return result;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Survey currentSurvey = null;
            List<Question> questions = null;
            String currentQuestion = null;
            List<String> options = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("SURVEY:")) {
                    currentSurvey = new Survey(line.substring(7), new ArrayList<>());
                    questions = new ArrayList<>();
                } else if (line.startsWith("Q:")) {
                    // сохраняем предыдущий вопрос, если он есть
                    if (currentQuestion != null && options != null) {
                        questions.add(new Question(currentQuestion, options));
                    }
                    currentQuestion = line.substring(2);
                    options = new ArrayList<>();
                } else if (line.startsWith("O:")) {
                    if (options != null) options.add(line.substring(2));
                } else if (line.equals("END")) {
                    // сохраняем последний вопрос
                    if (currentQuestion != null && options != null) {
                        questions.add(new Question(currentQuestion, options));
                        currentQuestion = null;
                        options = null;
                    }
                    // сохраняем опрос
                    if (currentSurvey != null && questions != null) {
                        currentSurvey.setQuestions(questions);
                        result.add(currentSurvey);
                    }
                    currentSurvey = null;
                    questions = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
