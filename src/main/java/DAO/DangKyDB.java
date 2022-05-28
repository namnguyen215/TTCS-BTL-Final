package DAO;

import java.sql.*;

public class DangKyDB extends DBController {
    private final String layThongTinTaiKhoan = "Select * from User where email= ?";
    private final String themMoiTaiKhoan = "Insert into User value(default,?,?,'customer')";
    private final String themMoiKhachHang = "Insert into Customer value(default,?,?,?,?,?)";


    public boolean themTaiKhoanMoi(String username, String password){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(themMoiTaiKhoan);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                if(ps != null) ps.close();
                if (rs != null) rs .close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;

    }

    public Boolean themKhachHangMoi(String ten,
                                    String tuoi,
                                    String diaChi,
                                    String soDienThoai,
                                    Integer idTaiKhoan){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(themMoiKhachHang);
            ps.setString(1, ten);
            ps.setInt(2, Integer.parseInt(tuoi));
            ps.setString(3, diaChi);
            ps.setString(4, soDienThoai);
            ps.setInt(5, idTaiKhoan);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(connection!= null)  connection.close();
                if(ps != null) ps.close();
                if (rs != null) rs .close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean kiemTraTaiKhoan(String tenDangNhap){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(layThongTinTaiKhoan);
            ps.setString(1, tenDangNhap);
            rs = ps.executeQuery();
            if (rs.next()){
                return false;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.print("Loi syntax");
        } finally {
            try {
                if(connection!= null)  connection.close();
                if(ps != null) ps.close();
                if (rs != null) rs .close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Integer layTaiKhoanBangId(String tenDangNhap){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(layThongTinTaiKhoan);
            ps.setString(1, tenDangNhap);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.print("Loi syntax");
        } finally {
            try {
                if(connection!= null)  connection.close();
                if(ps != null) ps.close();
                if (rs != null) rs .close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
