package controller.ThongKe;

import DAO.Admin.TKKhachHangDB;
import model.TraVeJson;
import DAO.NguoiDungDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import controller.mahoa.MaHoa;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "ThongKeKhachHang", urlPatterns = "/manage-customer")


public class ThongKeKhachHang extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    private static boolean kiemTraVaiTro(String accessToken){
        String id= MaHoa.decrypt(accessToken);
        NguoiDungDB db=new NguoiDungDB();
        String role=db.layRole(id);
        if(!role.equalsIgnoreCase("admin")){
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
            isAdmin=ThongKeKhachHang.kiemTraVaiTro(accessToken);
        }
        String trangThai = "200";
        String thongDiep = "OK";
        TKKhachHangDB db = new TKKhachHangDB();
        List<KhachHang> list =null;
        if(isAdmin==true){
            list = db.layDanhSachKhachHang();
        }
        else{
            trangThai="500";
            thongDiep="Nguoi dung khong co quyen";
        }
        TraVeJson json=new TraVeJson<>(trangThai,thongDiep,list);
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(json));
        out.close();
    }

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        KhachHang khachHang = this.gson.fromJson(req.getReader(), KhachHang.class);
        TKKhachHangDB tkKhachHangDB = new TKKhachHangDB();
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKeKhachHang.kiemTraVaiTro(accessToken);
        }
        String trangThai = "200";
        String thongDiep = "Th??m th??nh c??ng";
        List<KhachHang> duLieu = null;
        if (tkKhachHangDB.themKhachHang(khachHang) == false || isAdmin==false) {
            trangThai = "500";
            thongDiep = "Th??m kh??ng th??nh c??ng";
        }
        else{
            duLieu = tkKhachHangDB.layDanhSachKhachHang();
        }
        PrintWriter out = resp.getWriter();
        TraVeJson<KhachHang> json = new TraVeJson<>(trangThai, thongDiep, duLieu);
        out.write(gson.toJson(json));
        out.close();

    }

    @Override
    protected synchronized void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        KhachHang khachHang = this.gson.fromJson(req.getReader(), KhachHang.class);
        TKKhachHangDB db = new TKKhachHangDB();
        String trangThai = "200";
        String thongDiep = "Update th??nh c??ng";
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKeKhachHang.kiemTraVaiTro(accessToken);
        }
        if (db.kiemTraKhachHangBangId(khachHang.getId()) && isAdmin==true) {
            db.capNhatKhachHang(khachHang);
        } else {
            thongDiep = "Kh??ch h??ng kh??ng t???n t???i ho???c ng?????i d??ng kh??ng c?? quy???n!";
            trangThai = "500";
        }
        PrintWriter out = resp.getWriter();
        List<KhachHang> duLieu = db.layDanhSachKhachHang();
        TraVeJson<KhachHang> json = new TraVeJson<>(trangThai, thongDiep, duLieu);
        out.write(gson.toJson(json));
        out.close();
    }

    @Override
    protected synchronized void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKeKhachHang.kiemTraVaiTro(accessToken);
        }
        JsonObject jobj = this.gson.fromJson(req.getReader(), JsonObject.class);
        String id = jobj.get("id").getAsString();
        // perform authentication
        String trangThai = "500";
        String thongDiep = "Id n??y kh??ng t???n t???i!";
        PrintWriter out = resp.getWriter();
        TKKhachHangDB tkKhachHangDB = new TKKhachHangDB();
        if(isAdmin==false){
            trangThai="500";
            thongDiep="Ng?????i d??ng kh??ng c?? quy???n";
        }
        else if (id != null ) {
            KhachHang customer = tkKhachHangDB.getCustomerById(Integer.parseInt(id));

            if (customer == null) {
                trangThai = "500";
                thongDiep = "Kh??ng t???n t???i kh??ch h??ng n??y!";
            } else {
                tkKhachHangDB.deleteCustomerById(Integer.parseInt(id));
                trangThai="200";
                thongDiep = "X??a kh??ch h??ng th??nh c??ng!";
            }

        }
        List<KhachHang> duLieu = tkKhachHangDB.layDanhSachKhachHang();
        TraVeJson<KhachHang> json = new TraVeJson<>(trangThai, thongDiep, duLieu);
        out.write(gson.toJson(json));
        out.close();
    }
}

