/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Lucy
 */
public class CreateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (Voter.session.getId().equals(request.getSession().getId()))
            System.out.println("Session corresponding.");
        request.setCharacterEncoding("UTF-8");
        
        
        HashMap<String, String> parameters = HttpParameterExtractor.get(request);
        
        String username = parameters.get("username");
        String password = parameters.get("password");
        String name = parameters.get("name");
        String cpf = parameters.get("cpf");
        System.out.printf("Recebi os parâmetros: {%s} {%s} {%s} {%s}.\n", username, password, name, cpf);
        
        Voter voter = new Voter(username, password, name, cpf);
        boolean saved = voter.save();
        PrintWriter out = response.getWriter();
        System.out.println("Ok");
        
        Gson gson = new Gson();
        JsonObject jobj = new JsonObject();
        JsonArray jarr = new JsonArray();
        
        
        try {
            if (saved) {
                //request.getServletContext().getRequestDispatcher("/dynamic/jsp/login.jsp").forward(request, response);
                //response.sendRedirect("/Vota/");
                jobj.addProperty("status", true);
                out.print("{\"status\": \"success\", \"message\": \"Created user " + voter.name + "\"}");
                out.flush();
            } else {
                jobj.addProperty("status", false);
                for (String error : voter.errors) {
                    JsonObject jerrobj = new JsonObject();
                    jerrobj.addProperty("field", error.split(" ")[0]);
                    jerrobj.addProperty("message", error);
                    
                    jarr.add(jerrobj);
                    //String key = error.split(" ")[0];
                    //jobj.addProperty(key, error);
                }
                jobj.add("errors", jarr);
                out.print(jobj.toString());//"{\"failed\": \"failed\", \"message\": \"User not saved.\"}");
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
