/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fabian
 */
public class Voter {
    public static ArrayList<Voter> voters = new ArrayList<Voter>();
    public static HttpSession session;
    
    
    private String username;
    private String password;
    private String name;
    private String cpf;

    public Voter(String username, String password, String name, String cpf) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void save() {
        Voter voter = findByUsername(this.username);
        if (voter == null) {
            voters.add(this);
        } else {
            int index = voters.indexOf(voter);
            voters.set(index, this);
        }
    }
    
    // STATIC METHODS
    
    public static Voter findByUsername(String username) {
        for (Voter voter : voters)
            if (voter.getUsername().equals(username))
                return voter;
        return null;
    }
}
