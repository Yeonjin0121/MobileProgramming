package hyeonseo.ai.a2024project;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class listData implements Parcelable {
    private String date;
    private int iv_pill; // 이미지 리소스 ID
    private Uri imageUri; // 이미지 URI

    private String tv_Ptotal;
    private String tv_Dtype1;
    private String tv_Dtype2;
    private String tv_Dtype3;

    private int pillLocPic;  // pill_locPic 추가

    // 기존 이미지 리소스 ID를 사용하는 생성자
    public listData(String date, int imageResId, String tv_Ptotal, String tv_Dtype1, String tv_Dtype2, String tv_Dtype3){
        this.date = date;
        this.iv_pill = imageResId;
        this.tv_Ptotal = tv_Ptotal;
        this.tv_Dtype1 = tv_Dtype1;
        this.tv_Dtype2 = tv_Dtype2;
        this.tv_Dtype3 = tv_Dtype3;
    }

    // URI를 사용하는 생성자
    public listData(String date, Uri imageUri, String tv_Ptotal, String tv_Dtype1, String tv_Dtype2, String tv_Dtype3) {
        this.date = date;
        this.imageUri = imageUri;
        this.tv_Ptotal = tv_Ptotal;
        this.tv_Dtype1 = tv_Dtype1;
        this.tv_Dtype2 = tv_Dtype2;
        this.tv_Dtype3 = tv_Dtype3;
    }

    // Getter 메서드들
    public String getDate() { return date; }
    public int getIv_pill() { return iv_pill; }
    public Uri getImageUri() { return imageUri; }
    public String getTv_Ptotal() { return tv_Ptotal; }
    public String getTv_Dtype1() { return tv_Dtype1; }
    public String getTv_Dtype2() { return tv_Dtype2; }
    public String getTv_Dtype3() { return tv_Dtype3; }

    public int getPillLocPic() { return pillLocPic; }  // pillLocPic에 대한 getter 추가

    // Parcelable 구현
    protected listData(Parcel in) {
        date = in.readString();
        iv_pill = in.readInt();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        tv_Ptotal = in.readString();
        tv_Dtype1 = in.readString();
        tv_Dtype2 = in.readString();
        tv_Dtype3 = in.readString();
        pillLocPic = in.readInt();
    }

    public static final Creator<listData> CREATOR = new Creator<listData>() {
        @Override
        public listData createFromParcel(Parcel in) {
            return new listData(in);
        }

        @Override
        public listData[] newArray(int size) {
            return new listData[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeInt(iv_pill);
        dest.writeParcelable(imageUri, flags);
        dest.writeString(tv_Ptotal);
        dest.writeString(tv_Dtype1);
        dest.writeString(tv_Dtype2);
        dest.writeString(tv_Dtype3);
        dest.writeInt(pillLocPic);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
