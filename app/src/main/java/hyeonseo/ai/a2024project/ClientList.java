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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientList.
     */
    public static ClientList newInstance(String param1, String param2) {
        ClientList fragment = new ClientList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_client_list, container, false);

        // RecyclerView setup
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewClient);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<listData> dataList = new ArrayList<>();

        dataList.add(new listData("2024.01.21", R.drawable.pill1, "총 07개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.04.05", R.drawable.pill3, "총 34개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.06.21", R.drawable.pill6, "총 27개", "알약 (조제약)", "알약 (정제형)", ""));
        dataList.add(new listData("2024.06.28", R.drawable.pill1, "총 13개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.12.01", R.drawable.pill4, "총 14개", "알약 (조제약)", "", ""));
        dataList.add(new listData("2024.12.29", R.drawable.pill5, "총 18개", "TypeA", "TypeB", "TypeC"));


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

            // 약 정보 및 총 개수 계산
            int totalDrugCount = 0;
            StringBuilder drugSummary = new StringBuilder();
            String drugType1 = "";
            String drugType2 = "";
            String drugType3 = "";

            if (drugNames != null && drugCounts != null) {
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
            }

            // 데이터 추가 (MainActivity에서 전달받은 데이터 사용)
            dataList.add(new listData(
                    date,
                    firstImageUri, // 첫 번째 이미지 URI 사용
                    "총 " + totalDrugCount + "개",
                    drugType1, // 약 종류 1
                    drugType2, // 약 종류 2
                    drugType3  // 약 종류 3
            ));
        }



        // Setting the adapter
        listAdapter adapter = new listAdapter(dataList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // 데이터 변경 시 어댑터 갱신
        return rootView;
    }
}
