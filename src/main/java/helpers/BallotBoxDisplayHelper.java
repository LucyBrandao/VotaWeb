/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import static helpers.VotingRequestHelper.pendings;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import models.Candidate;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class BallotBoxDisplayHelper {
    
    
    public static void renderFederalDeputyDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='visor'>");
            out.println("<div class='cx simple'>");
            out.println("<div class='dep-fed'>");
            out.println("<div class='dep-fed-title'><b>Deputado Federal</b></div>");
            out.println("<br>");
            out.println("<div class='dep-fed-nums'>");
            for (int i = 0; i < 4; ++i)
                out.println("<div class='num empty'></div>");
            for (int i = 0; i < 4; ++i)
                out.println("</div>");
            out.close();
        }
    }
    
    public static void renderEstadualDeputyDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                out.println("<div class='visor'>");
                out.println("<div class='cx simple'>");
                out.println("<div class='dep-est'>");
                out.println("<div class='dep-est-title'><b>Deputado Estadual</b></div>");
                out.println("<br>");
                out.println("<div class='dep-est-nums'>");
                for (int i = 0; i < 5; ++i)
                    out.println("<div class='num empty'></div>");
                for (int i = 0; i < 4; ++i)
                    out.println("</div>");
                
                out.close();
        }
    }
    
    public static void renderSenatorDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                out.println("<div class='visor'>");
                out.println("<div class='cx simple'>");
                out.println("<div class='sen-1'>");
                out.println("<div class='sen-1-title'><b>Senador</b></div>");
                out.println("<br>");
                out.println("<div class='sen-1-nums'>");
                for (int i = 0; i < 3; ++i)
                    out.println("<div class='num empty'></div>");
                for (int i = 0; i < 4; ++i)
                    out.println("</div>");
                out.close();
        }
    }
    
    public static void renderGovernorDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='visor'>");
            out.println("<div class='cx simple'>");
            out.println("<div class='gov'>");
            out.println("<div class='gov-title'><b>Governador</b></div>");
            out.println("<br>");
            out.println("<div class='gov-nums'>");
            for (int i = 0; i < 2; ++i)
                out.println("<div class='num empty'></div>");
            for (int i = 0; i < 4; ++i)
                out.println("</div>");
            out.close();
        }
    }
    
    public static void renderPresidentDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='visor'>");
            out.println("<div class='cx simple'>");
            out.println("<div class='pres'>");
            out.println("<div class='pres-title'><b>Presidente</b></div>");
            out.println("<br>");
            out.println("<div class='pres-nums'>");
            for (int i = 0; i < 2; ++i)
                out.println("<div class='num empty'></div>");
            for (int i = 0; i < 4; ++i)
                out.println("</div>");
            out.close();
        }
    }
    
    public static void renderWaitingDisplay(HttpServletResponse response) 
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
    
    public static void renderInfoDisplay(HttpServletResponse response, Candidate candidate) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='visor'>");
            out.println("<div class='info'>");
            out.println("<div class='photo'><img src='static/img/perfil.jpg' alt='perfil'></div>");
            out.println("<div class='info-text'>");
            out.printf("<div class='name-poli'><b>%s</b></div><br>", candidate.post.name);
            out.printf("<div class='name-poli'><b>%s</b></div>", candidate.name);
            out.printf("<div class='num-poli'><b>%s</b></div>", candidate.number);
            out.printf("<div class='part-poli'>%s</div>", candidate.party);
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='instr'>");
            out.println("Aperte a tecla:<br>");
            out.println("<br>");
            out.println("<b>CONFIRMAR</b> para <b>CONFIRMAR</b> este voto<br>");
            out.println("<b>CORRIGIR</b> para <b>REINICIAR</b> este voto");
            out.println("</div>");
            out.println("</div>");
            out.close();
        }
    }
    
    public static void renderConfirmDisplay(HttpServletResponse response) 
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
    
    public static void renderNullVote(HttpServletResponse response) 
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='visor'>");
            out.println("<div class='nulo-text'>Voto Nulo</div>");
            out.println("<div class='instr'>");
            out.println("Aperte a tecla:<br>");
            out.println("<br>");
            out.println("<b>CONFIRMAR</b> para <b>CONFIRMAR</b> este voto<br>");
            out.println("<b>CORRIGIR</b> para <b>REINICIAR</b> este voto");
            out.println("/div>");
            out.close();
        }
    }
    
    public static void renderFinishDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='fim'><b>Fim</b></div>");
            out.close();
        }
    }
    
    public static void renderPendingDisplay(HttpServletResponse response) 
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class='loading'>");
            out.println("<img src='static/img/loading.gif' alt='aguarde'>");
            out.println("<p>Aguarde...</p>");
            out.println("</div>");
            out.close();
        }
    }
}
