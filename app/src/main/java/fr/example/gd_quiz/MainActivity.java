package fr.example.gd_quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText playerNameEditText;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String PLAYER_NAME_KEY = "playerName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerNameEditText = findViewById(R.id.playerNameEditText);
        Button startButton = findViewById(R.id.startButton);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String savedPlayerName = sharedPreferences.getString(PLAYER_NAME_KEY, "");
        playerNameEditText.setText(savedPlayerName);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlayerName();
                // Passer à l'écran de jeu
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                String playerName = playerNameEditText.getText().toString();
                intent.putExtra(PLAYER_NAME_KEY, playerName);
                startActivity(intent);
            }
        });
    }

    private void savePlayerName() {
        String playerName = playerNameEditText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME_KEY, playerName);
        editor.apply();
    }
}
