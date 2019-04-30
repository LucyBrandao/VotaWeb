/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import libs.HttpParameterExtractor;
import models.Voter;

/**
 *
 * @author Fabian
 */
public class CreateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (Voter.session.equals(request.getSession()))
            System.out.println("Session corresponding.");
        request.setCharacterEncoding("UTF-8");
        
        
        HashMap<String, String> parameters = HttpParameterExtractor.get(request);
        
        String username = parameters.get("username");
        String password = parameters.get("password");
        String name = parameters.get("name");
        String cpf = parameters.get("cpf");
        System.out.printf("Recebi os parâmetros: {%s} {%s} {%s} {%s}.\n", username, password, name, cpf);
        
        Voter voter = new Voter(username, password, name, cpf);
        voter.save();
        Voter confirm = Voter.findByUsername(username);
        PrintWriter out = response.getWriter();
        System.out.println("Ok");
        try {
            if (confirm != null) {
                //request.getServletContext().getRequestDispatcher("/dynamic/jsp/login.jsp").forward(request, response);
                //response.sendRedirect("/Vota/");
                out.print("{\"status\": \"success\", \"message\": \"Created user " + confirm.getName() + "\"}");
                out.flush();
            } else {
                out.print("{\"failed\": \"failed\", \"message\": \"User not saved.\"}");
                out.flush();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            out.print("{\"status\": \"failed\", \"message\": \"Something went wrong\"}");
            out.flush();
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
