package DAO;

import java.sql.*;

public class HuyPhongDB extends DBController{
    private String xoaDatPhong = "DELETE FROM Booking WHERE id = ?";
    private String chonPhongDatVoiId = "Select * FROM Booking WHERE id = ?";

    private int customerId=-1;
    public boolean chonPhongDatBangId(String id){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(chonPhongDatVoiId);
            ps.setString(1, id);

            rs = ps.executeQuery();
            if (rs.next()){
                customerId=rs.getInt("customerId");
                return true;
            }
        } catch (SQLException e) {
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
        return false;
    }

    public void xoaDatPhong(String id){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(xoaDatPhong);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
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
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
