package controller.filters;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter{

    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) req).getSession(false);
        if(session == null || !session.getAttribute("role").toString().equalsIgnoreCase("admin")) {
            ((HttpServletResponse) resp).sendRedirect("page/dang-nhap/");
            return;
        }


        chain.doFilter(req, resp);//sends request to next resource

    }
    public void destroy() {}



}
