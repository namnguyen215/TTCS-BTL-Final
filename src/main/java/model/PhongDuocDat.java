package model;

public class PhongDuocDat {
    private int id, maDatPhong, maPhong;
    private float danhGia;

    public PhongDuocDat(int id, int bookingId, int roomId, float danhGia) {
        this.id = id;
        this.maDatPhong = bookingId;
        this.maPhong = roomId;
        this.danhGia = danhGia;
    }

    public PhongDuocDat() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(int maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }
}
