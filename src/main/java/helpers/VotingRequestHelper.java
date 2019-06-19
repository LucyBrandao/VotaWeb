/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class VotingRequestHelper {
    public static ArrayList<Voter> pendings = new ArrayList<>();
    public static ArrayList<Voter> enableds = new ArrayList<>();
    //public static boolean hasNew = false;
    
//    public static void addResquestes(Voter voter)
    
    public static void makeTable(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<tr>");
            out.println("<th>Nome</th>");
            out.println("<th>Título de Eleitor</th>");
            out.println("<th>Habilitar</th>");
            out.println("</tr>");
            for (Voter voter : pendings) {
                out.println("<tr>");
                out.printf("<td>%s</td>\n", voter.name);
                out.printf("<td>%s</td>\n", voter.username);
                out.printf("<td id='%d' class='habilitate' onclick='habilitate(this)'>", voter.id);
                out.println("<i class='fa fa-plus' aria-hidden='true'></i>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.close();
        }
    }
}
