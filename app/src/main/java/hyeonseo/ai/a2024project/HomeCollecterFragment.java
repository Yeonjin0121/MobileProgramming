package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;


public class HomeCollecterFragment extends Fragment {

    private String userType; // 전달받은 사용자 타입
    private String username; // 전달받은 사용자 이름

    // newInstance() 메서드 추가
    public static HomeCollecterFragment newInstance(String userType, String username) {
        HomeCollecterFragment fragment = new HomeCollecterFragment();
        Bundle args = new Bundle();
        args.putString("user_type", userType);
        args.putString("username", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_collecter_activity, container, false);

        // 시스템 바 패딩 처리
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // LoginActivity에서 전달받은 데이터 처리
        if (getArguments() != null) {
            userType = getArguments().getString("user_type");
            username = getArguments().getString("username");
        }

        // TextView 설정
        TextView textView = view.findViewById(R.id.home_textView);
        textView.setText(username + " 님은 현재\n 새싹등급이에요!");

        // FrameLayout 클릭 이벤트 설정
        FrameLayout frameLayout = view.findViewById(R.id.howtouseservice);
        frameLayout.setOnClickListener(v -> {
            Intent howToUseIntent = new Intent(getActivity(), HowToUseServiceActivity.class);
            howToUseIntent.putExtra("user_type", userType);
            howToUseIntent.putExtra("username", username);
            startActivity(howToUseIntent);
        });

        return view;
    }
}