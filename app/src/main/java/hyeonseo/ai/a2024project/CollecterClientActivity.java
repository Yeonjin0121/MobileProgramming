package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class CollecterClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collecter_client);

        // 수거자 버튼과 의뢰자 버튼
        Button collecterButton = findViewById(R.id.collecterbutton);
        Button clientButton = findViewById(R.id.clientbutton);

        // 수거자 버튼 클릭 시
        collecterButton.setOnClickListener(v -> {
            Intent intent = new Intent(CollecterClientActivity.this, LoginRegisterActivity.class);
            intent.putExtra("user_type", "collecter"); // 수거자 정보를 전달
            startActivity(intent);
        });

        // 의뢰자 버튼 클릭 시
        clientButton.setOnClickListener(v -> {
            Intent intent = new Intent(CollecterClientActivity.this, LoginRegisterActivity.class);
            intent.putExtra("user_type", "client"); // 의뢰자 정보를 전달
            startActivity(intent);
        });
    }
}
