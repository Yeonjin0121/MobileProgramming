package hyeonseo.ai.a2024project;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryView extends BaseAdapter {

    Context context;
    Integer[] pillID = {R.drawable.pill1, R.drawable.pill2, R.drawable.pill3, R.drawable.pill4, R.drawable.pill5, R.drawable.pill6};

    ImageView imageView;
    public GalleryView(Context c){ context = c;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        imageView = new ImageView(context);
        imageView.setLayoutParams(new Gallery.LayoutParams(450,450));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(40,5,40,5);
        imageView.setImageResource(pillID[position]);

        return imageView;

    }

    @Override
    public int getCount() {
        return pillID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
