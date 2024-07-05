package fr.example.gd_quiz;

import java.util.ArrayList;
import java.util.List;

public class Theme {
    private String name;
    private List<List<Question>> questionSeries;

    public Theme(String name, List<List<Question>> theme1Series) {
        this.name = name;
        this.questionSeries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<List<Question>> getQuestionSeries() {
        return questionSeries;
    }

    public void addQuestionSeries(List<Question> series) {
        questionSeries.add(series);
    }
}
