package controller.DangNhap;

import DAO.HuyPhongDB;
import DAO.LichDatCuaToiDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.LichDatCuaToi;
import model.TraVeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HuyPhong", urlPatterns = "/cancel-booking")
public class CancelBooking extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JsonObject jo = this.gson.fromJson(req.getReader(), JsonObject.class);
        String id= jo.get("id").getAsString();

        String tinhTrang="200";
        String tinNhan ="Đã xoá thành công";
        HuyPhongDB huyPhongDB = new HuyPhongDB();
        if (id != null) {
            if(huyPhongDB.chonPhongDatBangId(id)){
                huyPhongDB.xoaDatPhong(id);
            } else {
                tinhTrang="500";
                tinNhan ="Không tồn tại lịch đặt phòng này";
            }
        }
        //Lay customer ID
        int customerId=huyPhongDB.getCustomerId();
        LichDatCuaToiDB db=new LichDatCuaToiDB();
        List<LichDatCuaToi> list=null;
        if(customerId!=-1){
            list = db.layBookingTheoIdKhachHang(customerId);
        }
        PrintWriter out = resp.getWriter();
        TraVeJson traVeJson=new TraVeJson<>(tinhTrang,tinNhan,list);
        out.write(gson.toJson(traVeJson));
        out.close();
    }
}
