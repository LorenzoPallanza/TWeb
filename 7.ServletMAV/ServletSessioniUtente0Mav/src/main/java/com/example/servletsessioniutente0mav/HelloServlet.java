package com.example.servletsessioniutente0mav;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("login");
        HttpSession s = request.getSession();
        if (account != null)
            s.setAttribute("account", account);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestisciSessione</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Ciao ");
            out.println(s.getAttribute("account") + "</p>");
            out.println("<p>Ricarica la pagina: ");
            out.println("<form action=\"hello-servlet\" method=\"post\">"
                    + "   <input type=\"submit\" name=\"submit\" value=\"OK\"/>"
                    + "</form> </p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}