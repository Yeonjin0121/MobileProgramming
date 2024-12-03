package hyeonseo.ai.a2024project;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        // Intent로 전달받은 user_type 값
        Intent intent = getIntent();
        String userType = intent.getStringExtra("user_type");

        // 로그인 버튼과 회원가입 버튼
        Button loginButton = findViewById(R.id.loginbutton);
        Button registerButton = findViewById(R.id.registerbutton);

        // 로그인 버튼 클릭 시
        loginButton.setOnClickListener(v -> {
            // 로그인 화면으로 이동
            Intent loginIntent = new Intent(LoginRegisterActivity.this, LoginActivity.class);
            loginIntent.putExtra("user_type", userType); // user_type 전달 (수거자/의뢰자)
            startActivity(loginIntent);
        });

        // 회원가입 버튼 클릭 시
        registerButton.setOnClickListener(v -> {
            // 회원가입 화면으로 이동
            Intent registerIntent = new Intent(LoginRegisterActivity.this, RegisterActivity.class);
            registerIntent.putExtra("user_type", userType); // user_type 전달 (수거자/의뢰자)
            startActivity(registerIntent);
        });

        android.util.Log.d("LoginRegisterActivity", "user_type: " + userType);

    }
}

