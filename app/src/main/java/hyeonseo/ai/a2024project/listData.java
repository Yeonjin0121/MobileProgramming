package hyeonseo.ai.a2024project;

public class listData {
    private String date;
    private int iv_pill;
    private String tv_Ptotal , tv_Dtype1, tv_Dtype2, tv_Dtype3;

    public listData(String date, int iv_pill, String tv_Ptotal, String tv_Dtype1, String tv_Dtype2, String tv_Dtype3){
        this.date = date;
        this.iv_pill = iv_pill;
        this.tv_Ptotal = tv_Ptotal;
        this.tv_Dtype1 = tv_Dtype1;
        this.tv_Dtype2 = tv_Dtype2;
        this.tv_Dtype3 = tv_Dtype3;

    }

    public String getDate() {return date;}
    public int getIv_pill() {return iv_pill;}
    public String getTv_Ptotal(){return tv_Ptotal;}
    public String getTv_Dtype1(){return tv_Dtype1;}
    public String getTv_Dtype2(){return tv_Dtype2;}
    public String getTv_Dtype3(){return tv_Dtype3;}


}
