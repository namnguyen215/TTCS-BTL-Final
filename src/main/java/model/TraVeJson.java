package model;

import java.util.ArrayList;
import java.util.*;
public class TraVeJson<T> {
    String trangThai, thongDiep;
    List<T> duLieu =new ArrayList<>();

    public TraVeJson(String trangThai, String thongDiep, List<T> duLieu) {
        this.trangThai = trangThai;
        this.thongDiep = thongDiep;
        this.duLieu = duLieu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongDiep() {
        return thongDiep;
    }

    public void setThongDiep(String thongDiep) {
        this.thongDiep = thongDiep;
    }

    public List<T> getDuLieu() {
        return duLieu;
    }

    public void setDuLieu(List<T> duLieu) {
        this.duLieu = duLieu;
    }
}
