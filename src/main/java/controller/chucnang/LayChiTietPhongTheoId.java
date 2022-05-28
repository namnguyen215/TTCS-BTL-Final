package controller.chucnang;

import DAO.PhongDB;
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

@WebServlet(name="LayChiTietPhongTheoId",urlPatterns = "/get-detail-room-by-id")
public class LayChiTietPhongTheoId extends HttpServlet {
    private Gson gson = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

//        JsonObject jobj = this.gson.fromJson(req.getReader(), JsonObject.class);
//        String id =jobj.get("id").getAsString();
        String id = req.getParameter("id");

        System.out.println("id" + id);
        PhongDB db=new PhongDB();
        List<Phong> list = db.getDetailRoomById(id);
        String body =gson.toJson(list);
        PrintWriter out = resp.getWriter();
        out.write(body);
        out.close();
    }
}
