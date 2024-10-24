package vn.edu.tlu.m2151170579;

public class MenuItem {
    private String tenMon;
    private String donGia;

    public MenuItem(String tenMon, String donGia) {
        this.tenMon = tenMon;
        this.donGia = donGia;
    }

    public String getTenMon() {
        return tenMon;
    }

    public String getDonGia() {
        return donGia;
    }
}
