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

/**
 *
 * @author conti
 */
public class InfoHotel extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            try {
                bd.conectar();

                ResultSet res = bd.consulta("call spDesplegarInfohoteles(" + request.getParameter("idHotel") + ");");
                while (res.next()) {
                    out.print("<section class=\"mbr-section 02 cid-sw2ojNQ1OQ\" id=\"content18-i\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n"
                            + "                    <div class=\"mbr-col-sm-12 mbr-col-md-10\">\n"
                            + "                        <h2 class=\"mbr-section-title mbr-white align-center mbr-fonts-style mbr-bold display-1\">\n"
                            + "                            " + res.getString("NombreHotel") + "</h2>\n"
                            + "                        <h3 class=\"mbr-section-subtitle align-center mbr-regular mbr-white mbr-fonts-style mbr-pt-2 display-5\">\n"
                            + "                            " + res.getString("Descripcion") + "</h3>\n"
                            + "\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"features3 mbr-section cid-sw2zgWW9pA\" id=\"features3-y\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n"
                            + "                    <div class=\"card mbr-col-sm-12 mbr-col-md-6 mbr-col-lg-4\">\n"
                            + "                        <div class=\"card-img align-center mbr-pb-2\">\n"
                            + "                            <div class=\"iconfont-wrapper\">\n"
                            + "                                <span class=\"mbr-iconfont fa-dollar fa\"><svg width=\"100%\" height=\"100%\" viewBox=\"0 0 1792 1792\" xmlns=\"http://www.w3.org/2000/svg\" fill=\"currentColor\"><path d=\"M1362 1185q0 153-99.5 263.5t-258.5 136.5v175q0 14-9 23t-23 9h-135q-13 0-22.5-9.5t-9.5-22.5v-175q-66-9-127.5-31t-101.5-44.5-74-48-46.5-37.5-17.5-18q-17-21-2-41l103-135q7-10 23-12 15-2 24 9l2 2q113 99 243 125 37 8 74 8 81 0 142.5-43t61.5-122q0-28-15-53t-33.5-42-58.5-37.5-66-32-80-32.5q-39-16-61.5-25t-61.5-26.5-62.5-31-56.5-35.5-53.5-42.5-43.5-49-35.5-58-21-66.5-8.5-78q0-138 98-242t255-134v-180q0-13 9.5-22.5t22.5-9.5h135q14 0 23 9t9 23v176q57 6 110.5 23t87 33.5 63.5 37.5 39 29 15 14q17 18 5 38l-81 146q-8 15-23 16-14 3-27-7-3-3-14.5-12t-39-26.5-58.5-32-74.5-26-85.5-11.5q-95 0-155 43t-60 111q0 26 8.5 48t29.5 41.5 39.5 33 56 31 60.5 27 70 27.5q53 20 81 31.5t76 35 75.5 42.5 62 50 53 63.5 31.5 76.5 13 94z\"></path></svg></span>\n"
                            + "                            </div>\n"
                            + "                        </div>\n"
                            + "                        <h3 class=\"mbr-section-title mbr-white mbr-fonts-style align-center mbr-bold mbr-py-1 display-7\">\n"
                            + "                            Precio</h3>\n"
                            + "                        <p class=\"mbr-text mbr-white mbr-fonts-style align-center mbr-py-1 display-4\">\n"
                            + "                            " + res.getString("Precio") + "\n"
                            + "                        </p>\n"
                            + "\n"
                            + "                    </div>\n"
                            + "\n"
                            + "                    <div class=\"card mbr-col-sm-12 mbr-col-md-6 mbr-col-lg-4 last-child\">\n"
                            + "                        <div class=\"card-img align-center mbr-pb-2\">\n"
                            + "                            <div class=\"iconfont-wrapper\">\n"
                            + "                                <span class=\"mbr-iconfont fa-home fa\"><svg width=\"100%\" height=\"100%\" viewBox=\"0 0 1792 1792\" xmlns=\"http://www.w3.org/2000/svg\" fill=\"currentColor\"><path d=\"M1472 992v480q0 26-19 45t-45 19h-384v-384h-256v384h-384q-26 0-45-19t-19-45v-480q0-1 .5-3t.5-3l575-474 575 474q1 2 1 6zm223-69l-62 74q-8 9-21 11h-3q-13 0-21-7l-692-577-692 577q-12 8-24 7-13-2-21-11l-62-74q-8-10-7-23.5t11-21.5l719-599q32-26 76-26t76 26l244 204v-195q0-14 9-23t23-9h192q14 0 23 9t9 23v408l219 182q10 8 11 21.5t-7 23.5z\"></path></svg></span>\n"
                            + "                            </div>\n"
                            + "                        </div>        \n"
                            + "                        <h3 class=\"mbr-section-title mbr-white mbr-fonts-style align-center mbr-bold mbr-py-1 display-7\">\n"
                            + "                            Número de cuartos</h3>\n"
                            + "                        <p class=\"mbr-text mbr-white mbr-fonts-style align-center mbr-py-1 display-4\">\n"
                            + "                            " + res.getString("NumeroCuartos") + "\n"
                            + "                        </p>\n"
                            + "\n"
                            + "                    </div>\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"mbr-section 04 cid-sw2ot6ThSy\" id=\"content5-j\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\">\n"
                            + "                    <div class=\"mbr-section-btn mbr-col-md-12 mbr-col-sm-12 mbr-col-lg-8 align-center\">\n"
                            + "                        <button class=\"btn btn-secondary display-4\" onclick=\"AgregarFavs()\">Agregar a Favoritos</button></div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"map cid-sw2q0ENX7D\" id=\"map1-p\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div>\n"
                            + "\n"
                            + "            </div>\n"
                            + "            <div class=\"google-map\"><amp-iframe src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyDk89J4FSunMF33ruMVWJaJht_Ro0kvoXs&amp;q=350 5th Ave, New York, NY 10118\" height=\"400\" layout=\"fill\" sandbox=\"allow-scripts allow-same-origin                                 allow-popups\" frameborder=\"0\" class=\"mobirise-loader\">\n"
                            + "                    <div placeholder=\"\" class=\"placeholder\">\n"
                            + "                        <div class=\"mobirise-spinner\">\n"
                            + "                            <em></em>\n"
                            + "                            <em></em>\n"
                            + "                            <em></em>\n"
                            + "                        </div></div>\n"
                            + "                </amp-iframe></div>\n"
                            + "\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"team2 mbr-section cid-sw2o2soPiu\" id=\"team2-h\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container-fluid\">\n"
                            + "                <div class=\"mbr-row mbr-jc-c\" id=\"ComentariosContent\">\n");
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
                    out.print("\n"
                            + "\n"
                            + "\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"contacts2 cid-sw2owrOxeF\" id=\"contacts2-k\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <h2 class=\"mbr-section-title align-center mbr-fonts-style mbr-white mbr-bold display-2\">Agregar comentario</h2>\n"
                            + "\n"
                            + "\n"
                            + "                <div class=\"mbr-row mbr-jc-c mbr-pt-4\">\n"
                            + "                    <div class=\"mbr-col-lg-7 mbr-col-md-10 mbr-col-sm-12\" data-form-type=\"formoid\">\n"
                            + "                        <form class=\"mbr-form\" >\n"
                            + "                            <div class=\"mbr-form-row\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "                                <div class=\"area field mbr-col-sm-12 mbr-col-md-12 mbr-col-lg-12\" >\n"
                            + "                                     <input type=\"file\" id=\"imgInput\" name=\"img\" class=\"field-input display-4\">"
                            + "                                    <textarea class=\"field-input display-4\" name=\"Comentario\" rows=\"7\" data-form-field=\"Comentario\" placeholder=\"Comentario\" id=\"ComentarioInput\"></textarea>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"mbr-section-btn mbr-col-sm-12 mbr-col-md-12 mbr-col-lg-12 align-center\"><button type=\"button\" class=\"btn btn-primary btn-form display-4\" onClick=\"AgregarComentario();\">Enviar</button></div>\n"
                            + "                            </div>\n"
                            + "                        </form>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "            </div>\n"
                            + "        </section>\n"
                            + "\n"
                            + "        <section class=\"contact1 cid-sw2qBs5LBq\" id=\"contacts1-u\">\n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "            <div class=\"container\">\n"
                            + "                <h2 class=\"mbr-section-title align-center mbr-fonts-style mbr-white mbr-bold display-2\">Reservación</h2>\n"
                            + "\n"
                            + "\n"
                            + "                <div class=\"mbr-row mbr-jc-c mbr-pt-4\">\n"
                            + "                    <div class=\"mbr-col-lg-10 mbr-col-md-12 mbr-col-sm-12\" data-form-type=\"formoid\">\n"
                            + "                        <!--Formbuilder Form-->\n"
                            + "                        <form method=\"post\" class=\"mbr-form\" >\n"
                            + "                            <div class=\"dragArea mbr-form-row\">\n"
                            + "                                <div class=\"mbr-col-lg-9 mbr-col-md-12 mbr-col-sm-12 field \">\n"
                            + "                                    <input type=\"date\" name=\"fecha\" placeholder=\"Fecha\" data-form-field=\"Name\" class=\"field-input display-4\" id=\"datePicker\">\n"
                            + "                                </div>\n"
                            + "\n"
                            + "\n"
                            + "                                <div class=\"mbr-col-lg-3 mbr-col-md-12 mbr-col-sm-12 mbr-section-btn align-center field\"><button type=\"button\" onClick=\"agregarReservacion();\" class=\"btn btn-primary btn-form display-4\">Reservar</button></div>\n"
                            + "                            </div>\n"
                            + "                        </form><!--Formbuilder Form-->\n"
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
