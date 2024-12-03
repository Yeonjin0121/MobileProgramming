package com.example.mobile_project_chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private BakingViewModel bakingViewModel;
    private TextInputEditText promptEditText;
    private Button sendButton;
    private TextView resultText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        // Initialize ViewModel
        bakingViewModel = new ViewModelProvider(this).get(BakingViewModel.class);

        // Initialize views
        promptEditText = findViewById(R.id.promptEditText);
        sendButton = findViewById(R.id.sendButton);
        resultText = findViewById(R.id.resultText);
        progressBar = findViewById(R.id.progressBar);

        // Set default text
        promptEditText.setText(getString(R.string.prompt_placeholder));
        resultText.setText(getString(R.string.results_placeholder));

        // Set up button click listener
        sendButton.setOnClickListener(v -> {
            String prompt = promptEditText.getText().toString();
            if (!prompt.isEmpty()) {
                bakingViewModel.sendPrompt(prompt);
            }
        });

        // Observe UI state changes
        bakingViewModel.getUiState().observe(this, uiState -> {
            if (uiState instanceof UiState.Loading) {
                progressBar.setVisibility(View.VISIBLE);
                resultText.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                resultText.setVisibility(View.VISIBLE);

                if (uiState instanceof UiState.Success) {
                    resultText.setTextColor(getColor(android.R.color.tab_indicator_text));
                    resultText.setText(((UiState.Success) uiState).getOutputText());
                } else if (uiState instanceof UiState.Error) {
                    resultText.setTextColor(getColor(com.google.android.material.R.color.error_color_material_dark));
                    resultText.setText(((UiState.Error) uiState).getErrorMessage());
                }
            }
        });
    }
}