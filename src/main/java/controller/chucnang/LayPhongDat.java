package controller.chucnang;

import DAO.DatPhongDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.PhongDuocDat;
import model.DatPhong;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet(name="GetBookRoom",urlPatterns = "/book-room")
public class LayPhongDat extends HttpServlet {
    private Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JsonObject jobj= this.gson.fromJson(req.getReader(), JsonObject.class);
        DatPhongDB datPhongDB = new DatPhongDB();
        DatPhong booking = new DatPhong();
        booking.setCustomerId(jobj.get("customerId").getAsInt());
        String StrcheckIn = jobj.get("checkIn").getAsString();
        Date checkIn = Date.valueOf(StrcheckIn);
        booking.setNgayDen(checkIn);


        String StrCheckOut = jobj.get("checkOut").getAsString();
        Date checkOut = Date.valueOf(StrCheckOut);

        booking.setNgayDi(checkOut);
        //query tu db cai booking vua insert de lay id

        PhongDuocDat bookedRoom = new PhongDuocDat();
        bookedRoom.setMaDatPhong(booking.getId());
        bookedRoom.setMaPhong(jobj.get("roomId").getAsInt());
        String status="200";
        String body ="Đặt phòng thành công!";

        if (datPhongDB.addBooking(booking,bookedRoom)==false){
            status="500";
            body="Thêm lịch đặt không thành công!";
        }
        PrintWriter out = resp.getWriter();
        JsonObject json=new JsonObject();
        json.addProperty("status",status);
        json.addProperty("message",body);
        out.write(gson.toJson(json));
        out.close();

    }
}
