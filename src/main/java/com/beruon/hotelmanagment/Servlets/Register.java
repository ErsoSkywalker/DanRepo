/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beruon.hotelmanagment.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.jvm.hotspot.utilities.soql.SOQLException;

/**
 *
 * @author conti
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        response.setContentType( "text/plain; charset=iso-8859-1" );
        
        BD.cDatos bd = new BD.cDatos();
        String respuesta = "0";
        try{
            bd.conectar();
            ResultSet res = bd.consulta("call spAgregarUser('"+request.getParameter("user")+"','"+request.getParameter("pass")+"','"+request.getParameter("name")+"','"+request.getParameter("lastname")+"');");
            while(res.next()){
                respuesta = res.getString("Mensaje");
            }
            bd.cierraConexion();
            
            if(Integer.parseInt(respuesta) > 0){
                HttpSession ses = request.getSession();
                ses.setAttribute("idUsuario", respuesta);
                ses.setAttribute("Rol", "Turista");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
         ServletOutputStream sout = response.getOutputStream();

        sout.print(respuesta);
        
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
