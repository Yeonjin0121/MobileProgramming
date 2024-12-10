package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listViewHolder> {
    private List<listData> dataList;
    private String userType; // 사용자 유형

    // 생성자
    public listAdapter(List<listData> dataList, String userType) {
        this.dataList = dataList;
        this.userType = userType; // user_type 저장

        // 생성자에서 userType 로그 출력
        Log.d("listAdapter", "Constructor userType: " + userType);
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item 레이아웃을 inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_detail, parent, false);
        return new listViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        // 데이터를 뷰에 바인딩
        listData currentData = dataList.get(position);
        holder.dateTextView.setText(currentData.getDate());
        holder.pillImage.setImageResource(currentData.getIv_pill());
        holder.totalPill.setText(currentData.getTv_Ptotal());
        holder.drugType1.setText(currentData.getTv_Dtype1());
        holder.drugType2.setText(currentData.getTv_Dtype2());
        holder.drugType3.setText(currentData.getTv_Dtype3());

        // RecyclerView 아이템 클릭 리스너
        holder.itemView.setOnClickListener(v -> {
            Intent intent;
            Log.d("listAdapter", "userType: " + userType); // userType 로그 추가
            // userType에 따라 적절한 액티비티로 이동
            if ("collecter".equals(userType)) {
                Log.d("listAdapter", "Navigating to AboutDetailsCollecter"); // 로그 추가
                intent = new Intent(v.getContext(), AboutDetailsCollecter.class);
            } else {
                Log.d("listAdapter", "Navigating to AboutDetails"); // 로그 추가
                intent = new Intent(v.getContext(), AboutDetails.class);
            }
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
