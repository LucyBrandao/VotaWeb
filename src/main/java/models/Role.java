/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Lucy
 */

@Entity(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @Column(name="name", nullable=false, unique=true)
    public String name;
    
    @Column(name="description", nullable=false)
    public String description;
    
    @Column(name="level", nullable=false, unique=true)
    public Short level;
    
    /*@OneToMany(mappedBy = "roles", targetEntity = Admin.class, 
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="admin_roles",  
                   joinColumns={@JoinColumn(name="role_id", 
                    referencedColumnName="id")},  
                   inverseJoinColumns={@JoinColumn(name="admin_id", 
                     referencedColumnName="id")})*/
    //@Column(name="admins")
    @OneToMany(mappedBy="role", targetEntity=Admin.class)
    public List<Admin> admins;
    
    @OneToMany(mappedBy="role", targetEntity=Voter.class)
    public List<Voter> voters;
    
    /*@Transient
    public ArrayList<String> errors;*/
}
