package DAO;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

public class DAO {
    //NB: in questo esempio lasciamo URL, account e password per accedere al DB
    //nel codice. Meglio sarebbe caricare i dati da web.xml nella init() della Servlet
    private static final String url1 = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static int InsertTable(String name,String surname,String register){
        Connection conn1 = null;
        int i = 0;
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
            Statement st = conn1.createStatement();
            PreparedStatement ps=conn1.prepareStatement("insert into studente values(?,?,?)");
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setString(3,register);
            i = ps.executeUpdate();
            if(i>0) {
                System.out.println("Success");
            }else{
                System.out.println("FAIL");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return i;
    }


    public static ArrayList<Persona> queryDB() {
        Connection conn1 = null;
        ArrayList<Persona> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STUDENTE");
            while (rs.next()) {
                Persona p = new Persona(rs.getString("NOME"),rs.getString("COGNOME"), rs.getString("MATRICOLA"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }
}
