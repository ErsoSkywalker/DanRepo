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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author conti
 */
public class EliminarReservacion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        BD.cDatos bd = new BD.cDatos();
        HttpSession ses = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            try {
                bd.conectar();
                bd.consulta("call spEliminarReservaciones(" + request.getParameter("reservacion") + ");");
                ResultSet res3 = bd.consulta("call spDesplegarReservaciones(" + ses.getAttribute("idUsuario") + ")");
                while (res3.next()) {
                    out.print("                            <section>\n"
                            + "                                <header class=\"section-title\" role=\"button\" aria-controls=\"0\" aria-expanded=\"false\" tabindex=\"0\"> \n"
                            + "                                    <div class=\"header_title\"> \n"
                            + "                                        <h4 class=\"tab-title mbr-bold mbr-black mbr-fonts-style display-7\">" + res3.getString("NombreHotel") + "</h4> \n"
                            + "                                        <div class=\"iconfont-wrapper\">\n"
                            + "                                            <span class=\"mbr-iconfont mbr-bold mobi-mbri-arrow-down mobi-mbri\"><svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"currentColor\" width=\"100%\" height=\"100%\"><path d=\"M1.4 4.6L12 15.2 22.6 4.6 24 6 12 18 0 6z\"></path></svg></span>\n"
                            + "                                        </div> \n"
                            + "                                    </div> \n"
                            + "                                </header>\n"
                            + "                                <div class=\"content\">\n"
                            + "                                    <p class=\"mbr-text mbr-fonts-style display-4\">\n"
                            + "                                        Fecha de reservación:" + res3.getString("fecha") + "\n"
                            + "                                    </p>\n"
                            + "                                     <button class=\"btn btn-sm btn-black-outline display-4\" onClick=\"eliminarReservacion(" + res3.getString("idReservation") + ")\">Eliminar</button>"
                            + "                                </div>\n"
                            + "                            </section>\n");
                }
                bd.cierraConexion();
            } catch (SQLException ex) {
                Logger.getLogger(EliminarReservacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
