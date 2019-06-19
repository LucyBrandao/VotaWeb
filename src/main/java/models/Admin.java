/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import services.DB;

/**
 *
 * @author Lucy
 */

@Entity(name="admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @Column(name="username", nullable=false, unique=true)
    public String username;
    
    @Column(name="password", nullable=false)
    public String password;
    
    @Column(name="name", nullable=false)
    public String name;
    
    @Column(name="session", nullable=false)
    public String sessionId;
    
    /*@Transient
    public ArrayList<String> errors;*/
    /*@ManyToOne
    @JoinTable(name="admin_roles",  
                   joinColumns={@JoinColumn(name="role_id", 
                    referencedColumnName="id")},  
                   inverseJoinColumns={@JoinColumn(name="admin_id", 
                     referencedColumnName="id")})
    //@JoinColumn(name="role_id")*/
    @ManyToOne
    public Role role;
    
    public boolean hasPermission(String role) {
        if (this.role == null) return false;
        return this.role.name.equals(role);
    }
    
    public static Admin findByUsername(String username) {
        System.out.println("Searching for admin...");
        return DB.<Admin>find(Admin.class, "admins", "username", username);
    }
    
}
