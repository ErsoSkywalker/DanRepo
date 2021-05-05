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

/**
 *
 * @author conti
 */
public class Buscador extends HttpServlet {

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
        Boolean bandera = false;
        try (PrintWriter out = response.getWriter()) {
            try {
                bd.conectar();
                
                ResultSet res = request.getParameter("data").equalsIgnoreCase("GetHotels") ? bd.consulta("call spDesplegarhoteles();") : bd.consulta("call spBuscarHoteles('"+request.getParameter("query")+"');");
                while (res.next()) {
                    bandera = true;
                    out.println("<div class=\"card mbr-col-sm-12 mbr-col-md-6 mbr-col-lg-4 md-pb\">\n"
                            + "                <div class=\"card-wrapper\">\n"
                            + "                    <div class=\"card-img\">\n"
                            + "                        <amp-img src=\"assets/images/mbr-696x522.jpg\" layout=\"responsive\" width=\"348\" height=\"261\" alt=\"Mobirise\" class=\"mobirise-loader\">\n"
                            + "                            <div placeholder=\"\" class=\"placeholder\">\n"
                            + "                                <div class=\"mobirise-spinner\">\n"
                            + "                                    <em></em>\n"
                            + "                                    <em></em>\n"
                            + "                                    <em></em>\n"
                            + "                                </div></div>\n"
                            + "                            \n"
                            + "                        </amp-img>\n"
                            + "                    </div>\n"
                            + "                    <div class=\"card-box mbr-p-5\">\n"
                            + "                        <h4 class=\"mbr-section-title mbr-fonts-style align-left mbr-bold mbr-pb-1 display-7\">\n"
                            + res.getString("NombreHotel") + "</h4>\n"
                            + "                        <p class=\"mbr-text mbr-fonts-style align-left mbr-pb-1 display-4\">\n"
                            + res.getString("Descripcion") + "</p>\n"
                            + "                        <div class=\"mbr-section-btn align-left\"><a class=\"btn btn-sm btn-black-outline display-4\" href=\"InfoHotel.html?idHotel="+res.getString("idHotel")+"\">Ver</a></div>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>");

                }

                bd.cierraConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(!bandera){
                out.print("No se han encontrado resultados):");
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
