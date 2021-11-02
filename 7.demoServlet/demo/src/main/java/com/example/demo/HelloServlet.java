package com.example.demo;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import DAO.*;

@WebServlet(name = "informazioni", value = "/informazioni")
public class HelloServlet extends HttpServlet {
    private Model model = null;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext ctx = conf.getServletContext();
        String url = ctx.getInitParameter("DB-URL");
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("pwd");
        // Notare la stampa nel log del Server
        System.out.println("URL del database: " + url);
        model = new Model(url, user, pwd);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tipoInfo = request.getParameter("tipoInfo");
        String loginName = request.getParameter("login");
        Operazione op = new Operazione();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Informazioni</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Ciao " + loginName + "</h2>");
            out.println("Hai chiesto la seguente operazione: "
                    + op.getOperazione(tipoInfo) + "<br>");
            out.println("Codice interno: " + tipoInfo + "<br>");
            if (tipoInfo!=null && tipoInfo.equals("persone")) {
                List<Persona> personas = model.queryDB();
                for (int i = 0; i < personas.size(); i++) {
                    out.println("<p>" + personas.get(i) + "</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}