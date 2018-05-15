/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib.miagespacelibmecaweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author clem
 */
@WebServlet(name = "Authentification", urlPatterns = {"/authentification"})
public class Authentification extends HttpServlet {

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
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String station = request.getParameter("station");
        miage.spacelib.miagespacelibmeca.WSMeca_Service service = new miage.spacelib.miagespacelibmeca.WSMeca_Service();
        miage.spacelib.miagespacelibmeca.WSMeca port = service.getWSMecaPort();
        
        long nb = port.authentifierMeca(login, pass);
        
        if (nb == 0) {
            request.setAttribute("error_login", "Informations invalides, veuillez réessayer.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("authentification.jsp");
            dispatcher.forward(request, response);
        } else {
            List<String> revisions = new ArrayList<>();
            revisions = port.afficherRevision(station, nb);
            String [] rev = new String [revisions.size()];
            for (int i=0;i<revisions.size();i++) {
                rev[i] = revisions.get(i);
            }
            
            request.setAttribute("stations_revision", rev);
            RequestDispatcher dispatcher = request.getRequestDispatcher("revisions.jsp");
            dispatcher.forward(request, response);
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
