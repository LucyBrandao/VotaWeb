/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Election;
import models.Report;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class Reporter extends HttpServlet {

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
        
        
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        
        Election election = Election.class.cast(context.getAttribute("election"));
        Voter voter = Voter.class.cast(session.getAttribute("voter"));
        
        if (voter == null) {
            response.sendRedirect("login");
        } else if (voter.role.level < 3) {
            response.sendRedirect("home");
        }
        
        makeTable(response, election);
    }
    
    protected void makeTable(HttpServletResponse response, Election election)
            throws ServletException, IOException {
        List<Report> reports = Report.selectByElection(election);
        
        if (reports != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<tr>");
                out.println("<th>Data</th>");
                out.println("<th>Hora</th>");
                out.println("<th>Mensagem</th>");
                out.println("<tr>");
                for (Report report : reports) {
                    out.println("<tr>");
                    out.printf("<td>%s</td>", report.created_at.toLocalDate().toString());
                    out.printf("<td>%s</td>", report.created_at.toLocalTime().toString());
                    out.printf("<td>%s</td>", report.message);
                    out.println("<tr>");
                }
                out.close();
            }
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
