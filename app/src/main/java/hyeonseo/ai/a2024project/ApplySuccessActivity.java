package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ApplySuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apply_success);


        Button homeButton = findViewById(R.id.next_button);  // 버튼의 ID를 XML에 맞게 설정
        homeButton.setOnClickListener(v -> {
            // MainActivity로 이동
            Intent nextIntent = new Intent(ApplySuccessActivity.this, MainActivity.class);



            startActivity(nextIntent); // 액티비티 전환
            finish(); // 현재 HowToUseActivity 종료
        });
    }
}