package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    // 전역 저장소: 앱 실행 중 사용자 데이터를 저장할 변수 (임시)
    private static HashMap<String, UserData> userDatabase = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 시스템 창 적합성을 비활성화하여 전체 화면 모드 활성화
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_register);

        // 회원가입 관련 정보 가져오기
        EditText edtName = findViewById(R.id.edtName);
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtConfirmPassword = findViewById(R.id.edtConfirmPassword);


        // 시스템 바 크기만큼 패딩을 추가하여 UI가 겹치지 않도록 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // LoginRegisterActivity에서 전달된 user_type 값 받기
        Intent intent = getIntent();
        String userType = intent.getStringExtra("user_type");

        // 받은 값에 따라 화면 내용 변경하기
        TextView textView = findViewById(R.id.userTypeText);
        if (userType != null) {
            if (userType.equals("collecter")) {
                textView.setText("수거자 회원가입 화면");
                // 수거자 관련 UI 처리 추가 가능
            } else if (userType.equals("client")) {
                textView.setText("의뢰자 회원가입 화면");
                // 의뢰자 관련 UI 처리 추가 가능
            }
        }

        // 회원가입 완료 버튼 설정
        Button registerButton = findViewById(R.id.btnSignup); // 버튼 ID는 XML에서 정의 필요
        registerButton.setOnClickListener(v -> {
            // 회원가입 로직 수행 (생략 가능)

            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();


            // 입력 유효성 검사
            if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 비밀번호 확인
            if (!password.equals(confirmPassword)) {
                edtConfirmPassword.setError("비밀번호가 일치하지 않습니다.");
                return;
            }

            // 중복 사용자 확인
            if (userDatabase.containsKey(username)) {
                edtUsername.setError("이미 존재하는 사용자입니다.");
                return;
            }

            // 사용자 등록
            userDatabase.put(username, new UserData(name, userType,email,password));

            // 회원가입 성공 시 Toast
            Toast.makeText(RegisterActivity.this, "회원가입이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();


            // LoginActivity로 이동
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            loginIntent.putExtra("user_type", userType); // 수거자/의뢰자 값 전달
            loginIntent.putExtra("username", username);
            loginIntent.putExtra("name", name);
            startActivity(loginIntent);
            finish(); // 현재 액티비티 종료
        });
    }



    // 사용자 정보 제공 메서드
    public static HashMap<String, UserData> getUserDatabase() {
        return userDatabase;
    }
}
