/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.OffsetDateTime;
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
import services.DB;

/**
 *
 * @author Lucy
 */
@Entity(name="reports")
public class Report {
    @Transient
    public ArrayList<String> errors = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public Long id;
    
    @Column(columnDefinition="TEXT", name="message", nullable=false, unique=false)
    public String message;
    
    @Column(name="created_at", nullable=false)
    public OffsetDateTime created_at = OffsetDateTime.now();
    
    @ManyToOne
    public Election election;
    
    
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
        
        if (check()) System.out.println("Report is valid.");
        
        for (String error : errors)
            System.out.println("ERROR: " + error);
        return errors.isEmpty();
    }
    
    public boolean check() {
        boolean hasElection = election != null;
        boolean hasMessage = message != null;
        
        if (!hasElection) errors.add("Election is null");
        if (!hasMessage) errors.add("Message is null");
        
        return hasElection && hasMessage;
    }
    
    public static List<Report> selectByElection(Election election) {
        System.out.println("Searching reports by election...");
        return DB.<Report>selectAndSort(Report.class, "reports", "election_id", election.id.toString(), "created_at", false);
    }
    
}
