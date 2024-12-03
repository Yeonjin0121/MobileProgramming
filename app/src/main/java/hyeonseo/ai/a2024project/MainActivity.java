package hyeonseo.ai.a2024project;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Fragment homeFragment;
    private Fragment listFragment; // 동적으로 clientList 또는 collectorList로 설정
    private Fragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_menu);

        // CollecterClientActivity에서 전달된 데이터 수신
        String userType = getIntent().getStringExtra("user_type");

        String username = getIntent().getStringExtra("username");
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        // 사용자 유형에 따라 프래그먼트 초기화
        if ("client".equals(userType)) {
            homeFragment = new HomeClientActivity();
            listFragment = new ClientList();
        } else if ("collecter".equals(userType)) {
            homeFragment = new HomeCollecterActivity();
            listFragment = new CollectorList();
        }
        myPageFragment = new MyPage();

        // MyPage 프래그먼트 생성 후 데이터 전달
        myPageFragment = MyPage.newInstance(username, name, userType, email);

        // 기본 프래그먼트 설정
        setFragment(homeFragment);

        // BottomNavigationView 클릭 리스너
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                if (menuItem.getItemId() == R.id.tab_home) {
                    selectedFragment = homeFragment;
                } else if (menuItem.getItemId() == R.id.tab_diary) {
                    selectedFragment = listFragment;
                } else if (menuItem.getItemId() == R.id.tab_profile) {
                    selectedFragment = myPageFragment;
                }

                if (selectedFragment != null) {
                    setFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }
}
