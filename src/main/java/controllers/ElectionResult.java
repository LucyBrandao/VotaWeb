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
import models.Candidate;
import models.Election;
import models.Report;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class ElectionResult extends HttpServlet {

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
        List<Candidate> candidates = Candidate.selectByElection(election);

        if (candidates != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>eleitores</title>");
                out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"static/css/table.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class=\"topnav\" id=\"myTopnav\">");
                out.println("<a href=\"#home.html\" style=\"letter-spacing: 2px; font-size: 1.3rem;\">Vota Web</a>");
                out.println("</div>");
                out.println("<table>");
                out.println("");
                out.println("");
                out.println("");
                out.println("<tr>");
                out.println("<th>Candidato</th>");
                out.println("<th>Cargo</th>");
                out.println("<th>Número</th>");
                out.println("<th>Partido</th>");
                out.println("<th>Votos</th>");
                out.println("<tr>");
                for (Candidate candidate : candidates) {
                    out.println("<tr>");
                    out.printf("<td>%s</td>", candidate.name);
                    out.printf("<td>%s</td>", candidate.post.name);
                    out.printf("<td>%s</td>", candidate.number);
                    out.printf("<td>%s</td>", candidate.party);
                    out.printf("<td>%d</td>", candidate.votes.size());
                    out.println("<tr>");
                }
                out.println("</table>\n" +
                            "</body>\n" +
                            "</html>");
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
