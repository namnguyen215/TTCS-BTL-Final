package model;


import java.sql.Date;

public class DatPhong {
    int id, maKhachHang;
    Date ngayDen, ngayDi;

    public DatPhong() {
    }

    public DatPhong(int id, int customerId, Date checkIn, Date checkOut) {
        this.id = id;
        this.maKhachHang = customerId;
        this.ngayDen = checkIn;
        this.ngayDi = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return maKhachHang;
    }

    public void setCustomerId(int customerId) {
        this.maKhachHang = customerId;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }
}
