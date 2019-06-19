/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import static models.Voter.findByUsername;
import services.DB;

/**
 *
 * @author Lucy
 */
@Entity(name="votes")
public class Vote {
    
    @Transient
    public ArrayList<String> errors = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public long id;
    
    @ManyToOne
    public Election election;
    
    @ManyToOne(optional = true)
    public Voter voter;
    
    @ManyToOne(optional = true)
    public Candidate candidate;
    
    @Column(name="created_at", nullable=false)
    public OffsetDateTime created_at;
    
    public Vote() {
        
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
        
        if (checkFields()) System.out.println("Fields are valids.");
        
        for (String error : errors)
            System.out.println("ERROR: " + error);
        return errors.isEmpty();
    }
    
    public boolean checkFields() {        
        boolean hasVoter = voter != null;
        boolean hasElection = election != null;
        boolean hasCandidate = candidate != null;
        boolean hasCreatedAt = created_at != null;
        
        if (!hasVoter) errors.add("Voter is null");
        if (!hasElection) errors.add("Election is null");
        if (!hasCandidate) errors.add("Candidate is null");
        if (!hasCreatedAt) errors.add("CreatedAt is null");
        
        return hasCandidate && hasElection && hasVoter && hasCreatedAt;
    }
}
