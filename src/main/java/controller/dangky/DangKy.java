package controller.dangky;

import DAO.DangKyDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DangKy", urlPatterns = "/register")

public class DangKy extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JsonObject jo = this.gson.fromJson(req.getReader(),JsonObject.class);
        DangKyDB dangKyDB = new DangKyDB();
        String tinhTrang="200";
        String tinNhan ="Bạn đã đăng ký thành công";

        String taiKhoan = jo.get("username").getAsString();
        String matKhau = jo.get("password").getAsString();

        String ten = jo.get("name").getAsString();
        String tuoi = jo.get("age").getAsString();
        String diaChi = jo.get("address").getAsString();
        String soDienThoai = jo.get("phoneNumber").getAsString();
        if (taiKhoan!= null && matKhau!= null){
            if(dangKyDB.kiemTraTaiKhoan(taiKhoan)){
                if (dangKyDB.themTaiKhoanMoi(taiKhoan, matKhau)){
                    int idTaiKhoan = dangKyDB.layTaiKhoanBangId(taiKhoan);
                    dangKyDB.themKhachHangMoi(ten, tuoi, diaChi, soDienThoai, idTaiKhoan);
                }
            } else {
                tinNhan = "Đã tồn tại Username";
                tinhTrang = "500";
            }
        }
        PrintWriter out = resp.getWriter();
        JsonObject json=new JsonObject();
        json.addProperty("status",tinhTrang);
        json.addProperty("message",tinNhan);
        out.write(gson.toJson(json));
        out.close();
    }
}
