package controller.ThongKe;

import DAO.Admin.TKPhongDB;
import DAO.NguoiDungDB;
import controller.mahoa.MaHoa;
import model.TraVeJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.Phong;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="ThongKePhong",urlPatterns = "/manage-room")


public class ThongKePhong extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    private static boolean kiemTraVaiTro(String accessToken){
        String id= MaHoa.decrypt(accessToken);
        NguoiDungDB db=new NguoiDungDB();
        String vaiTro=db.layRole(id);
        if(!vaiTro.equalsIgnoreCase("admin")){
            return false;
        }
        return true;
    }
    @Override
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKePhong.kiemTraVaiTro(accessToken);
        }
        String trangThai = "200";
        String thongDiep = "OK";
        TKPhongDB db=new TKPhongDB();
        List<Phong> danhSachPhong=null;
        if(isAdmin==true){
            danhSachPhong = db.layDanhSachPhong();
        }
        else{
            trangThai="500";
            thongDiep="Nguoi dung khong co quyen";
        }
        TraVeJson json=new TraVeJson<>(trangThai,thongDiep,danhSachPhong);
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(json));
        out.close();
    }

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKePhong.kiemTraVaiTro(accessToken);
        }
        Phong phong= this.gson.fromJson(req.getReader(), Phong.class);
        TKPhongDB tkPhongDB=new TKPhongDB();
        String trangThai="200";
        String thongDiep ="Thêm thành công";
        List<Phong> danhSachPhong=null;
        if(tkPhongDB.themPhong(phong)==false || isAdmin==false){
            trangThai="500";
            thongDiep ="Thêm không thành công";
        }
        else{
            danhSachPhong = tkPhongDB.layDanhSachPhong();
        }
        PrintWriter out = resp.getWriter();
        TraVeJson<Phong> json = new TraVeJson<>(trangThai, thongDiep, danhSachPhong);
        out.write(gson.toJson(json));
        out.close();
    }

    @Override
    protected synchronized void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKePhong.kiemTraVaiTro(accessToken);
        }
        Phong phong= this.gson.fromJson(req.getReader(), Phong.class);
        TKPhongDB tkPhongDB=new TKPhongDB();
        String trangThai="200";
        String thongDiep="Update thành công";
        if(tkPhongDB.checkRoomById(phong.getId()) && isAdmin==true){
            tkPhongDB.updateRoom(phong);
        }
        else{
            thongDiep ="Phòng không tồn tại!";
            trangThai="500";
        }
        PrintWriter out = resp.getWriter();
        List<Phong> data = tkPhongDB.layDanhSachPhong();
        TraVeJson<Phong> json = new TraVeJson<>(trangThai, thongDiep, data);
        out.write(gson.toJson(json));
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");


        JsonObject jobj= this.gson.fromJson(req.getReader(), JsonObject.class);
        String id=jobj.get("id").getAsString();
        // perform authentication
        String status="200";
        String body ="Id này không tồn tại!";
        PrintWriter out = resp.getWriter();
        TKPhongDB admindb=new TKPhongDB();
        if(id!=null){

            Phong room=admindb.getRoomById(Integer.parseInt(id));

            if (room==null)
            {
                status="500";
                body="Không tồn tại phòng này!";
            }
            else
            {
                admindb.deleteRoomById(Integer.parseInt(id));
                body="Xóa phòng thành công!";
            }

        }
        List<Phong> data = admindb.layDanhSachPhong();
        TraVeJson<Phong> json = new TraVeJson<>(status, body, data);
        out.write(gson.toJson(json));
        out.close();
    }
}

