/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Candidate;
import models.Election;
import models.Vote;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class ConfirmVote extends HttpServlet {

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
        
        ServletContext context = getServletContext();
        Election election = Election.class.cast(context.getAttribute("election"));
        Candidate candidate = Candidate.class.cast(session.getAttribute("candidate"));
        Vote vote = new Vote();
        
        vote.election = election;
        vote.voter = voter;
        vote.candidate = candidate;
        vote.created_at = OffsetDateTime.now();
        System.out.println("Trying to save vote...");
        boolean result = vote.save();
        renderResponse(response, result);
    }
    
    protected void renderResponse(HttpServletResponse response, boolean result) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jobj = new JsonObject();
        try (PrintWriter out = response.getWriter()) {
            jobj.addProperty("confirmed", result);
            out.print(jobj.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println("Error on responding Enable Voting request");
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
