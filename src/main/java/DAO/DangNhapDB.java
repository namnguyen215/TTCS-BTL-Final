package DAO;

import model.DangNhap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DangNhapDB extends DBController {

    public List<DangNhap> getLoginInfo(String email , String pass) {
        List<DangNhap> list = new ArrayList<>();

        String sql = "select c.name, u.role from User u, Customer c where u.id=c.id and u.email='" + email + "' and u.password='" + pass + "';";
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(sql);
            System.out.println(ps);
            result = ps.executeQuery();
            while (result.next()) {
                DangNhap login = new DangNhap(result.getString("name"),result.getString("role"),result.getInt("id"));
                list.add(login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi syntax");
        } finally {
            try {
                if (connection != null) connection.close();
                if (ps != null) ps.close();
                if (result != null) result.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
}
