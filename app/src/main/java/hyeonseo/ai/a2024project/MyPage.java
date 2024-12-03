package hyeonseo.ai.a2024project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPage extends Fragment {

    private static final String ARG_USERNAME = "username";
    private static final String ARG_NAME = "name";
    private static final String ARG_USER_TYPE = "userType";
    private static final String ARG_EMAIL = "email";

    // 4개의 매개변수를 받도록 newInstance() 메서드 수정
    public static MyPage newInstance(String username, String name, String userType, String email) {
        MyPage fragment = new MyPage();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putString(ARG_NAME, name);
        args.putString(ARG_USER_TYPE, userType);
        args.putString(ARG_EMAIL, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // 전달된 값을 가져옵니다
            String username = getArguments().getString(ARG_USERNAME);
            String name = getArguments().getString(ARG_NAME);
            String userType = getArguments().getString(ARG_USER_TYPE);
            String email = getArguments().getString(ARG_EMAIL);

            // 여기에 전달된 값들을 활용하여 UI 설정을 할 수 있습니다.
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page, container, false);

        TextView userIDTextView = view.findViewById(R.id.userID);
        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView userTypeTextView = view.findViewById(R.id.userTypeTextView);
        TextView emailTextView = view.findViewById(R.id.emailTextView);

        // 전달받은 데이터 설정
        String username = getArguments().getString(ARG_USERNAME);
        String name = getArguments().getString(ARG_NAME);
        String userType = getArguments().getString(ARG_USER_TYPE);
        String email = getArguments().getString(ARG_EMAIL);


        userIDTextView.setText(username);
        nameTextView.setText(name);
        userTypeTextView.setText(userType);
        emailTextView.setText(email);

        return view;
    }
}