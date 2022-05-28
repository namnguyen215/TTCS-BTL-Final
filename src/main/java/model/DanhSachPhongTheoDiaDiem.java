package model;

public class DanhSachPhongTheoDiaDiem {
    private int id;
    private String ten;
    private int soPhongNgu;
    private float danhGia;
    private String anhPhong;
    private int gia;

    public DanhSachPhongTheoDiaDiem(int id, String name, int numberOfBedrooms, float rate, String room_images, int price) {
        this.id = id;
        this.ten = name;


        this.soPhongNgu = numberOfBedrooms;
        this.danhGia = rate;
        this.anhPhong = room_images;
        this.gia = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoPhongNgu() {
        return soPhongNgu;
    }

    public void setSoPhongNgu(int soPhongNgu) {
        this.soPhongNgu = soPhongNgu;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }

    public String getAnhPhong() {
        return anhPhong;
    }

    public void setAnhPhong(String anhPhong) {
        this.anhPhong = anhPhong;
    }
}
