package model;

public class KhachHang {
    private int id, tuoi;
    private String ten, diaChi, soDienThoai;

    public KhachHang() {
    }
    public KhachHang(String name, int age, String address, String phoneNumber) {
        this.tuoi = age;
        this.ten = name;
        this.diaChi = address;
        this.soDienThoai = phoneNumber;
    }
    public KhachHang(int id, String name, int age, String address, String phoneNumber) {
        this.id = id;
        this.tuoi = age;
        this.ten = name;
        this.diaChi = address;
        this.soDienThoai = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
