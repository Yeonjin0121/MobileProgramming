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
        dataList.add(new listData("2024.10.28", R.drawable.ic_launcher_background, "총 34개", "Type1", "Type2", "Type3"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));
        dataList.add(new listData("2024.10.28", R.drawable.ic_launcher_background, "총 34개", "Type1", "Type2", "Type3"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));
        dataList.add(new listData("2024.10.28", R.drawable.ic_launcher_background, "총 34개", "Type1", "Type2", "Type3"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));
        dataList.add(new listData("2024.10.28", R.drawable.ic_launcher_background, "총 34개", "Type1", "Type2", "Type3"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));
        dataList.add(new listData("2024.10.29", R.drawable.ic_launcher_background, "총 24개", "TypeA", "TypeB", "TypeC"));

        // Setting the adapter
        listAdapter adapter = new listAdapter(dataList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
