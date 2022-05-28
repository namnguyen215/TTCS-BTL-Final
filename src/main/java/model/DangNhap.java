package model;


import controller.mahoa.MaHoa;


public class DangNhap {
    private Integer id;
    private String ten;
    private String chucVu;
    private String accessToken;


    public DangNhap(String name, String role, int id) {
        this.ten = name;
        this.chucVu = role;
        this.accessToken= MaHoa.encrypt(String.valueOf(id));
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
