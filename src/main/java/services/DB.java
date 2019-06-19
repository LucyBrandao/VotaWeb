/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Lucy
 */
public class DB {
    private static final EntityManager MANAGER = Persistence.createEntityManagerFactory("persistencia_simples").createEntityManager();
    
    public static <T> ArrayList<String> save(T obj) {
        ArrayList<String> errors = new ArrayList<>();
        MANAGER.getTransaction().begin();
        try {
            MANAGER.persist(obj);
        } catch (javax.persistence.PersistenceException ex) {
            for (Throwable current = ex; current != null; current = current.getCause()) {
                if (current instanceof PSQLException) {
                    final PSQLException psqlEx = (PSQLException) current;
                    final ServerErrorMessage serverErrorMessage = psqlEx.getServerErrorMessage();
                    System.out.println("Mensagem: " + serverErrorMessage.getMessage());
                    System.out.println("Mensagem: " + serverErrorMessage.getDetail());
                    System.out.println("Mensagem: " + serverErrorMessage.getConstraint());
                    System.out.println("Mensagem: " + serverErrorMessage.getHint());
                    System.out.println("Mensagem: " + serverErrorMessage.getWhere());
                    errors.add(serverErrorMessage.getDetail());
                    //if ("EMAIL_UQ_IDX".equals(serverErrorMessage.getConstraint())) {
                        // handle duplicate E-Mail address
                    //}
                    //break;
                }
            }
            return errors;
        }
        MANAGER.flush();
        MANAGER.getTransaction().commit();
        return errors;
    }
    
    public static Long count(String table, String column, String param) {
        String qstr = String.format("SELECT COUNT(id) FROM %s WHERE %s = '%s'", 
                                    table,
                                    column,
                                    param);
        System.out.println(qstr);
        Query query = MANAGER.createQuery(qstr);
        Long result = (Long)query.getSingleResult();
        
        return result;
    }
    
    public static <T> T find(Class type, String table, String column, String param) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s'", 
                                    table,
                                    column,
                                    param);
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        //Query query = MANAGER.createQuery(qstr);
        T result = null;
        try {
            result = query.getSingleResult();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            //System.err.printf("%s not found, returning null\n", table);
        }
        //System.out.println(result.getClass().getName());
        System.err.println("Returning");
        return result;
    }
    
    public static <T> T find(Class type, String table, String col1, String col2, String param1, String param2) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s' AND %s = '%s'",
                                    table,
                                    col1,
                                    col2,
                                    param1,
                                    param2);
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        T result = null;
        try {
            result = (T) query.getSingleResult();
        } catch(Exception e) {
            System.err.printf("%s not found, returning null\n", table);
        }
        System.err.println("Returning");
        return result;
    }
    
    public static Object get(String table, String column, String param) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s'", 
                                    table,
                                    column,
                                    param);
        System.out.println(qstr);
        Query query = MANAGER.createQuery(qstr);
        //System.out.println(result.getClass().getName());
        Object result = query.getSingleResult();
        System.err.println("Returning result");
        return result;
    }
    
    public static <T> ArrayList<T> select2(Class type, String table, String column, String param) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s'", 
                                    table,
                                    column,
                                    param);
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        //Query query = MANAGER.createQuery(qstr);
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            System.err.println("No results, returning null");
            return null;
        }
        System.err.println("Results: " + result.size());
        return (ArrayList<T>) result;
    }
    
    public static <T> List<T> select(Class type, String table, String column, String param) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s'", 
                                    table,
                                    column,
                                    param);
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        //Query query = MANAGER.createQuery(qstr);
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            System.err.println("No results, returning null");
            return null;
        }
        System.err.println("Results: " + result.size());
        return result;
    }
    
    public static <T> List<T> selectAndSort(Class type, String table, String column, String param, String sortBy, boolean reverse) {
        System.out.println("Querying...");
        String qstr = String.format("SELECT e FROM %s e WHERE %s = '%s' ORDER BY %s %s", 
                                    table,
                                    column,
                                    param,
                                    sortBy,
                                    (reverse) ? "DESC" : "ASC");
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        //Query query = MANAGER.createQuery(qstr);
        List<T> result = query.getResultList();
        if (result.isEmpty()) {
            System.err.println("No results, returning null");
            return null;
        }
        System.err.println("Results: " + result.size());
        return result;
    }
    
    public static <T> List<T> selectBetweenDates(Class type, String table, 
            String leftColumn, String rightColumn, 
            OffsetDateTime start, OffsetDateTime end) {

        String qstr = String.format("SELECT e FROM %s e WHERE '%s' >= %s "
                + "AND '%s' <= %s", table, start.toString(), leftColumn, end.toString(), rightColumn);
        System.out.println(qstr);
        TypedQuery<T> query = MANAGER.createQuery(qstr, type);
        
        System.out.println("Querying...");
        List<T> result = query.getResultList();
        
        if (result.isEmpty()) System.out.println("No results.");
        return result;
    }
    
    public static boolean exists(Object obj, Class name) {
        return true;
    }
}
