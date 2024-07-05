package fr.example.gd_quiz;

import android.annotation.SuppressLint;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView playerNameTextView;
    private TextView questionTextView;
    private TextView progressTextView;
    private CheckBox answerCheckBox1, answerCheckBox2, answerCheckBox3, answerCheckBox4;

    private List<List<Question>> themeQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerNameTextView = findViewById(R.id.playerNameTextView);
        questionTextView = findViewById(R.id.questionTextView);
        progressTextView = findViewById(R.id.progressTextView);
        answerCheckBox1 = findViewById(R.id.answerCheckBox1);
        answerCheckBox2 = findViewById(R.id.answerCheckBox2);
        answerCheckBox3 = findViewById(R.id.answerCheckBox3);
        answerCheckBox4 = findViewById(R.id.answerCheckBox4);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedTheme")) {
            String selectedTheme = intent.getStringExtra("selectedTheme");
            playerNameTextView.setText(selectedTheme); // Afficher le thème sélectionné par le joueur

            // Récupérer les questions pour le thème sélectionné à l'aide de ThemeManager
            ThemeManager themeManager = new ThemeManager(this); // Assurez-vous que ThemeManager a un constructeur prenant Context
            themeQuestions = themeManager.getThemeQuestions(selectedTheme);

            // Afficher la première question ou effectuer d'autres opérations selon votre logique
            showNextQuestion();
        }

        // Gestionnaire d'événements pour les CheckBox
        setupCheckBoxListeners();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < themeQuestions.size()) {
            List<Question> currentQuestionSet = themeQuestions.get(currentQuestionIndex);
            if (!currentQuestionSet.isEmpty()) {
                Question question = currentQuestionSet.get(0);
                questionTextView.setText(question.getQuestionText());
                String[] answers = question.getAnswers();
                answerCheckBox1.setText(answers[0]);
                answerCheckBox2.setText(answers[1]);
                answerCheckBox3.setText(answers[2]);
                answerCheckBox4.setText(answers[3]);
                resetCheckBoxes(); // Réinitialiser la sélection de réponse
                currentQuestionIndex++;
                progressTextView.setText("Question " + currentQuestionIndex + "/" + themeQuestions.size());
            }
        } else {
            endGame(); // Fin du jeu si toutes les questions ont été posées
        }
    }

    private void setupCheckBoxListeners() {
        answerCheckBox1.setOnCheckedChangeListener(checkBoxChangeListener);
        answerCheckBox2.setOnCheckedChangeListener(checkBoxChangeListener);
        answerCheckBox3.setOnCheckedChangeListener(checkBoxChangeListener);
        answerCheckBox4.setOnCheckedChangeListener(checkBoxChangeListener);
    }

    private void resetCheckBoxes() {
        answerCheckBox1.setChecked(false);
        answerCheckBox2.setChecked(false);
        answerCheckBox3.setChecked(false);
        answerCheckBox4.setChecked(false);
    }

    private void checkAnswer() {
        List<Question> currentQuestionSet = themeQuestions.get(currentQuestionIndex - 1);
        Question question = currentQuestionSet.get(0);
        boolean isAnswerCorrect = true;

        // Vérifier si les réponses sélectionnées sont correctes
        if (answerCheckBox1.isChecked() != question.isAnswerCorrect(0)) {
            isAnswerCorrect = false;
        }
        if (answerCheckBox2.isChecked() != question.isAnswerCorrect(1)) {
            isAnswerCorrect = false;
        }
        if (answerCheckBox3.isChecked() != question.isAnswerCorrect(2)) {
            isAnswerCorrect = false;
        }
        if (answerCheckBox4.isChecked() != question.isAnswerCorrect(3)) {
            isAnswerCorrect = false;
        }

        // Afficher le message approprié selon la réponse de l'utilisateur
        if (isAnswerCorrect) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Faux!", Toast.LENGTH_SHORT).show();
        }

        showNextQuestion(); // Passer à la question suivante après avoir vérifié la réponse
    }

    private CheckBox.OnCheckedChangeListener checkBoxChangeListener = new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // Appeler checkAnswer seulement si une réponse est cochée
            if (isChecked) {
                checkAnswer();
            }
        }
    };

    private void endGame() {
        // Implémentez ici la logique pour la fin du jeu, par exemple afficher le score final
        Toast.makeText(this, "Jeu Terminé! Votre score est " + score, Toast.LENGTH_SHORT).show();
        // Ajoutez ici la redirection vers une activité de fin de jeu ou une autre logique nécessaire
    }
}
