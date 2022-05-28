package DAO.Admin;

import DAO.DBController;
import model.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TKPhongDB extends DBController {
    private final String getAllRoom="Select * from Room";
    private final String getRoomById="Select * from Room where id= ?";
    private final String deleteById="delete from Room where id= ?";
    private final String insertRoomSQL="insert into Room values(default,?,?,?,?,?,?,?,?,?,?)";

    private final String updateRoom="update Room " +
                            "set id=?, name=?, position_district=?, position_city=?, area=?, " +
                            "numberOfBedrooms=?, numberOfBathrooms=?, numberOfFloors=? ,"+
                            "rate=?, description=?, price=?"+
                            "where id=?";


    public List<Phong> layDanhSachPhong(){
        List<Phong> list=new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getAllRoom);
            result = ps.executeQuery();
            while (result.next()){
                Phong room=new Phong();
                room.setId(result.getInt("id"));
                room.setTen(result.getString("name"));
                room.setViTriQuan(result.getString("position_district"));
                room.setViTriThanhPho(result.getString("position_city"));
                room.setDienTich(result.getFloat("area"));
                room.setSoPhongNgu(result.getInt("numberOfBedrooms"));
                room.setSoPhongTam(result.getInt("numberOfBathrooms"));
                room.setSoTang(result.getInt("numberOfFloors"));
                room.setDanhGia(result.getFloat("rate"));
                room.setMoTa(result.getString("description"));
                room.setGia(result.getFloat("price"));
                list.add(room);
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

    public boolean checkRoomById(int id){
        Phong Room=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getRoomById);
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

    public Phong getRoomById(int id){
        Phong Room=null;
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(getRoomById);
            ps.setInt(1,id);
            result = ps.executeQuery();
            if (result.next()){
                Room=new Phong();
                Room.setId(result.getInt("id"));
                Room.setTen(result.getString("name"));
                Room.setViTriQuan(result.getString("position_district"));
                Room.setViTriThanhPho(result.getString("position_city"));
                Room.setDienTich(result.getFloat("area"));
                Room.setSoPhongNgu(result.getInt("numberOfBedrooms"));
                Room.setSoPhongTam(result.getInt("numberOfBathrooms"));
                Room.setSoTang(result.getInt("numberOfFloors"));
                Room.setDanhGia(result.getFloat("rate"));
                Room.setMoTa(result.getString("description"));
                Room.setGia(result.getFloat("price"));
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
        return Room;
    }

    public void deleteRoomById(int id){
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
    public boolean themPhong(Phong phong){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(insertRoomSQL);
            ps.setString(1,phong.getTen());
            ps.setString(2,phong.getViTriQuan());
            ps.setString(3,phong.getViTriThanhPho());
            ps.setFloat(4,phong.getDienTich());
            ps.setInt(5,phong.getSoPhongNgu());
            ps.setInt(6,phong.getSoPhongTam());
            ps.setInt(7,phong.getSoTang());
            ps.setFloat(8,phong.getDanhGia());
            ps.setString(9,phong.getMoTa());
            ps.setFloat(10,phong.getGia());
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
    public void updateRoom(Phong room){
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(updateRoom);
            ps.setInt(1,room.getId());
            ps.setString(2,room.getTen());
            ps.setString(3,room.getViTriQuan());
            ps.setString(4,room.getViTriThanhPho());
            ps.setFloat(5,room.getDienTich());
            ps.setInt(6,room.getSoPhongNgu());
            ps.setInt(7,room.getSoPhongTam());
            ps.setInt(8,room.getSoTang());
            ps.setFloat(9,room.getDanhGia());
            ps.setString(10,room.getMoTa());
            ps.setFloat(11,room.getGia());
            ps.setInt(12,room.getId());
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
        TKPhongDB db=new TKPhongDB();
//        Room c=new Room(27,"name5",20,"lao cai","123123");
        db.layDanhSachPhong();
//        db.updateRoom(c);
//        db.addRoom(c);
    }
}
