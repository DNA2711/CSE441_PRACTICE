package vn.edu.tlu.m2151170579;

public class Mon {
    private int maMon;
    private String tenMon;
    private float donGia;

    public Mon(int maMon, String tenMon, float donGia) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.donGia = donGia;
    }

    public int getMaMon() {
        return maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public float getDonGia() {
        return donGia;
    }
}
