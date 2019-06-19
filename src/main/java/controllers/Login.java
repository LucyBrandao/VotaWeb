/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class Login extends HttpServlet {

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
        if (Voter.session == null)
            Voter.session = request.getSession();
        
        HttpSession session = request.getSession();
        
        Voter voter = Voter.class.cast(session.getAttribute("voter"));
        response.setContentType("text/html;charset=UTF-8");
        if (voter != null && session.getId().equals(voter.sessionId)) {
            try {
                response.sendRedirect("home");
                //request.getServletContext().getRequestDispatcher("/dynamic/jsp/home.jsp").include(request, response);
            } catch (Exception e) {
            }
        } else {
            session.setAttribute("voter", null);
            //response.setContentType("text/html;charset=UTF-8");
            try {
                request.getServletContext().getRequestDispatcher("/dynamic/jsp/login.jsp").include(request, response);
                //sc.getRequestDispatcher("/dynamic/jsp/login.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }
        //request.setAttribute("fail", "true");
        //ServletContext sc = request.getServletContext();
        
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
