package fr.example.gd_quiz;

public class Question {
    private String questionText;
    private String[] answers;
    private boolean[] selectedAnswers; // Tableau de booléens pour les réponses sélectionnées
    private int correctAnswerIndex; // Index de la réponse correcte

    public Question(String questionText, String[] answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.selectedAnswers = new boolean[answers.length]; // Initialiser le tableau des réponses sélectionnées
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getAnswers() {
        return answers;
    }

    public boolean[] getSelectedAnswers() {
        return selectedAnswers;
    }

    public void setSelectedAnswers(boolean[] selectedAnswers) {
        this.selectedAnswers = selectedAnswers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isAnswerCorrect(int answerIndex) {
        return selectedAnswers[answerIndex] == (answerIndex == correctAnswerIndex);
    }


}
