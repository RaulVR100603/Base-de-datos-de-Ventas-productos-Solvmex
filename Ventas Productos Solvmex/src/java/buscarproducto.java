/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author HP
 */
@WebServlet(name = "buscarproducto", urlPatterns = {"/buscarproducto"})
public class buscarproducto extends HttpServlet {

 Connection conexion; 
 
 public void init()
 {
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection("jdbc:mysql://localhost/solvmex","root","");
     }
     catch (ClassNotFoundException man)
     {
         System.out.println("no hay manejador");
     }
     
     catch(SQLException base)
     {
         System.out.println("no hay conexion");
     }
 }
 
 public String buscarregistro(String buscarproducto)
 {
     String html="";
     try
     {
     Statement sen = conexion.createStatement();
     ResultSet res=sen.executeQuery(buscarproducto);
     while(res.next())
     {
         
    html +="<input name='clave' value='"+res.getInt(1)+"'><br>";
    html +="<input name='nombre' value='"+res.getString(2)+"'><br>";
    html +="<input name='marca' value='"+res.getString(3)+"'><br>";
    html +="<input name='precio' value='"+res.getString(4)+"'><br>";
    html +="</form>";
         
     }
     
     res.close();
     sen.close();
     return html;
     }
     catch(SQLException base)
     {
         return base.getMessage();
     
     }
 }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            String clave = request.getParameter("clave");
            String buscarproducto="select * from productos where clave ="+clave;
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet buscarproducto</title>");            
            out.println("</head>");
            out.println("<body bgcolor='aqua' text='blue'>");
            
            out.println("<h4><form method='post' action='menu',>");
            out.println("<td><input type='submit' value='REGRESAR A MENU PRINCIPAL'></td>");
            out.println("<h2>"+buscarregistro(buscarproducto)+"</h2><br>");
            out.println("</form></h4>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
