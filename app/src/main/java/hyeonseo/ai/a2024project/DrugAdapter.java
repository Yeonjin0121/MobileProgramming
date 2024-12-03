package hyeonseo.ai.a2024project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> { // 수정: RecyclerView.Adapter로 변경

    private List<DrugItem> drugList;

    public DrugAdapter(List<DrugItem> drugList) {
        this.drugList = drugList;
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug, parent, false);
        return new DrugViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        DrugItem drugItem = drugList.get(position);
        holder.drugName.setText(drugItem.getName());
        holder.drugDescription.setText(drugItem.getDescription());
        holder.drugCount.setText(String.valueOf(drugItem.getCount()));

        holder.plusButton.setOnClickListener(v -> {
            drugItem.setCount(drugItem.getCount() + 1);
            holder.drugCount.setText(String.valueOf(drugItem.getCount()));
        });

        holder.minusButton.setOnClickListener(v -> {
            if (drugItem.getCount() > 0) {
                drugItem.setCount(drugItem.getCount() - 1);
                holder.drugCount.setText(String.valueOf(drugItem.getCount()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return drugList.size();
    }

    public static class DrugViewHolder extends RecyclerView.ViewHolder {
        TextView drugName, drugDescription, drugCount;
        Button plusButton, minusButton;

        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);
            drugName = itemView.findViewById(R.id.drug_name);
            drugDescription = itemView.findViewById(R.id.drug_description);
            drugCount = itemView.findViewById(R.id.drug_count_text);
            plusButton = itemView.findViewById(R.id.plus_button);
            minusButton = itemView.findViewById(R.id.minus_button);
        }
    }
}
