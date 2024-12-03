package com.example.mobile_project_chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

public class BakingFragment extends Fragment {
    private BakingViewModel viewModel;
    private TextInputEditText promptEditText;
    private Button sendButton;
    private TextView resultText;
    private ProgressBar progressBar;

    public static BakingFragment newInstance() {
        return new BakingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BakingViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_baking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        promptEditText = view.findViewById(R.id.promptEditText);
        sendButton = view.findViewById(R.id.sendButton);
        resultText = view.findViewById(R.id.resultText);
        progressBar = view.findViewById(R.id.progressBar);

        // Set default text
        promptEditText.setText(getString(R.string.prompt_placeholder));
        resultText.setText(getString(R.string.results_placeholder));

        // Set up button click listener
        sendButton.setOnClickListener(v -> {
            String prompt = promptEditText.getText().toString();
            if (!prompt.isEmpty()) {
                viewModel.sendPrompt(prompt);
            }
        });

        // Observe UI state changes
        viewModel.getUiState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState instanceof UiState.Loading) {
                progressBar.setVisibility(View.VISIBLE);
                resultText.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                resultText.setVisibility(View.VISIBLE);

                if (uiState instanceof UiState.Success) {
                    resultText.setTextColor(requireContext().getColor(android.R.color.tab_indicator_text));
                    resultText.setText(((UiState.Success) uiState).getOutputText());
                } else if (uiState instanceof UiState.Error) {
                    resultText.setTextColor(requireContext().getColor(com.google.android.material.R.color.error_color_material_dark));
                    resultText.setText(((UiState.Error) uiState).getErrorMessage());
                }
            }
        });
    }
}