/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Lucy
 */
//@Entity(name="voting_proofs")
public class VotingProof {
    
    //@OneToOne
    Voter voter;
    
    //@OneToOne
    Election election;
}
