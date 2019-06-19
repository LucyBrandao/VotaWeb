/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.JsonObject;
import helpers.BallotBoxDisplayHelper;
import helpers.VotingRequestHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Election;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class RequestResponse extends HttpServlet {

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
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jobj = new JsonObject();
        
        try (PrintWriter out = response.getWriter()) {
            boolean enabled = false;
            for (Voter v : VotingRequestHelper.enableds) {
                if (v.id.equals(voter.id))
                    enabled = true;
            }
            
            jobj.addProperty("enabled", enabled);
            out.print(jobj.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
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
