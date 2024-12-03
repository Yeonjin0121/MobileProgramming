package hyeonseo.ai.a2024project;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class listViewHolder extends RecyclerView.ViewHolder {
    TextView dateTextView, totalPill, drugType1, drugType2, drugType3;
    ImageView pillImage;


    public listViewHolder(@Nullable View itemView){
        super(itemView);

        dateTextView = itemView.findViewById(R.id.dateTextView);
        pillImage = itemView.findViewById(R.id.pill_locPic);
        totalPill = itemView.findViewById(R.id.totalPill);
        drugType1 = itemView.findViewById(R.id.drugType1);
        drugType2 = itemView.findViewById(R.id.drugType2);
        drugType3 = itemView.findViewById(R.id.drugType3);
    }
}
