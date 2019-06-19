/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import services.DB;

/**
 *
 * @author Lucy
 */
@Entity(name="candidates")
public class Candidate {
    @Transient
    public ArrayList<String> errors;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @Column(name="name", nullable=false)
    public String name;
    
    @Column(name="party", nullable=false)
    public String party;
    
    @ManyToOne
    public Post post;
    
    @Column(name="number", nullable=false)
    public String number;
    
    @OneToMany(mappedBy="candidate", targetEntity=Vote.class)
    public List<Vote> votes;
    
    Candidate() {
        
    }
    
    Candidate(String name, String party, String number) {
        this.errors = new ArrayList<>();
        this.name = name;
        this.party = party;
        this.number = number;
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
        errors.clear();
        
        checkName();
        checkPost();
        checkParty();
        checkNumber();
        
        for (String error : errors)
            System.out.println("ERROR: " + error);
        return errors.isEmpty();
    }
    
    public boolean checkName() {
        boolean match = name.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+");
        
        if (!match) errors.add("Name invalid");
        
        return match;
    }
    
    public boolean checkPost() {
        boolean hasPost = post != null;
        
        if (!hasPost) errors.add("Post invalid");
        
        return hasPost;
    }
    
    public boolean checkParty() {
        boolean match = party.matches("[A-Za-z]+");
        if (!match) errors.add("party invalid");
        
        return match;
    }
    
    public boolean checkNumber() {
        Candidate candidate = findByNumber(number);
        boolean exists = candidate != null;
        boolean updating = exists && this.number.equals(candidate.number);
        boolean match = number.matches("[0-9]+");
        
        if (exists && !updating) errors.add("Number already exists");
        if (!match) if (!match) errors.add("Number invalid");
        
        return (match && !exists) || (match && updating);
    }
    
    // STATIC METHODS
    
    public static Candidate findByName(String name) {
        System.out.println("Searching for candidate...");
        return DB.<Candidate>find(Candidate.class, "candidates", "name", name);
    }
    
    public static Candidate findByNumber(String number) {
        System.out.println("Searching for candidate...");
        return DB.<Candidate>find(Candidate.class, "candidates", "number", number);
    }
    
    public static List<Candidate> findCandidatesByParty(String party) {
        System.out.println("Searching for candidate...");
        return DB.<Candidate>select(Candidate.class, "candidates", "party", party);
    }
    
    public static List<Candidate> selectByElection(Election election) {
        System.out.println("Searching reports by election...");
        return DB.<Candidate>selectAndSort(Candidate.class, "candidates", "election_id", election.id.toString(), "post_id", false);
    }
}
