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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author conti
 */
public class InfoProfile extends HttpServlet {

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
                ResultSet res = bd.consulta("call spGetProfileInfo(" + ses.getAttribute("idUsuario") + ");");
                while (res.next()) {
                    out.print(" <section class=\"mbr-section 01 cid-sw2pD8qKTf\" id=\"content1-n\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n"
                            + "                    <div class=\"mbr-col-sm-12 mbr-col-md-10\">\n"
                            + "                        <h2 class=\"mbr-section-title align-center mbr-fonts-style mbr-bold display-1\">\n"
                            + "                            " + res.getString("Nombre") + "</h2>\n"
                            + "\n"
                            + "\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"features2 mbr-section cid-sw2pKSClXw\" id=\"features2-o\">\n"
                            + "\n"
                            + "\n"
                            + "\n");
                                    if(((String)ses.getAttribute("Rol")).equalsIgnoreCase("Administrador"))
                                        out.print("<center><a class=\"btn btn-sm btn-black-outline display-4\" href=\"createHotel.html\">Crear Hotel</a></center>");
                                    
                            out.print("            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n");
                    ResultSet res2 = bd.consulta("call spDesplegarFavoritosUser(" + ses.getAttribute("idUsuario") + ");");
                    while (res2.next()) {
                        out.print("                    <div class=\"card mbr-col-sm-12 mbr-col-md-6 mbr-col-lg-4 md-pb\">\n"
                                + "                        <div class=\"card-wrapper\">\n"
                                + "                            <div class=\"card-img\">\n"
                                + "                                <amp-img src=\"assets/images/mbr-696x464.jpg\" layout=\"responsive\" width=\"348\" height=\"232\" alt=\"Mobirise\" class=\"mobirise-loader\">\n"
                                + "                                    <div placeholder=\"\" class=\"placeholder\">\n"
                                + "                                        <div class=\"mobirise-spinner\">\n"
                                + "                                            <em></em>\n"
                                + "                                            <em></em>\n"
                                + "                                            <em></em>\n"
                                + "                                        </div></div>\n"
                                + "\n"
                                + "                                </amp-img>\n"
                                + "                            </div>\n"
                                + "                            <div class=\"card-box mbr-p-5\">\n"
                                + "                                <h4 class=\"mbr-section-title mbr-fonts-style align-left mbr-bold mbr-pb-1 display-7\">" + res2.getString("NombreHotel") + "</h4>\n"
                                + "                                <p class=\"mbr-text mbr-fonts-style align-left mbr-pb-1 display-4\">\n"
                                + "                                    " + res2.getString("Descripcion") + "</p>\n"
                                + "                                <div class=\"mbr-section-btn align-left\"><a class=\"btn btn-sm btn-black-outline display-4\" href=\"InfoHotel.html?idHotel=" + res2.getString("idHotel") + "\">Ver</a></div>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                    </div>\n");
                    }

                    out.print("\n"
                            + "\n"
                            + "\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"toggle cid-sw2q9EG7at\" id=\"toggle1-q\">\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n"
                            + "                    <div class=\"mbr-col-sm-12 mbr-col-md-12 mbr-col-lg-12\">\n"
                            + "                        <h3 class=\"mbr-fonts-style mbr-section-title mbr-bold align-center display-2\">Reservaciones</h3>\n"
                            + "\n"
                            + "                    </div>\n"
                            + "                    <div class=\"mbr-col-sm-12 mbr-col-md-12 mbr-col-lg-8\" id=\"ReservacionesDiv\">\n"
                            + "                        <amp-accordion class=\"accordion mbr-pt-4\" disable-session-states=\"\">\n");

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

                    out.print("                            \n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "                        </amp-accordion>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>");
                }
                bd.cierraConexion();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
