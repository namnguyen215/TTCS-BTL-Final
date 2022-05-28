package DAO;


import model.TopDiaDiem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDB extends DBController {
    public List<TopDiaDiem> getTopLocation(){
        Connection connection = getConnection();
        List<TopDiaDiem> list=new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            ps = connection.prepareStatement("select distinct room.position_city, count(id) from Room room group by position_city order by count(id) desc limit 6");
            System.out.println(ps);
            result = ps.executeQuery();
            while (result.next()){
                TopDiaDiem TopDiaDiem =new TopDiaDiem(result.getString("position_city"),result.getInt("count(id)") );
                list.add(TopDiaDiem);
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
                e.printStackTrace();
            }
        }
        return list;
    }

}
