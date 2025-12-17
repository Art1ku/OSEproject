package group.survey.util;

import java.io.*;
import java.util.List;

public class SurveyFileManager {

    private static final String FILE = "survey_history.txt";

    public static void save(String title, List<String> answers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(title);
            bw.newLine();
            for (String a : answers) {
                bw.write("- " + a);
                bw.newLine();
            }
            bw.write("-----");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadAll() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ignored) {}
        return sb.toString();
    }
}
