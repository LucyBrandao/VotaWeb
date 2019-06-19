/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.BallotBoxDisplayHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.HttpParameterExtractor;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class BallotBoxDisplay extends HttpServlet {

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
        
        onAccessPage(request, response);
        
    }
    
    protected void onAccessPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Voter voter = Voter.class.cast(session.getAttribute("voter"));
        
        if (voter == null) {
            response.sendRedirect("login");
        }
        
        HashMap<String, String> parameters = HttpParameterExtractor.get(request);
        
        String step = parameters.get("step");

        if (step.equals("1"))
            BallotBoxDisplayHelper.renderEstadualDeputyDisplay(response);
        else if (step.equals("2"))
            BallotBoxDisplayHelper.renderFederalDeputyDisplay(response);
        else if (step.equals("3"))
            BallotBoxDisplayHelper.renderSenatorDisplay(response);
        /*else if (step.equals("4"))
            BallotBoxDisplayHelper.renderGovernorDisplay(response);*/
        else if (step.equals("4"))
            BallotBoxDisplayHelper.renderPresidentDisplay(response);
        else if (step.equals("5"))
            BallotBoxDisplayHelper.renderFinishDisplay(response);
        else
            BallotBoxDisplayHelper.renderPendingDisplay(response);
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
