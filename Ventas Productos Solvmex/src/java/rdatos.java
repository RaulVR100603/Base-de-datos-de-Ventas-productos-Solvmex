/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Gay_Cha
 */
@WebServlet(urlPatterns = {"/rdatos"})
public class rdatos extends HttpServlet {

     Connection conexion;
     
     public void init ()
     {
         try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost/solvmex","root","");
        }
        catch(ClassNotFoundException man)
        {
            System.out.println("no se encontro un manejador");
        }
        catch(SQLException base)
        {
            System.out.println("No hay conexion");
        }
    
    }
    
    public String insertar(String agregarproducto)
    {
        String html="";
        try{
            
            Statement a=conexion.createStatement();
            
            int fila=a.executeUpdate(agregarproducto);
            return html+=fila+"producto registrado";
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
       
             String nombre=request.getParameter("nombre");
             String marca=request.getParameter("marca");
             String precio=request.getParameter("precio");
             
             
            String agregarproducto="insert into productos(nombre , marca , precio) values('"+nombre+"','"+marca+"',"+precio+")";
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rdatos</title>");            
            out.println("</head>");
            out.println("<body bgcolor='aqua' text='blue'>");
            
            out.println("<form action='menu'>");
            out.println("<input type='submit' value='regresar al menu principal'>");
            out.println(""+insertar(agregarproducto));
            
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
