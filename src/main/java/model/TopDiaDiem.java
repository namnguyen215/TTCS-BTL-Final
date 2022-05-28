package model;

public class TopDiaDiem {
    private String thanhPho;
    private int soLuong;

    public TopDiaDiem(String thanhPho, int soLuong) {
        this.thanhPho = thanhPho;
        this.soLuong = soLuong;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
