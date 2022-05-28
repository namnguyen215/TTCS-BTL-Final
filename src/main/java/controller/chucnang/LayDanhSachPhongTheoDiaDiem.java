package controller.chucnang;

import DAO.PhongDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.DanhSachPhongTheoDiaDiem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="GetListRoomByLocation",urlPatterns = "/get-list-room-by-location")
public class LayDanhSachPhongTheoDiaDiem extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String position = req.getParameter("position");

        PhongDB db = new PhongDB();
        List<DanhSachPhongTheoDiaDiem> list = db.getListRoomByLocation(position);
        String body =gson.toJson(list);
        PrintWriter out = resp.getWriter();
        out.write(body);
        out.close();
    }
}
