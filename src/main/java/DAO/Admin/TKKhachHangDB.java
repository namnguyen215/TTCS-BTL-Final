package DAO.Admin;
import DAO.DBController;
import model.KhachHang;

import java.sql.*;
import java.util.*;

public class TKKhachHangDB extends DBController {
    private final String getAllCustomer="Select * from Customer";
    private final String getCustomerById="Select * from Customer where id= ?";
    private final String deleteById="delete from Customer where id= ?";
    private final String insertCustomerSQL="insert into Customer values(default,?,?,?,?,0)";

    private final String updateCustomer="update Customer " +
                            "set id=?, name=?, age=?, address=?,phoneNumber=? " +
                            "where id=?";


    public List<KhachHang> layDanhSachKhachHang(){
        List<KhachHang> list=new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getAllCustomer);
            result = ps.executeQuery();
            while (result.next()){
                KhachHang customer = new KhachHang(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("address"),
                    result.getString("phoneNumber")
                );
                list.add(customer);
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

    public boolean kiemTraKhachHangBangId(int id){
        KhachHang customer=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getCustomerById);
            ps.setInt(1,id);
            result = ps.executeQuery();
            if (result.next()){
                return true;
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
        return false;
    }

    public KhachHang getCustomerById(int id){
        KhachHang customer=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getCustomerById);
            ps.setInt(1,id);
            result = ps.executeQuery();
            if (result.next()){
                customer=new KhachHang(result.getInt("id"),result.getString("name"),result.getInt("age"),
                         result.getString("address"),
                        result.getString("phoneNumber"));
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
        return customer;
    }

    public void deleteCustomerById(int id){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(deleteById);
            ps.setInt(1,id);
            ps.executeUpdate();
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
    }
    public boolean themKhachHang(KhachHang customer){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(insertCustomerSQL);
            ps.setString(1,customer.getTen());
            ps.setInt(2,customer.getTuoi());
            ps.setString(3,customer.getDiaChi());
            ps.setString(4,customer.getSoDienThoai());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi syntax");
            return false;
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
        return true;
    }
    public void capNhatKhachHang(KhachHang khachHang){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(updateCustomer);
            ps.setInt(1,khachHang.getId());
            ps.setString(2,khachHang.getTen());
            ps.setInt(3,khachHang.getTuoi());
            ps.setString(4,khachHang.getDiaChi());
            ps.setString(5,khachHang.getSoDienThoai());
            ps.setInt(6,khachHang.getId());
            ps.executeUpdate();
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
    }
    public static void main(String[] args) {
        TKKhachHangDB db=new TKKhachHangDB();
        KhachHang c=new KhachHang(27,"name5",20,"lao cai","123123");
        db.layDanhSachKhachHang();
//        db.updateCustomer(c);
//        db.addCustomer(c);
    }
}
