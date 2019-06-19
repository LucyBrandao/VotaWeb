/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.VotingRequestHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import libs.HttpParameterExtractor;
import models.Election;
import models.Report;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class Authenticate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (Voter.session.equals(request.getSession()))
            System.out.println("Session corresponding.");
        
        HttpSession session = request.getSession(true);
        
        if (session.isNew()) System.out.println("Nova sessão");
        else System.out.println("Sessão existente");
        
        HashMap<String, String> parameters = HttpParameterExtractor.get(request);
        
        String username = parameters.get("username"); // request.getParameter("username");
        String password = parameters.get("password");
        
        Voter voter = Voter.findByUsername(username);
        boolean authenticated = false;
        if (voter != null) authenticated = voter.password.equals(password);
        System.out.println("Cheguei aqui");
        try {
            if (!authenticated) {
                System.out.println("Usuário não encontrado.");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"status\": \"fail\", \"message\": \"User not found " + username + "\"}");
                out.flush();
            } else {
                
                ServletContext context = getServletContext();
                
                Election election = Election.class.cast(context.getAttribute("election"));
                
                if (election == null) {
                    
                    OffsetDateTime now = OffsetDateTime.now();
                    election = new Election();
                    election.created_at = now;
                    election.startsAt = now;
                    election.endsAt = now.plusMinutes(3);
                    election.save();


                    context.setAttribute("election", election);
                }
        
                Report report = new Report();
                
                report.election = election;
                report.message = String.format("Usuário %s; Autenticação no sistema", voter.username);
                report.save();
                
                voter.sessionId = session.getId();
                voter.save();
                session.setAttribute("voter", voter);
                //if (voter.)
                //boolean added = VotingRequestHelper.pendings.add(voter);
                //if (added) System.out.println("Adicionado " + VotingRequestHelper.pendings.size());
                System.out.println("Parece tudo OK.");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"status\": \"success\", \"url\": \"/Vota/home\"}");
                out.flush();
            }
        } catch (Exception e) {
            //System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
