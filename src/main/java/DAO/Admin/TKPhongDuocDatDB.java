package DAO.Admin;

import DAO.DBController;
import model.PhongDuocDat;
import model.ThongTinPhongDaDat;
import model.DatPhong;
import model.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TKPhongDuocDatDB extends DBController {
    private final String layTatCaPhongDuocDat ="select * " +
            "from phongDuocDat bk, Room r, Booking b " +
            "where bk.roomId=r.id and bk.bookingId=b.id";
    private final String layPhongDuocDatTheoId ="Select * from phongDuocDat where id= ?";
    private final String xoaTheoId ="delete from phongDuocDat where id= ?;";
    private final String kiemTraDatPhong ="select * from Booking where id=?;";
    private final String kiemTraPhong ="select * from Room where id=?;";
    private final String capNhatPhongDuocDat ="update phongDuocDat " +
                            "set id=?, bookingId=?, roomId=?, rate=? " +
                            "where id=?";


    public List<ThongTinPhongDaDat> layThongTinPhongDuocDat(){
        List<ThongTinPhongDaDat> list=new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(layTatCaPhongDuocDat);
            result = ps.executeQuery();
            while (result.next()){
                Phong room=new Phong();
                DatPhong booking=new DatPhong();
                PhongDuocDat phongDuocDat=new PhongDuocDat();
                ThongTinPhongDaDat thongTinPhongDaDat =new ThongTinPhongDaDat();
                phongDuocDat.setId(result.getInt("bk.id"));
                phongDuocDat.setMaDatPhong(result.getInt("bookingId"));
                phongDuocDat.setMaPhong(result.getInt("roomId"));
                phongDuocDat.setDanhGia(result.getFloat("bk.rate"));
                room.setId(result.getInt("r.id"));
                room.setTen(result.getString("name"));
                room.setViTriQuan(result.getString("position_district"));
                room.setViTriThanhPho(result.getString("position_city"));
                room.setDienTich(result.getInt("area"));
                room.setSoPhongNgu(result.getInt("numberOfBedrooms"));
                room.setSoPhongTam(result.getInt("numberOfBathrooms"));
                room.setSoTang(result.getInt("numberOfFloors"));
                room.setDanhGia(result.getFloat("r.rate"));
                room.setMoTa(result.getString("description"));
                booking.setId(result.getInt("b.id"));
                booking.setCustomerId(result.getInt("customerId"));
                booking.setNgayDen(result.getDate("checkIn"));
                booking.setNgayDi(result.getDate("checkOut"));
                thongTinPhongDaDat.setPhongDuocDat(phongDuocDat);
                thongTinPhongDaDat.setBooking(booking);
                thongTinPhongDaDat.setPhong(room);
                list.add(thongTinPhongDaDat);
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

    public boolean checkphongDuocDatById(int id){
        PhongDuocDat phongDuocDat=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(layPhongDuocDatTheoId);
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

    public PhongDuocDat layThongTinPhongDuocDatTheoId(int id){
        PhongDuocDat phongDuocDat=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(layPhongDuocDatTheoId);
            ps.setInt(1,id);
            result = ps.executeQuery();
            if (result.next()){
                phongDuocDat=new PhongDuocDat(result.getInt("id"),result.getInt("bookingId"),result.getInt("roomId"),
                         result.getFloat("rate"));
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
        return phongDuocDat;
    }

    public void xoaPhongDuocDatTheoId(int id){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(xoaTheoId);
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


    public boolean coTheUpdateDuoc(PhongDuocDat phongDuocDat){
        Connection connection = getConnection();
        PreparedStatement psBooking = null,psRoom=null;
        ResultSet resultRoom = null,resultBooking=null;
        try {
            psBooking = connection.prepareStatement(kiemTraDatPhong);
            psBooking.setInt(1,phongDuocDat.getMaDatPhong());

            psRoom=connection.prepareStatement(kiemTraPhong);
            psRoom.setInt(1,phongDuocDat.getMaPhong());

            resultBooking=psBooking.executeQuery();
            resultRoom=psRoom.executeQuery();

            if(resultBooking.next() && resultRoom.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Loi syntax");
            return false;
        } finally {
            try {
                if (connection != null) connection.close();
                if (psRoom != null) psRoom.close();
                if (psBooking != null) psBooking.close();
                if (resultRoom != null) resultRoom.close();
                if (resultBooking != null) resultBooking.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
        return false;
    }
    public boolean capNhatPhongDuocDat(PhongDuocDat phongDuocDat){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(capNhatPhongDuocDat);
            ps.setInt(1,phongDuocDat.getId());
            ps.setInt(2,phongDuocDat.getMaDatPhong());
            ps.setInt(3,phongDuocDat.getMaPhong());
            ps.setFloat(4,phongDuocDat.getDanhGia());
            ps.setInt(5,phongDuocDat.getId());
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
    public static void main(String[] args) {
        TKPhongDuocDatDB db=new TKPhongDuocDatDB();
        PhongDuocDat c=new PhongDuocDat(29,109,20,7);
        System.out.println(db.coTheUpdateDuoc(c));
//        db.updatephongDuocDat(c);
//        db.addphongDuocDat(c);
    }
}
