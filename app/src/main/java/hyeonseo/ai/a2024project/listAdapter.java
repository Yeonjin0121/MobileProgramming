package hyeonseo.ai.a2024project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listViewHolder> {
    private List<listData> dataList;

    // 생성자
    public listAdapter(List<listData> dataList) {
        this.dataList = dataList;
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

        // RecyclerView 아이템 클릭 리스너 추가
        holder.itemView.setOnClickListener(v -> {
            // 클릭 시 AboutDetails Activity로 이동
            Intent intent = new Intent(v.getContext(), AboutDetails.class);
            v.getContext().startActivity(intent); // AboutDetails 페이지로 이동
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
