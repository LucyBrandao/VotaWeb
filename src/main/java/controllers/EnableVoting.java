/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import helpers.VotingRequestHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.HttpParameterExtractor;
import models.Election;
import models.Report;
import models.Voter;
import models.VotingSession;

/**
 *
 * @author Lucy
 */
public class EnableVoting extends HttpServlet {

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
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        
        Election election = Election.class.cast(context.getAttribute("election"));
        Voter admin = Voter.class.cast(session.getAttribute("voter"));
        
        if (admin == null || election == null) {
            response.sendRedirect("login");
        }
        
        HashMap<String, String> parameters = HttpParameterExtractor.get(request);
        String voterId = parameters.get("voter_id");
        
        Voter voter = null;
        for (Voter v : VotingRequestHelper.pendings) {
            if (v.id.toString().equals(voterId))
                voter = v;
        }
        
        System.out.println("Respondendo...");
        
        if (voter != null) {
            boolean result = makeSession(admin, voter, election);
            renderResponse(response, result);
        } else {
            renderResponse(response, false);
        }
    }
    
    protected boolean makeSession(Voter admin, Voter voter, Election election) {
        VotingSession vs = new VotingSession();
        vs.status = "enabled";
        vs.enabledBy = admin;
        vs.voter = voter;
        vs.election = election;
        if (vs.save()){
            VotingRequestHelper.pendings.remove(voter);
            voter.votingSessions.add(vs);
            VotingRequestHelper.enableds.add(voter);
            Report report = new Report();
            report.election = election;
            report.message = String.format("Usuário %s; Habilitação de votação usuário %s", voter.username, admin.username);
            report.save();
            return true;
        }
        
        System.err.println("Error in save Voting Session");
        return false;
    }
    
    protected void renderResponse(HttpServletResponse response, boolean result) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jobj = new JsonObject();
        try (PrintWriter out = response.getWriter()) {
            jobj.addProperty("enabled", result);
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
