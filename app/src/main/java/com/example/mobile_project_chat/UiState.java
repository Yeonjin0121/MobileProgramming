package com.example.mobile_project_chat;

// Sealed class를 interface와 static 내부 클래스로 구현
public interface UiState {
    // Initial state
    class Initial implements UiState {
        private static Initial instance = null;

        private Initial() {}

        public static Initial getInstance() {
            if (instance == null) {
                instance = new Initial();
            }
            return instance;
        }
    }

    // Loading state
    class Loading implements UiState {
        private static Loading instance = null;

        private Loading() {}

        public static Loading getInstance() {
            if (instance == null) {
                instance = new Loading();
            }
            return instance;
        }
    }

    // Success state
    class Success implements UiState {
        private final String outputText;

        public Success(String outputText) {
            this.outputText = outputText;
        }

        public String getOutputText() {
            return outputText;
        }
    }

    // Error state
    class Error implements UiState {
        private final String errorMessage;

        public Error(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}