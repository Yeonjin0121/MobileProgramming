package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AboutDetails extends AppCompatActivity {

    static ImageView pillImg;

    Gallery gallery;

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_details);

        Intent intent = getIntent();

        // 약 정보 받기
        for (int i = 1; i <= 5; i++) {
            String drugName = intent.getStringExtra("drug" + i + "_name");
            String drugCount = intent.getStringExtra("drug" + i + "_count");
            if (drugName != null && drugCount != null) {
                Log.d("DrugInfo", "약 이름: " + drugName + ", 약 개수: " + drugCount);
            }
        }

        // 주소, 날짜, 시간 받기
        String address = intent.getStringExtra("address");
        String selectedDate = intent.getStringExtra("selected_date");
        ArrayList<String> selectedTimes = intent.getStringArrayListExtra("selected_times");

        Log.d("UserInfo", "주소: " + address + ", 날짜: " + selectedDate);
        for (String time : selectedTimes) {
            Log.d("SelectedTime", time);
        }

        // 사진 처리
        ArrayList<String> imageUris = intent.getStringArrayListExtra("image_uris");
        for (String uri : imageUris) {
            Log.d("ImageURI", uri);
            // 이미지 URI를 표시하거나 RecyclerView에 추가
        }


        gallery = findViewById(R.id.galleryView);

        GalleryView galleryView = new GalleryView(this);
        gallery.setAdapter(galleryView);

        backBtn = findViewById(R.id.back_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}