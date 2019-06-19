/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Lucy
 */

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

@Entity(name="posts")
public class Post {
    @Transient
    public ArrayList<String> errors = new ArrayList<>();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "serial", updatable=false, nullable=false, unique=true)
    public long id;
    
    @Column(name="name", nullable=false)
    public String name;
    
    @OneToMany(mappedBy="post", targetEntity=Candidate.class)
    public List<Candidate> candidates;
}
