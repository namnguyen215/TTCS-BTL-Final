package controller.ThongKe;

import DAO.Admin.TKPhongDuocDatDB;
import DAO.NguoiDungDB;
import controller.mahoa.MaHoa;
import model.TraVeJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.PhongDuocDat;
import model.ThongTinPhongDaDat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ThongKePhongDuocDat", urlPatterns = "/manage-bookedroom")


public class ThongKePhongDuocDat extends HttpServlet {
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
        String trangThai = "200";
        String thongDiep = "OK";
        if(accessToken!=null){
            isAdmin=ThongKePhongDuocDat.kiemTraVaiTro(accessToken);
        }
        TKPhongDuocDatDB db = new TKPhongDuocDatDB();

        List<ThongTinPhongDaDat> list = null;
        if(isAdmin==true){
            list =db.layThongTinPhongDuocDat();
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
    protected synchronized void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String accessToken=req.getHeader("accessToken");
        boolean isAdmin=false;
        if(accessToken!=null){
            isAdmin=ThongKePhongDuocDat.kiemTraVaiTro(accessToken);
        }
        PhongDuocDat bk = this.gson.fromJson(req.getReader(), PhongDuocDat.class);
        TKPhongDuocDatDB db = new TKPhongDuocDatDB();
        String trangThai = "200";
        String thongDiep = "Update th??nh c??ng";
        if (db.coTheUpdateDuoc(bk) && isAdmin==true) {
            db.capNhatPhongDuocDat(bk);
        } else {
            thongDiep = "Kh??ch h??ng kh??ng t???n t???i ho???c ng?????i d??ng kh??ng c?? quy???n!";
            trangThai = "500";
        }
        PrintWriter out = resp.getWriter();
        List<ThongTinPhongDaDat> duLieu = db.layThongTinPhongDuocDat();
        TraVeJson<ThongTinPhongDaDat> json = new TraVeJson<>(trangThai, thongDiep, duLieu);
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
            isAdmin=ThongKePhongDuocDat.kiemTraVaiTro(accessToken);
        }
        JsonObject jobj = this.gson.fromJson(req.getReader(), JsonObject.class);
        String id = jobj.get("id").getAsString();
        // perform authentication
        String trangThai = "200";
        String thongDiep = "Id n??y kh??ng t???n t???i!";
        PrintWriter out = resp.getWriter();
        TKPhongDuocDatDB admindb = new TKPhongDuocDatDB();
        if(isAdmin==false){
            trangThai="500";
            thongDiep="Ng?????i d??ng kh??ng c?? quy???n";
        }
        else if (id != null) {
            PhongDuocDat bookedRoom = admindb.layThongTinPhongDuocDatTheoId(Integer.parseInt(id));
            if (bookedRoom == null) {
                trangThai = "500";
                thongDiep = "Ph??ng n??y ch??a ???????c ?????t!";
            } else {
                admindb.xoaPhongDuocDatTheoId(Integer.parseInt(id));
                thongDiep = "X??a ph??ng ???????c th??nh c??ng!";
            }

        }
        List<ThongTinPhongDaDat> duLieu = admindb.layThongTinPhongDuocDat();
        TraVeJson<ThongTinPhongDaDat> json = new TraVeJson<>(trangThai, thongDiep, duLieu);
        out.write(gson.toJson(json));
        out.close();
    }
}

