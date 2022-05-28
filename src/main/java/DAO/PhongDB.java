package DAO;

import model.Phong;
import model.DanhSachPhongTheoDiaDiem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class PhongDB extends DBController{
    public static String removeAccent(String s) {


        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    public List<DanhSachPhongTheoDiaDiem> getListRoomByLocation(String position){
        String position1 = removeAccent(position).toLowerCase();
        List<DanhSachPhongTheoDiaDiem> list=new ArrayList<>();
        String sql = "select data.id , data.name , data.numberOfBedrooms ,data.price, data.rate , data.url\n" +
                "from  \n" +
                "(select room.id as id, room.name , room.numberOfBedrooms ,room.price, room.rate , roomimageurl.url,\n" +
                "\t\tROW_NUMBER() OVER (PARTITION BY room.id ) AS RowNumber\n" +
                "from Room room, RoomImageUrl roomimageurl \n" +
                "where room.id = roomimageurl.roomId and room.position_city like '%"+position1+"%') as data\n" +
                "where RowNumber=1;";
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement(sql);
            System.out.println(ps);
            result = ps.executeQuery();
            while (result.next()){
                DanhSachPhongTheoDiaDiem DanhSachPhongTheoDiaDiem =new DanhSachPhongTheoDiaDiem(result.getInt("id"),result.getString("name"),result.getInt("numberOfBedrooms"),result.getFloat("rate"),result.getString("url"),result.getInt("price") );
                list.add(DanhSachPhongTheoDiaDiem);
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
    public List<Phong> getDetailRoomById(String id){

        List<Phong> list=new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM Room  where id ='"+id+"';");
            System.out.println(ps);
            result = ps.executeQuery();
            while (result.next()){
                Phong room=new Phong(result.getInt("id"),
                        result.getString("name"),result.getString("position_district"),result.getString("position_city"),result.getInt("area"),result.getInt("numberOfBedrooms"),result.getInt("numberOfBathrooms"),result.getInt("numberOfFloors"),result.getFloat("rate"),result.getString("description"),result.getFloat("price"));
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

}
