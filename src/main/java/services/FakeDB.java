/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import models.Voter;

/**
 *
 * @author Lucy
 */
public class FakeDB {
    private static FakeDB db;
    private ArrayList<Voter> voters;
    
    private FakeDB() {
        voters = new ArrayList<>();
        createDefaultVoters();
    }
    
    public static FakeDB DB() {
        if (db == null) db = new FakeDB();
        return db;
    }
    
    private void createDefaultVoters() {
        Voter voter1 = new Voter("111", "123", "Voter1", "000.000.000-01");
        Voter voter2 = new Voter("222", "123", "Voter2", "000.000.000-02");
        voter1.save();
        voter2.save();
    }
    
    public void save(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Voter":
                Voter voter = Voter.class.cast(object);
                saveVoter(voter);
                System.out.println("Usuário salvo.");
                break;
            default:
                System.out.println("Não obtive o resultado esperado.");
        }
    }
    
    private void saveVoter(Voter voter) {
        if (Voter.findByUsername(voter.username) == null) {
            voters.add(voter);
        } else {
            int index = voters.indexOf(voter);
            voters.set(index, voter);
        }
    }
}
