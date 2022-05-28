package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LichDatCuaToi {
    private int maDatPhong;
    private int maPhong;
    private String ngayDen,ngayDi;
    private String  tenPhong, urlAnh;
    private long soNgay;

    private String tongTien;

    public LichDatCuaToi() {


    }

    public LichDatCuaToi(int maDatPhong, int maPhong, Date ngayDen, Date ngayDi, String tenPhong, String urlAnh,float giaTien) {
        this.maDatPhong = maDatPhong;
        this.maPhong = maPhong;
        this.ngayDen = ngayDen.toString();
        this.ngayDi = ngayDi.toString();
        this.tenPhong = tenPhong;
        this.urlAnh = urlAnh;
        long diffInMillies = Math.abs(ngayDi.getTime() - ngayDen.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        this.soNgay = diff;
        this.tongTien = String.format("%.2f", soNgay*giaTien);
    }



    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getUrlAnh() {
        return urlAnh;
    }

    public void setUrlAnh(String urlAnh) {
        this.urlAnh = urlAnh;
    }

    public long getSoNgay() {
        return soNgay;
    }

    public String getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(String ngayDen) {
        this.ngayDen = ngayDen;
    }

    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public void setSoNgay(long soNgay) {
        this.soNgay = soNgay;
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

    @Override
    public String toString() {
        return "LichDatCuaToi{" +
                "maDatPhong=" + maDatPhong +
                ", maPhong=" + maPhong +
                ", ngayDen=" + ngayDen +
                ", ngayDi=" + ngayDi +
                ", tenPhong='" + tenPhong + '\'' +
                ", urlAnh='" + urlAnh + '\'' +
                ", soNgay=" + soNgay +
                ", tongTien=" + tongTien +
                '}';
    }
}
