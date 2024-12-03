package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ApplyCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_collection);

        // RecyclerView 초기화
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // 데이터 생성
        List<DrugItem> drugList = new ArrayList<>();
        drugList.add(new DrugItem("Drug A", "Description of Drug A"));
        drugList.add(new DrugItem("Drug B", "Description of Drug B"));
        drugList.add(new DrugItem("Drug C", "Description of Drug C"));

        // 어댑터 설정
        DrugAdapter adapter = new DrugAdapter(drugList);
        recyclerView.setAdapter(adapter);

        // 레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 다음 버튼 설정
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ApplyInfoActivity로 전환
                Intent intent = new Intent(ApplyCollectionActivity.this, ApplyInfoActivity.class);
                startActivity(intent);
                finish(); // 현재 HowToUseActivity 종료
            }
        });
    }
}