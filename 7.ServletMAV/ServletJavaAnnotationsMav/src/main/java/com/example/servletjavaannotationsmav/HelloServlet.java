package com.example.servletjavaannotationsmav;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet",
        initParams = {
        @WebInitParam(name = "param1", value = "val1"),
        @WebInitParam(name = "param2", value = "val2")})
public class HelloServlet extends HttpServlet {
    private String par1 = "";
    private String par2 = "";

    public void init(ServletConfig conf) {
        par1 = conf.getInitParameter("param1");
        par2 = conf.getInitParameter("param2");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + par1 + "</h1>");
        out.println("<h1>" + par2 + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}