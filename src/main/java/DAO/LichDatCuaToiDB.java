package DAO;

import model.LichDatCuaToi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LichDatCuaToiDB extends DBController {

    public List<LichDatCuaToi> layBookingTheoIdKhachHang(int customerId) {

        List<LichDatCuaToi> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            String sql="select bookingId, id as roomId, checkIn, checkOut, name, url, price " +
                    "from(select bookingId,r.id, checkIn, checkOut, name,url, " +
                    "            ROW_NUMBER() over (partition by  r.id, bookingId) as RowNumber, " +
                    "            price " +
                    "     from Booking bk, BookedRoom br,Room r, RoomImageUrl ri " +
                    "     where br.bookingId = bk.id and br.roomId=r.id and ri.roomId=r.id and bk.customerId ="+customerId+" ) as data " +
                    "where RowNumber=1;";
            ps = connection.prepareStatement(sql);
            System.out.println(ps);
            result = ps.executeQuery();
            while (result.next()) {
                //int maDatPhong, int maPhong, Date ngayDen, Date ngayDi, String tenPhong, String urlAnh,float giaTien
                LichDatCuaToi LichDatCuaToi = new LichDatCuaToi(result.getInt("bookingId"), result.getInt("roomId"),
                        result.getDate("checkIn"),result.getDate("checkOut"),
                        result.getString("name"),result.getString("url"), result.getFloat("price"));
                list.add(LichDatCuaToi);
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
