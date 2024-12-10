package hyeonseo.ai.a2024project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
public class AboutDetailsCollecter extends AppCompatActivity {

    static ImageView pillImg;

    Gallery gallery;

    Button backBtn;
    Button successBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_details_collecter);

        Intent intent = getIntent();




        gallery = findViewById(R.id.galleryView);

        GalleryView galleryView = new GalleryView(this);
        gallery.setAdapter(galleryView);

        backBtn = findViewById(R.id.back_button);

        successBtn = findViewById(R.id.collecter_success);

        successBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void showDialog(){
        AlertDialog.Builder msgBuilder = new AlertDialog.Builder(AboutDetailsCollecter.this)
                .setTitle("수거 수락")
                .setMessage("해당 약물을 수거 하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AboutDetailsCollecter.this, "수거 수락 완료", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AboutDetailsCollecter.this, "수거 수락 취소", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog msgDlg = msgBuilder.create();
        msgDlg.show();
    }




}

