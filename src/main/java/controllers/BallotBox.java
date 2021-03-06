/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.VotingRequestHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Election;
import models.Vote;
import models.Voter;
import models.VotingSession;

/**
 *
 * @author Lucy
 */
public class BallotBox extends HttpServlet {

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
        
        try{
            response.setContentType("text/html;charset=UTF-8");
            request.getServletContext().getRequestDispatcher("/dynamic/jsp/ballot_box.jsp").forward(request, response);
        } catch (Exception e){}
        
    }
    
    protected void onAccessPage(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        
        Election election = Election.class.cast(context.getAttribute("election"));
        Voter voter = Voter.class.cast(session.getAttribute("voter"));
        
        if (voter == null || election == null) {
            response.sendRedirect("login");
        }
        
        requestVotingSession(voter);
        //session.setAttribute("voter", voter);
    }
    
    protected void requestVotingSession(Voter voter) {
        /*VotingSession vs = new VotingSession();
        vs.election = election;
        vs.voter = voter;
        voter.votingSessions.add(vs);*/
        //vs.save();
        VotingRequestHelper.pendings.add(voter);
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
