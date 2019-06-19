/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libs.HttpParameterExtractor;
import models.Admin;

/**
 *
 * @author Lucy
 */
public class Authenticate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        /*if (session.isNew()) System.out.println("Nova sessão");
        else System.out.println("Sessão existente");*/
        
        JsonObject jobj = new JsonObject();
        JsonArray jarr = new JsonArray();
        //Gson gson = GsonBuilder()
        if (session.isNew()) {
            System.out.printf("Nova sessão iniciada ID: %s\n", session.getId());
            HashMap<String, String> parameters = HttpParameterExtractor.get(request);

            String username = parameters.get("username");
            String password = parameters.get("password");

            Admin admin = Admin.findByUsername(username);
            boolean authenticated = false;
            if (admin != null) authenticated = admin.password.equals(password);
            
            try {
                if (authenticated) {
                    //session.setAttribute("username", admin.username);
                    //admin.sessionId = session.getId();
                    //System.out.printf("Login de admin username %s\n", admin.username);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    jobj.addProperty("status", true);
                    jobj.addProperty("url", "/Vota/admin/home");
                    out.print(jobj.toString());
                    //out.print("{\"status\": \"success\", \"url\": \"/Vota/admin/home\"}");
                    out.flush();
                } else {
                    System.out.printf("Login falhou para username %s\n", username);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    jobj.addProperty("status", false);
                    /*for (String error : admin.errors)
                        jarr.add(error);*/
                    jobj.addProperty("errors", "");
                    out.print("{\"status\": \"success\", \"url\": \"/Vota/home\"}");
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
           try {
                System.out.printf("Login de admin username %s\n", session.getAttribute("username"));
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                jobj.addProperty("status", true);
                jobj.addProperty("url", "/Vota/admin/home");
                out.print(jobj.toString());
                //out.print("{\"status\": \"success\", \"url\": \"/Vota/admin/home\"}");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
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
