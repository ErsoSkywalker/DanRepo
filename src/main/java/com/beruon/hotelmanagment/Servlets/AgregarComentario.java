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
import sun.jvm.hotspot.utilities.soql.SOQLException;

/**
 *
 * @author conti
 */
public class AgregarComentario extends HttpServlet {

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
                bd.consulta("call spAgregarComentario(" + request.getParameter("idHotel") + ", " + ses.getAttribute("idUsuario") + ", '" + request.getParameter("Comentario") + "','" + request.getParameter("img") + "');");
                ResultSet res2 = bd.consulta("call spDespliegaComentsPorHotel(" + request.getParameter("idHotel") + ");");

                while (res2.next()) {
                    out.print("\n"
                            + "                    <div class=\"card mbr-col-sm-12 mbr-col-md-12 mbr-col-lg-6 last-child\">\n"
                            + "                        <div class=\"card-wrapper mbr-p-5 mbr-flex\">\n"
                            + "                            \n");
                    if (!res2.getString("img").equals("")) {
                        out.print("                                "
                                + "<div class=\"card-img mbr-col-md-6 mbr-m-auto\"><amp-img src=\"" + res2.getString("img") + "\" layout=\"responsive\" height=\"319.25\" width=\"319.25\" alt=\"Image\" class=\"mobirise-loader\">\n"
                                + "                                    <div placeholder=\"\" class=\"placeholder\">\n"
                                + "                                        <div class=\"mobirise-spinner\">\n"
                                + "                                            <em></em>\n"
                                + "                                            <em></em>\n"
                                + "                                            <em></em>\n"
                                + "                                        </div></div>\n"
                                + "\n"
                                + "                                </amp-img></div>\n");
                    }

                    out.print("                            \n"
                            + "                            <div class=\"card-box mbr-col-sm-12 mbr-col-md-6 mbr-m-auto mbr-p-2\">\n"
                            + "\n"
                            + "                                <h3 class=\"mbr-section-title mbr-bold mbr-fonts-style align-center mbr-white mbr-pt-1 display-5 mbr-pb-2\">" + res2.getString("Nombre") + "</h3>\n"
                            + "                                <p class=\"mbr-text mbr-fonts-style align-center mbr-white mbr-pt-1 display-4\">\n"
                            + "                                    " + res2.getString("Comentario") + "\n"
                            + "                                </p>\n"
                            + "\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                    </div>\n");
                }
                bd.cierraConexion();
            } catch (SQLException ex) {
                Logger.getLogger(AgregarComentario.class.getName()).log(Level.SEVERE, null, ex);
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
