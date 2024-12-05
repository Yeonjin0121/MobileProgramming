package hyeonseo.ai.a2024project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectorList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectorList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CollectorList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectorList.
     */
    public static CollectorList newInstance(String param1, String param2) {
        CollectorList fragment = new CollectorList();
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
        View rootView = inflater.inflate(R.layout.fragment_collector_list, container, false);

        // RecyclerView setup
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCollector);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));







        // Sample data for the adapter
        List<listData> dataList = new ArrayList<>();
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

        // Setting the adapter
        listAdapter adapter = new listAdapter(dataList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
