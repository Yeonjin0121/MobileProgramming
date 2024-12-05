package hyeonseo.ai.a2024project;

import android.net.Uri;

public class listData {
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
}
