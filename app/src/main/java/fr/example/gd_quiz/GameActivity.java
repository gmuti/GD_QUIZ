package fr.example.gd_quiz;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView playerNameTextView;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String PLAYER_NAME_KEY = "playerName";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerNameTextView = findViewById(R.id.playerNameTextView); // Assurez-vous d'avoir un TextView avec cet id dans activity_game.xml

        // Récupérer le nom du joueur depuis SharedPreferences
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String playerName = sharedPreferences.getString(PLAYER_NAME_KEY, "");

        // Afficher le nom du joueur dans le TextView
        playerNameTextView.setText(playerName);
    }
}
