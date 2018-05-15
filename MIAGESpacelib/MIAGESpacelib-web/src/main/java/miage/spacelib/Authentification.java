/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miage.spacelib;

import java.io.IOException;
import java.io.PrintWriter;
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
    
    private WSMeca wsmeca;
    private WSUsager wsusager;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Spacelib Authentification</title>");
            out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>");
            out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" integrity=\"sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB\" crossorigin=\"anonymous\">");
            out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\" integrity=\"sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T\" crossorigin=\"anonymous\"></script>");         
            out.println("</head>");
            out.println("<body>");
            out.println("   <form class=\"form-signin\" style=\"width: 100%;max-width: 420px;padding: 15px;margin: auto;\" method=\"POST\">");
            out.println("       <div class=\"text-center mb-4\" style=\"margin-bottom: 1rem;\">");
            out.println("           <h1 class=\"h3 mb-3 font-weight-normal\">Spacelib</h1>");
            out.println("           <p>Veuillez vous identifier au service.</p>");
            out.println("       </div>");
            out.println("       <div class=\"form-label-group\" style=\"margin-bottom: 1rem;\">");
            out.println("           <input type=\"text\" id=\"inputNomPrenom\" name=\"login\" class=\"form-control\" placeholder=\"nom.prenom\" required autofocus>");
            out.println("       </div>");
            out.println("       <div class=\"form-label-group\" style=\"margin-bottom: 1rem;\">");
            out.println("           <input type=\"password\" id=\"inputPassword\" name=\"pass\" class=\"form-control\" placeholder=\"mot de passe\" required>");
            out.println("       </div>");
            out.println("       <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Connexion</button>");
            out.println("   </form>");
            out.println("</body>");
            out.println("</html>");
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
        this.wsmeca = new WSMeca();
        this.wsusager = new WSUsager();
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        long nb = wsmeca.authentifierMeca(login, pass);
        System.out.println(nb+"");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet d'authentification";
    }// </editor-fold>

}
