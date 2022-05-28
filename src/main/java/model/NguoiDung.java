package model;

public class NguoiDung {
    private String email, matKhau, chucVu;
    private int id;
    public NguoiDung(String email, String matKhau) {
        this.email = email;
        this.matKhau = matKhau;
    }

    public NguoiDung(int id , String email, String matKhau, String chucVu) {
        this.id = id;
        this.email = email;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
