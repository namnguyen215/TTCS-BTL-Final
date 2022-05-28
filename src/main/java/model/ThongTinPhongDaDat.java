package model;

public class ThongTinPhongDaDat {
    private Phong phong;
    private DatPhong datPhong;
    private PhongDuocDat phongDuocDat;

    public ThongTinPhongDaDat() {
    }

    public ThongTinPhongDaDat(Phong phong, DatPhong datPhong, PhongDuocDat bookedRoom) {
        this.phong = phong;
        this.datPhong = datPhong;
        this.phongDuocDat = bookedRoom;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public DatPhong getBooking() {
        return datPhong;
    }

    public void setBooking(DatPhong booking) {
        this.datPhong = booking;
    }

    public PhongDuocDat getPhongDuocDat() {
        return phongDuocDat;
    }

    public void setPhongDuocDat(PhongDuocDat phongDuocDat) {
        this.phongDuocDat = phongDuocDat;
    }
}
