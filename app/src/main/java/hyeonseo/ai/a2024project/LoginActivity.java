package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 시스템 창 적합성을 비활성화하여 전체 화면 모드를 활성화
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_login);

        // 시스템 바 크기만큼 패딩을 추가하여 UI가 겹치지 않도록 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // LoginRegisterActivity에서 전달된 user_type 값 받기
        Intent intent = getIntent();
        String userType = intent.getStringExtra("user_type");

        // 전달된 user_type 값을 디버깅용으로 로그 출력
        android.util.Log.d("LoginActivity", "Received user_type: " + userType);

        // 화면에 표시할 텍스트뷰 설정
        TextView textView = findViewById(R.id.userTypeText);


        // user_type 값에 따라 화면 내용 설정
        if ("collecter".equals(userType)) {
            textView.setText("수거자 로그인 화면");
        } else if ("client".equals(userType)) {
            textView.setText("의뢰자 로그인 화면");
        } else {
            textView.setText("사용자 타입 정보가 올바르지 않습니다.");
            android.util.Log.w("LoginActivity", "Invalid user_type value: " + userType);
        }

        // 로그인 버튼과 입력 필드 추가
        EditText usernameEditText = findViewById(R.id.edtUsername);
        EditText passwordEditText = findViewById(R.id.edtPassword);
        Button loginButton = findViewById(R.id.btnLogin);

        // 로그인 버튼 클릭 시 동작 정의
        loginButton.setOnClickListener(v -> {
            // 입력된 값 받기
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();  // password는 넘기지 않음

            // 사용자 데이터 가져오기
            HashMap<String, UserData> userDatabase = RegisterActivity.getUserDatabase();

            // 로그인 정보 확인
            if (userDatabase.containsKey(username)) {
                UserData userData = userDatabase.get(username);
                if (userData.password.equals(password)) {
                    // 로그인 성공 시 Toast 메시지 표시
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    // 로그인 성공 후, MainActivity로 이동
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mainIntent.putExtra("user_type", userData.userType);  // user_type 전달
                    mainIntent.putExtra("username", username);           // username 전달
                    mainIntent.putExtra("name", userData.name);           // 이름 전달
                    mainIntent.putExtra("email", userData.email);         // 이메일 전달
                    startActivity(mainIntent);
                    finish(); // 현재 로그인 화면 종료
                } else {
                    // 비밀번호 오류
                    passwordEditText.setError("아이디 또는 비밀번호가 올바르지 않습니다.");
                }
            } else {
                // 아이디 없음
                usernameEditText.setError("아이디 또는 비밀번호가 올바르지 않습니다.");
            }
        });
    }
}