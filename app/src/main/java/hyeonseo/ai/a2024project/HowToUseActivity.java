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

public class HowToUseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_how_to_use);

        // 시스템 바 패딩 처리
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.howtouse2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // HomeClientActivity 혹은 HomeCollecterActivity에서 전달된 값 받기
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userType = intent.getStringExtra("user_type");

        // 전달된 값 디버깅 로그 확인
        android.util.Log.d("HowToUseActivity", "Username: " + username + ", User Type: " + userType);

        // "홈 화면으로" 버튼 설정
        Button homeButton = findViewById(R.id.btnHome);  // 버튼의 ID를 XML에 맞게 설정
        homeButton.setOnClickListener(v -> {
            // MainActivity로 이동
            Intent nextIntent = new Intent(HowToUseActivity.this, MainActivity.class);


            // 추가로 필요한 데이터를 전달
            nextIntent.putExtra("username", username);
            nextIntent.putExtra("user_type", userType);

            startActivity(nextIntent); // 액티비티 전환
            finish(); // 현재 HowToUseActivity 종료
        });

        // 특정 TextView 클릭 시 HowToUseServiceActivity로 이동
        TextView textView = findViewById(R.id.textViewHowToUseService); // XML에서 ID 설정 필요
        textView.setOnClickListener(v -> {
            Intent nextIntent = new Intent(HowToUseActivity.this, HowToUseServiceActivity.class);
            startActivity(nextIntent);
            finish(); // 현재 HowToUseActivity 종료
        });
    }
}