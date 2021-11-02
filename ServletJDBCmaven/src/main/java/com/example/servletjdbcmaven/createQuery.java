package com.example.servletjdbcmaven;

import DAO.*;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "createQuery", value = "/createQuery")
public class createQuery extends HttpServlet {
        private static String name;
        private static String surname;
        private static String register;

        public void init() {
            DAO.registerDriver();
        }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            processRequest(request, response);
        }

        public void destroy() {
        }

        private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String register =  request.getParameter("register");
            System.out.println(name + " " + surname + " " + register);
            int s = DAO.InsertTable(name,surname,register);
            if(s > 0){
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Stampa</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Name: " + name + " Surname " + surname + " Register " + register + "</h1>");
                    out.println("<h1>CARICATO</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }else{
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Stampa</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>ERRORE</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

        }
}
