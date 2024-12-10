package hyeonseo.ai.a2024project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import android.net.Uri;

public class ClientList extends Fragment {
    private List<listData> dataList = new ArrayList<>();  // 기존 데이터를 저장할 리스트
    private listAdapter adapter;

    public ClientList() {
        // 기존에 있는 데이터 초기화
        dataList.add(new listData("2024.01.21", R.drawable.pill1, "총 07개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.04.05", R.drawable.pill3, "총 34개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.06.21", R.drawable.pill6, "총 27개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.06.28", R.drawable.pill1, "총 13개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.12.01", R.drawable.pill4, "총 14개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.12.29", R.drawable.pill5, "총 18개", "TypeA", "TypeB", "TypeC"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_client_list, container, false);

        // RecyclerView 설정
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewClient);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // userType 가져오기
        String userType = getArguments() != null ? getArguments().getString("user_type") : "client";

        // Adapter 초기화
        adapter = new listAdapter(dataList, userType);
        recyclerView.setAdapter(adapter);


        // MainActivity로부터 전달된 데이터 처리
        handleReceivedData();

        return rootView;
    }

    private void handleReceivedData() {
        // MainActivity로부터 전달된 Intent 데이터 수신
        Bundle args = getArguments();
        if (args != null) {
            String username = args.getString("username");
            String userType = args.getString("user_type");
            String date = args.getString("date");
            ArrayList<String> times = args.getStringArrayList("times");
            ArrayList<String> drugNames = args.getStringArrayList("drug_names");
            ArrayList<String> drugCounts = args.getStringArrayList("drug_counts");
            ArrayList<String> imageUris = args.getStringArrayList("image_uris");

            // Log the received data
            Log.d("ClientList", "Username: " + username);
            Log.d("ClientList", "User Type: " + userType);
            Log.d("ClientList", "Date: " + date);

            // 수거 신청 데이터가 있으면 dataList에 추가
            if (date != null && !date.isEmpty() && drugNames != null && !drugNames.isEmpty()) {
                // 약 정보 및 총 개수 계산
                int totalDrugCount = 0;
                String drugType1 = "";
                String drugType2 = "";
                String drugType3 = "";

                int drugIndex = 0;
                for (int i = 0; i < drugNames.size(); i++) {
                    String name = drugNames.get(i);
                    int count = Integer.parseInt(drugCounts.get(i));
                    totalDrugCount += count;

                    // 약 종류가 0이 아닌 경우에만 추가
                    if (count > 0) {
                        if (drugIndex == 0) {
                            drugType1 = name + " (" + count + "개)";
                        } else if (drugIndex == 1) {
                            drugType2 = name + " (" + count + "개)";
                        } else if (drugIndex == 2) {
                            drugType3 = name + " (" + count + "개)";
                        }
                        drugIndex++;
                    }
                }

                // 선택한 시간 문자열
                StringBuilder timeSummary = new StringBuilder();
                if (times != null) {
                    for (String time : times) {
                        timeSummary.append(time).append(". ");
                        Log.d("ClientList", "Selected time: " + time);
                    }
                }

                // 이미지 URI 리스트가 비어 있지 않으면 첫 번째 이미지 사용
                Uri firstImageUri = null;
                if (imageUris != null && !imageUris.isEmpty()) {
                    firstImageUri = Uri.parse(imageUris.get(0));  // 첫 번째 이미지 URI로 변환
                    Log.d("ClientList", "Image URI: " + firstImageUri);

                }

                // 기존 dataList에 새로운 데이터 추가
                dataList.add(new listData(
                        date,
                        firstImageUri, // 첫 번째 이미지 URI 사용
                        "총 " + totalDrugCount + "개",
                        drugType1, // 약 종류 1
                        drugType2, // 약 종류 2
                        drugType3  // 약 종류 3
                ));

                // RecyclerView 갱신
                adapter.notifyItemInserted(dataList.size() - 1);  // 추가된 아이템만 갱신
            } else {
                Log.d("ClientList", "No new data to add.");
            }
        }


    }
}
