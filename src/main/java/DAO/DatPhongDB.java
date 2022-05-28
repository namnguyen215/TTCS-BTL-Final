package DAO;

import model.PhongDuocDat;
import model.DatPhong;

import java.sql.*;

public class DatPhongDB extends DBController {
    private final String insertBookingSQL="insert into  Booking values(default,?,?,?)";
    private final String insertBookedRoomSQL="insert into  BookedRoom values(default,?,?,?)";

    public boolean addBooking(DatPhong booking, PhongDuocDat bookedRoom){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        PreparedStatement px = null;
        PreparedStatement psLayBooking=null;
        ResultSet result = null;
        try {
            //check logic ngay check in < check out, check in > ngay hien tai
            //check trung lap phong
            ps = connection.prepareStatement(insertBookingSQL);
            ps.setInt(1,booking.getCustomerId());
            ps.setDate(2, (Date) booking.getNgayDen());
            ps.setDate(3, (Date) booking.getNgayDi());
            ps.executeUpdate();
            String layBookingVuaThemSQL="select * from Booking order by id desc limit 1";
            psLayBooking=connection.prepareStatement(layBookingVuaThemSQL);
            result=psLayBooking.executeQuery();
            DatPhong datPhong=null;
            if (result.next()){
                datPhong=new DatPhong();
                datPhong.setId(result.getInt("id"));
                datPhong.setCustomerId(result.getInt("customerId"));
                datPhong.setNgayDen(result.getDate("checkIn"));
                datPhong.setNgayDi(result.getDate("checkOut"));
            }
            px = connection.prepareStatement(insertBookedRoomSQL);
            px.setInt(1,datPhong.getId());
            px.setInt(2,bookedRoom.getMaPhong());
            px.setFloat(3,0);
            px.executeUpdate();
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
    //them booking truoc
    //get in tu booking vua them
    //truyen vao add booking

}
