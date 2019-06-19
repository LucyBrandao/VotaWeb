/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.Transient;
import services.DB;

/**
 *
 * @author Lucy
 */

@Entity(name="voters")
public class Voter {
    @Transient
    public static ArrayList<Voter> voters = new ArrayList<Voter>();
    @Transient
    public static HttpSession session;
    @Transient
    public ArrayList<String> errors;
    
    @Transient
    public boolean canVote = false;
    
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
    
    @Column(name="cpf", nullable=false, unique=true)
    public String cpf;
    
    @Column(name="session", nullable=true, unique=true)
    public String sessionId;
    
    @ManyToOne
    public Role role;
    
    @OneToMany(mappedBy="voter", targetEntity=VotingSession.class)
    public List<VotingSession> votingSessions;
    
    @OneToMany(mappedBy="voter", targetEntity=Vote.class)
    public List<Vote> votes;
    
    @ManyToMany(mappedBy="voters")//, targetEntity=Vote.class)
    public List<Election> elections;
    
    /*@OneToMany(mappedBy="voter", targetEntity=Vote.class)
    public List<Vote> votes;*/
    
    public Voter()
    {
    }

    public Voter(String username, String password, String name, String cpf) {
        this.errors = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
    }
    
    public boolean save() {
        boolean canSave = valid();
        if (!canSave) return false;
        System.out.println("Save begin...");
        DB.save(this);
        System.out.println("Save end!");
        return true;
    }
    
    public boolean valid() {
        assert ("1111".matches("[0-9]{2}") != true);
        assert ("".matches("[0-9]{11}") != true);
        
        errors.clear();
        
        if (checkUsername()) System.out.println("Username is valid.");
        if (checkPassword()) System.out.println("Password is valid.");
        if (checkName()) System.out.println("Name is valid.");
        if (checkCpf()) System.out.println("CPF is valid.");
        
        for (String error : errors)
            System.out.println("ERROR: " + error);
        return errors.isEmpty();
    }
    
    public boolean checkUsername() {
        Voter voter = findByUsername(username);
        boolean exists = voter != null;
        boolean updating = exists && voter.id.equals(this.id);
        boolean match = username.matches("[0-9]{12}");
        
        if (exists && !updating) errors.add("Username already exists");
        if (!match) errors.add("Username invalid");
        
        return (match && !exists) || (match && updating);
    }
    
    public boolean checkPassword() {
        boolean match = password.matches("[0-9]{8,32}");
        
        if (!match) errors.add("Password invalid");
        
        return match;
    }
    
    public boolean checkName() {
        boolean match = name.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+");
        
        if (!match) errors.add("Name invalid");
        
        return match;
    }
    
    public boolean checkCpf() {
        Voter voter = findByCpf(cpf);
        boolean exists = voter != null;
        boolean updating = exists && voter.id.equals(this.id);
        boolean match = cpf.matches("[0-9]{11}");
        
        if (exists && !updating) errors.add("CPF already exists");
        if (!match) errors.add("CPF invalid");
        
        return (match && !exists) || (match && updating);
    }
    
    public boolean ableToVote(Election election) {
        for (VotingSession vs : votingSessions) {
            if (vs.election.id == election.id) {
                if (vs.status == "enabled")
                    return true;
                else
                    return false;
            }
        }
        
        return false;
    }
    
    public void takeBallotPaper(Election election) {
        Vote vote = new Vote();
        
        vote.voter = this;
        vote.election = election;
        
        votes.add(vote);
    }
    
    public boolean canVote() {
        return role.level >= 1 && role.level <= 4;
    }
    
    public boolean canHabilitateVote() {
        return role.level >= 2 && role.level <= 4;
    }
    
    public boolean canPrintReport() {
        return role.level >= 3 && role.level <= 4;
    }
    
    public boolean canRegisterUser() {
        return role.level == 4;
    }
    
    // STATIC METHODS
    
    public static Voter findById(String id) {
        System.out.println("Searching for user by id...");
        return DB.<Voter>find(Voter.class, "voters", "id", id);
    }
    
    public static Voter findByUsername(String username) {
        System.out.println("Searching for user...");
        Voter voter = DB.<Voter>find(Voter.class, "voters", "username", username);
        if (voter != null) {
            if (voter.errors == null) {
                voter.errors = new ArrayList<>();
            }
        }
        return voter;
    }
    
    public static Voter findByCpf(String cpf) {
        System.out.println("Searching for user by CPF...");
        return DB.<Voter>find(Voter.class, "voters", "cpf", cpf);
    }
    
    public static Voter findBySession(String session) {
        System.out.println("Searching for user by Session...");
        return DB.<Voter>find(Voter.class, "voters", "session", session);
    }
}
