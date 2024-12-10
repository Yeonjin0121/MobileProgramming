package hyeonseo.ai.a2024project;

import android.net.Uri;
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

public class CollectorList extends Fragment {
    private List<listData> dataList = new ArrayList<>();
    private listAdapter adapter;

    public CollectorList() {
        // 기존 데이터 초기화
        dataList.add(new listData("2024.01.21", R.drawable.pill1, "총 07개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.03.10", R.drawable.pill2, "총 24개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.04.05", R.drawable.pill3, "총 34개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.05.07", R.drawable.pill4, "총 14개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.05.11", R.drawable.pill5, "총 1개", "알약 (정제형)", "", ""));
        dataList.add(new listData("2024.06.21", R.drawable.pill6, "총 27개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.06.28", R.drawable.pill1, "총 13개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.08.04", R.drawable.pill2, "총 24개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.11.18", R.drawable.pill3, "총 19개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.12.01", R.drawable.pill4, "총 14개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.06.21", R.drawable.pill6, "총 27개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.12.29", R.drawable.pill5, "총 18개", "TypeA", "TypeB", "TypeC"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collector_list, container, false);

        // RecyclerView 설정
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCollector);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // userType 가져오기
        String userType = getArguments() != null ? getArguments().getString("user_type") : "collecter";
        Log.d("CollectorList", "Received userType: " + userType);
        // Adapter 초기화
        adapter = new listAdapter(dataList, userType);
        recyclerView.setAdapter(adapter);

        // MainActivity로부터 전달된 데이터 처리
        handleReceivedData();

        return rootView;
    }

    private void handleReceivedData() {
        Bundle args = getArguments();
        if (args == null) return;

        // 데이터 수신
        String date = args.getString("date");
        ArrayList<String> times = args.getStringArrayList("times");
        ArrayList<String> drugNames = args.getStringArrayList("drug_names");
        ArrayList<String> drugCounts = args.getStringArrayList("drug_counts");
        ArrayList<String> imageUris = args.getStringArrayList("image_uris");

        if (date != null && drugNames != null && drugCounts != null) {
            // 약 데이터 처리
            int totalDrugCount = 0;
            String[] drugTypes = new String[3];

            for (int i = 0; i < drugNames.size(); i++) {
                int count = Integer.parseInt(drugCounts.get(i));
                if (count > 0) {
                    totalDrugCount += count;
                    if (i < drugTypes.length) {
                        drugTypes[i] = drugNames.get(i) + " (" + count + "개)";
                    }
                }
            }

            // 첫 번째 이미지 URI 처리
            Uri firstImageUri = null;
            if (imageUris != null && !imageUris.isEmpty()) {
                firstImageUri = Uri.parse(imageUris.get(0));
            }

            // 데이터 추가
            dataList.add(new listData(
                    date,
                    firstImageUri, // 기본 이미지 처리
                    "총 " + totalDrugCount + "개",
                    drugTypes[0] != null ? drugTypes[0] : "",
                    drugTypes[1] != null ? drugTypes[1] : "",
                    drugTypes[2] != null ? drugTypes[2] : ""
            ));
            adapter.notifyItemInserted(dataList.size() - 1);
        } else {
            Log.d("CollectorList", "필요한 데이터가 없습니다.");
        }
    }
}
