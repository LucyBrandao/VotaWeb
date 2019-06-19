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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import static models.Voter.findByCpf;
import services.DB;

/**
 *
 * @author Lucy
 */

@Entity(name="voting_sessions")
public class VotingSession {
    @Transient
    public ArrayList<String> errors = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @Column(nullable=false, unique=false)
    public String status = "pending";
    
    @ManyToOne
    public Election election;
    
    @ManyToOne
    public Voter voter;
    
    @OneToOne
    public Voter enabledBy;
    
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
        
        if (checkNull() && checkIfExist()) System.out.println("VotingSession is valid.");
        
        for (String error : errors)
            System.out.println("ERROR: " + error);
        return errors.isEmpty();
    }
    
    public boolean checkIfExist() {
        VotingSession vs = findByVoterAndElection(voter, election);
        boolean exists = vs != null;
        boolean updating = exists && vs.id.equals(this.id);
        
        if (exists && !updating) errors.add("VotingSessions already exists");
        
        return !exists || updating;
    }
    
    public boolean checkNull() {
        boolean voterIsNull = voter == null;
        boolean electionIsNull = election == null;
        boolean enabledByIsNull = enabledBy == null;
        
        if (voterIsNull) errors.add("Voter is null");
        if (electionIsNull) errors.add("Election is null");
        if (enabledByIsNull) errors.add("EnabledBy is null");
        
        return !(voterIsNull || electionIsNull || enabledByIsNull);
    }
    
    public static VotingSession findByVoterAndElection(Voter voter, Election election) {
        System.out.println("Searching for VotingSession by voter and election...");
        return DB.<VotingSession>find(VotingSession.class, "voting_sessions",
            "voter_id", voter.id.toString(), "election_id", election.id.toString());
    }
}
