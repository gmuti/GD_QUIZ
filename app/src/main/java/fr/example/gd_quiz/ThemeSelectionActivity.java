package fr.example.gd_quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThemeSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);

        Button theme1Button = findViewById(R.id.theme1Button);
        Button theme2Button = findViewById(R.id.theme2Button);

        theme1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity("Theme 1");
            }
        });

        theme2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity("Theme 2");
            }
        });
    }

    private void startGameActivity(String selectedTheme) {
        Intent gameIntent = new Intent(ThemeSelectionActivity.this, GameActivity.class);
        gameIntent.putExtra("selectedTheme", selectedTheme);
        startActivity(gameIntent);
    }
}
