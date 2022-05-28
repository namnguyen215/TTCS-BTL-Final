package controller.DangNhap;

import DAO.NguoiDungDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="DangNhap",urlPatterns = "/login")
public class DangNhap extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        model.DangNhap duLieu = null;
        JsonObject jobj = this.gson.fromJson(req.getReader(), JsonObject.class);
        String taikhoan =jobj.get("username").getAsString();
        String matkhau =jobj.get("password").getAsString();
        NguoiDungDB db = new NguoiDungDB();
        String trangThai="200";
        String thongDiep ="Đăng nhập thành công!";
//        String url="";
        if (db.kiemTraNguoiDung(taikhoan,matkhau) == false){
            trangThai="500";
            thongDiep="Đăng nhập không thành công, sai tài khoản, mật khẩu";
        }
        else {
            duLieu=db.layNguoiDung(taikhoan,matkhau);
            session.setAttribute("role",duLieu.getChucVu());
        }
        PrintWriter out = resp.getWriter();
        JsonObject jsonTraVe=new JsonObject();
        jsonTraVe.addProperty("status",trangThai);
        jsonTraVe.addProperty("message",thongDiep);
        jsonTraVe.add("data",gson.toJsonTree(duLieu));
        out.write(gson.toJson(jsonTraVe));
        out.close();
    }
}
