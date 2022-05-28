package model;

public class Phong {
    private int id;
    private String ten;
    private String viTriQuan;
    private String viTriThanhPho;
    private float dienTich;
    private int soPhongNgu;
    private int soPhongTam;
    private int soTang;
    private float danhGia;
    private String moTa;
    private float gia;

    public Phong() {
    }

    public Phong(int id, String name, String position_district, String position_city, int area, int numberOfBedrooms, int numberOfBathrooms, int numberOfFloors, float rate, String description, float price) {
        this.id = id;
        this.ten = name;
        this.viTriQuan = position_district;
        this.viTriThanhPho = position_city;
        this.dienTich = area;
        this.soPhongNgu = numberOfBedrooms;
        this.soPhongTam = numberOfBathrooms;
        this.soTang = numberOfFloors;
        this.danhGia = rate;
        this.moTa = description;
        this.gia = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getViTriQuan() {
        return viTriQuan;
    }

    public void setViTriQuan(String viTriQuan) {
        this.viTriQuan = viTriQuan;
    }

    public String getViTriThanhPho() {
        return viTriThanhPho;
    }

    public void setViTriThanhPho(String viTriThanhPho) {
        this.viTriThanhPho = viTriThanhPho;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoPhongNgu() {
        return soPhongNgu;
    }

    public void setSoPhongNgu(int soPhongNgu) {
        this.soPhongNgu = soPhongNgu;
    }

    public int getSoPhongTam() {
        return soPhongTam;
    }

    public void setSoPhongTam(int soPhongTam) {
        this.soPhongTam = soPhongTam;
    }

    public int getSoTang() {
        return soTang;
    }

    public void setSoTang(int soTang) {
        this.soTang = soTang;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
