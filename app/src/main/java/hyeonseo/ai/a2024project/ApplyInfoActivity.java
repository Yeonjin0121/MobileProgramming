package hyeonseo.ai.a2024project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class ApplyInfoActivity extends AppCompatActivity {

    private ArrayList<Uri> imageList = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private ActivityResultLauncher<String> imagePickerLauncher;
    Button backBtn;

    TextView selectedDateText;
    TextView selectedTimes;


    private HashMap<Button, Boolean> buttonStateMap = new HashMap<>(); // 버튼 상태 관리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_info);

        //주소
        TextView addressDetailText = findViewById(R.id.address_detail_text);
        EditText addressDetailEdit = findViewById(R.id.address_detail_edit);
        Button changeButton = findViewById(R.id.change_button);

        changeButton.setOnClickListener(v -> {
            if (addressDetailEdit.getVisibility() == View.GONE) {
                // EditText를 보이게 하고 TextView를 숨김
                addressDetailEdit.setText(addressDetailText.getText()); // TextView의 텍스트를 EditText에 복사
                addressDetailEdit.setVisibility(View.VISIBLE);
                addressDetailText.setVisibility(View.GONE);
                changeButton.setText("저장"); // 버튼 텍스트를 "저장"으로 변경
            } else {
                // TextView를 보이게 하고 EditText를 숨김
                addressDetailText.setText(addressDetailEdit.getText()); // EditText의 텍스트를 TextView에 복사
                addressDetailEdit.setVisibility(View.GONE);
                addressDetailText.setVisibility(View.VISIBLE);
                changeButton.setText("변경"); // 버튼 텍스트를 "변경"으로 변경
            }
        });


        // 날짜 선택
        selectedDateText = findViewById(R.id.selected_date_text);
        Button selectDateButton = findViewById(R.id.select_date_button);

        // 날짜 선택 버튼 클릭 리스너
        selectDateButton.setOnClickListener(v -> showDatePickerDialog());


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String userType = intent.getStringExtra("user_type");


        // 약 정보 로깅 및 처리
        for (int i = 1; i <= 5; i++) {
            String drugName = intent.getStringExtra("drug" + i + "_name");
            int drugCount = intent.getIntExtra("drug" + i + "_count",0);
            if (drugName != null && drugCount >= 0) {
                // 약 정보 로그로 확인
                Log.d("DrugInfo", "약 이름: " + drugName + ", 약 개수: " + drugCount);
            }
        }
        // 사용자 이름과 타입 로그로 확인
        Log.d("UserInfo", "사용자 이름: " + username + ", 사용자 타입: " + userType);


        // RecyclerView 설정
        RecyclerView imageRecyclerView = findViewById(R.id.image_recycler_view);
        imageAdapter = new ImageAdapter(imageList);
        imageRecyclerView.setAdapter(imageAdapter);
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 이미지 선택 launcher 초기화
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        Log.d("ImagePicker", "선택된 이미지 URI: " + uri.toString());
                        imageList.add(uri); // 이미지 추가
                        imageAdapter.notifyItemInserted(imageList.size() - 1);
                    }
                }
        );

        // 이미지 업로드 버튼 클릭 리스너
        ImageButton uploadImageButton = findViewById(R.id.upload_image_button);
        uploadImageButton.setOnClickListener(v ->
                imagePickerLauncher.launch("image/*")
        );


        //시간 선택
        Button timeButton6 = findViewById(R.id.time_button_6);
        Button timeButton7 = findViewById(R.id.time_button_7);
        Button timeButton8 = findViewById(R.id.time_button_8);
        Button timeButton9 = findViewById(R.id.time_button_9);
        Button timeButton10 = findViewById(R.id.time_button_10);
        Button timeButton11 = findViewById(R.id.time_button_11);
        Button timeButton12 = findViewById(R.id.time_button_12);
        Button timeButton13 = findViewById(R.id.time_button_13);
        Button timeButton14 = findViewById(R.id.time_button_14);
        Button timeButton15 = findViewById(R.id.time_button_15);
        Button timeButton16 = findViewById(R.id.time_button_16);
        Button timeButton17 = findViewById(R.id.time_button_17);

        // 버튼들을 배열에 추가 (필요한 만큼)
        Button[] buttons = {timeButton6, timeButton7, timeButton8, timeButton9, timeButton10, timeButton11, timeButton12, timeButton13, timeButton14, timeButton15, timeButton16, timeButton17};

        // 초기화: 모든 버튼의 선택 상태를 false로 설정
        for (Button button : buttons) {
            buttonStateMap.put(button, false);
            button.setOnClickListener(this::onTimeButtonClick);
        }

        // 다음 버튼 설정
        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {

            Intent intentToss = new Intent(ApplyInfoActivity.this, MainActivity.class);
            intentToss.putExtra("username", username);
            intentToss.putExtra("user_type", userType);
            intentToss.putExtra("address", addressDetailText.getText().toString());
            intentToss.putExtra("date", selectedDateText.getText().toString());

// 선택한 시간 전달
            ArrayList<String> selectedTimes = new ArrayList<>();
            for (Button button : buttonStateMap.keySet()) {
                if (buttonStateMap.get(button)) {
                    selectedTimes.add(button.getText().toString());
                }
            }
// 알약 정보 전달
            ArrayList<String> drugNames = new ArrayList<>();
            ArrayList<String> drugCounts = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                String drugName = intent.getStringExtra("drug" + i + "_name");
                int drugCount = intent.getIntExtra("drug" + i + "_count", 0);
                if (drugName != null && drugCount > 0) {
                    drugNames.add(drugName);
                    drugCounts.add(String.valueOf(drugCount));
                }
            }
            intentToss.putStringArrayListExtra("drug_names", drugNames);
            intentToss.putStringArrayListExtra("drug_counts", drugCounts);

// 이미지 URI 리스트 전달
            ArrayList<String> imageUris = new ArrayList<>();
            for (Uri uri : imageList) {
                imageUris.add(uri.toString());
            }
            intentToss.putStringArrayListExtra("image_uris", imageUris);

// MainActivity로 이동
            startActivity(intentToss);


            finish(); // 현재 Activity 종료



        });

        backBtn = findViewById(R.id.back_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplyInfoActivity.this, ApplyCollectionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // 선택한 날짜를 TextView에 설정
                    String date = selectedYear + "년 " + (selectedMonth + 1) + "월 " + selectedDay + "일";
                    selectedDateText.setText(date);
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    // 시간 버튼 클릭 처리
    private void onTimeButtonClick(View view) {
        Button clickedButton = (Button) view;
        boolean isSelected = buttonStateMap.get(clickedButton);

        // 상태 변경
        if (isSelected) {
            clickedButton.setSelected(false); // 선택 해제
            buttonStateMap.put(clickedButton, false);
        } else {
            clickedButton.setSelected(true); // 선택 처리
            buttonStateMap.put(clickedButton, true);
        }
    }
}