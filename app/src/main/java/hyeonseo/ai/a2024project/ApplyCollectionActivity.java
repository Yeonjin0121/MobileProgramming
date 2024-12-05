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

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_collection);

        // RecyclerView 초기화
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // 데이터 생성
        List<DrugItem> drugList = new ArrayList<>();
        drugList.add(new DrugItem("가루약", "경구형 가루약 - 먹는 가루약"));
        drugList.add(new DrugItem("알약 (조제약)", "병원에서 진료 후 의사의 처방을 받아 약국에서 조제 받는 약"));
        drugList.add(new DrugItem("알약 (정제형)", "약물을 부형제와 압축해 일정한 모양으로 만든 제형"));
        drugList.add(new DrugItem("물약", "물약이나 시럽형으로 된 액체류"));
        drugList.add(new DrugItem("연고 등 특수 용기", "연고와 안약, 코 스프레이, 천식 흡입제와 같이 특수 용기에 보관된 약"));

        // 어댑터 설정
        DrugAdapter adapter = new DrugAdapter(drugList);
        recyclerView.setAdapter(adapter);

        // 레이아웃 매니저 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Intent로 전달받은 사용자 정보
        Intent intentFromPrevious = getIntent();
        String username = intentFromPrevious.getStringExtra("username");
        String userType = intentFromPrevious.getStringExtra("user_type");

        // 다음 버튼 설정
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ApplyInfoActivity로 전환
                Intent intent = new Intent(ApplyCollectionActivity.this, ApplyInfoActivity.class);

                // 약 이름과 개수 전달
                for (int i = 0; i < drugList.size(); i++) {
                    intent.putExtra("drug" + (i + 1) + "_name", drugList.get(i).getName()); // 약 이름
                    intent.putExtra("drug" + (i + 1) + "_count", drugList.get(i).getCount()); // 약 개수
                }
                // 사용자 데이터 추가
                intent.putExtra("username", username);
                intent.putExtra("user_type", userType);

                startActivity(intent);
                finish(); // 현재 HowToUseActivity 종료
            }
        });

        backBtn = findViewById(R.id.back_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}