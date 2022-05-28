package DAO;

import model.DangNhap;
import model.NguoiDung;

import java.sql.*;


public class NguoiDungDB extends DBController{

    public boolean kiemTraNguoiDung(String email, String password) {
        String sql = "select * from User where email=? and password=?";
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            result = ps.executeQuery();
            if (result.next()) return true;
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
        return false;
    }


    public DangNhap layNguoiDung(String email, String password){
        NguoiDung nguoiDung =null;
        DangNhap login = null;
        String sql="select * from User where email=? and password=?";
        System.out.println(sql);
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,password);
            result = ps.executeQuery();
            if (result.next()){
                nguoiDung =new NguoiDung(result.getInt("id"),result.getString("email"),result.getString("password"),result.getString("role"));
            }
            if(nguoiDung != null && nguoiDung.getChucVu().equalsIgnoreCase("admin")){
                String sql1="select a.name,User.role, User.id from Admin a , User where a.id = User.id and User.id = "+ nguoiDung.getId();
                ps = connection.prepareStatement(sql1);
                result = ps.executeQuery();
                if (result.next()){
                    System.out.println("result.getInt(\"id\")" + result.getInt("id"));
                    login =new DangNhap(result.getString("name"),result.getString("role"), result.getInt("id"));
                }
            }
            else if(nguoiDung != null && nguoiDung.getChucVu().equalsIgnoreCase("customer")){
                String sql1="select a.name,User.role,User.id from Customer a , User where a.id = User.id and User.id = "+ nguoiDung.getId();
                ps = connection.prepareStatement(sql1);
                result = ps.executeQuery();
                if (result.next()){
                    login =new DangNhap(result.getString("name"),result.getString("role"), result.getInt("id"));
                }
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
        return login;
    }



//    public void addUser(String username, String password){
//        String sql= "insert into login values ('"+username+"','"+password+"');";
//        System.out.println(sql);
//        Connection connection = getConnection();
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Loi syntax");
//        } finally {
//            try {
//                if (connection != null) connection.close();
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public String layRole(String id){
        String sql="select role from User where id ="+id+";";
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        String role=null;
        try {
            ps = connection.prepareStatement(sql);
            result=ps.executeQuery();
            if(result.next()){
                role= result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi syntax");
        } finally {
            try {
                if (connection != null) connection.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return role;
    }

}
