package fr.example.gd_quiz;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ThemeManager {

    private static final String THEME_PREFS = "theme_prefs";
    private static final String THEME_KEY = "themes";

    private List<Theme> themes;
    private SharedPreferences sharedPreferences;

    // Ajoutez un constructeur prenant un contexte en paramètre
    public ThemeManager(Context context) {
        // Utilisez le contexte pour obtenir les préférences partagées
        sharedPreferences = context.getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE);
        themes = loadThemes();
        if (themes == null || themes.isEmpty()) {
            initializeDefaultThemes();
            saveThemes();
        }
    }

    private void initializeDefaultThemes() {
        themes = new ArrayList<>();

        // Exemple : Ajouter des thèmes avec des séries de questions différentes
        List<List<Question>> theme1Series = new ArrayList<>();
        theme1Series.add(createQuestionSeriesForTheme1()); // Série 1 pour le thème 1
        themes.add(new Theme("Theme 1", theme1Series));

        List<List<Question>> theme2Series = new ArrayList<>();
        theme2Series.add(createQuestionSeriesForTheme2()); // Série 1 pour le thème 2
        themes.add(new Theme("Theme 2", theme2Series));

        // Ajouter d'autres thèmes avec leurs séries de questions respectives
    }

    public List<Theme> getThemes() {
        return themes;
    }

    private List<Question> createQuestionSeriesForTheme1() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1 - Theme 1?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 0));
        questions.add(new Question("Question 2 - Theme 1?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 1));
        questions.add(new Question("Question 3 - Theme 1?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 2));
        questions.add(new Question("Question 4 - Theme 1?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 3));
        questions.add(new Question("Question 5 - Theme 1?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 0));
        return questions;
    }

    private List<Question> createQuestionSeriesForTheme2() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1 - Theme 2?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 0));
        questions.add(new Question("Question 2 - Theme 2?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 1));
        questions.add(new Question("Question 3 - Theme 2?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 2));
        questions.add(new Question("Question 4 - Theme 2?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 3));
        questions.add(new Question("Question 5 - Theme 2?", new String[]{"Answer 1", "Answer 2", "Answer 3", "Answer 4"}, 0));
        return questions;
    }

    private void saveThemes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String themesJson = gson.toJson(themes);
        editor.putString(THEME_KEY, themesJson);
        editor.apply();
    }

    private List<Theme> loadThemes() {
        String themesJson = sharedPreferences.getString(THEME_KEY, null);
        if (themesJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Theme>>() {}.getType();
            return gson.fromJson(themesJson, type);
        }
        return null;
    }

    public List<List<Question>> getThemeQuestions(String themeName) {
        List<List<Question>> themeQuestions = new ArrayList<>();
        for (Theme theme : themes) {
            if (theme.getName().equals(themeName)) {
                themeQuestions = theme.getQuestionSeries();
                break;
            }
        }
        return themeQuestions;
    }
}
