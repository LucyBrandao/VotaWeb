/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import services.DB;

/**
 *
 * @author Lucy
 */
@Entity(name="elections")
public class Election {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @ManyToMany//(mappedBy="elections", cascade = CascadeType.ALL)
    @JoinTable(name="voting_proofs",
             joinColumns={@JoinColumn(name="voter_id")},
             inverseJoinColumns={@JoinColumn(name="election_id")})
    public List<Voter> voters;
    
    //@OneToMany(mappedBy="election", targetEntity=Candidate.class)
    //public List<Candidate> candidates;
    
    @OneToMany(mappedBy="election", targetEntity=VotingSession.class)
    public List<VotingSession> votingSessions;
    
    @OneToMany(mappedBy="election", targetEntity=Vote.class)
    public List<Vote> votes;
    
    @Column(name="created_at", nullable=false)
    public OffsetDateTime created_at;
    
    @Column(name="starts_at", nullable=false)
    public OffsetDateTime startsAt;
    
    @Column(name="ends_at", nullable=false)
    public OffsetDateTime endsAt;
    
    @OneToMany(mappedBy="election", targetEntity=Report.class)
    public List<Report> report;
    
    public boolean save() {
        System.out.println("Save begin...");
        DB.save(this);
        System.out.println("Save end!");
        return true;
    }
    
    public static boolean isElectionPeriod() {
        System.out.println("Searching for election...");
        OffsetDateTime now = OffsetDateTime.now();
        
//        Election election = new Election();
//        election.created_at = now;
//        election.startsAt = now;
//        election.endsAt = now.plusMinutes(5);
//        election.save();
        
        System.out.println(now.toString());
        
        return !DB.<Election>selectBetweenDates(Election.class, "elections",
                "starts_at", "ends_at", now, now).isEmpty();
    }
}
