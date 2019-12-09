/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacade;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 * @author EliteBook
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal, UserFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User getUserByUsername(EntityManager em, String username) {
        TypedQuery<User> query
                = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query = query.setParameter("username", username);
        List<User> userList = query.getResultList();
        if (userList.isEmpty()) {
            return null;
        } else
            return userList.get(0);
    }

    @Override
    public Long createUser(User user) {
       em.persist(user);
       return user.getId();
  
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try{
        user = em.createQuery("select c from User c where "
                + "c.email = :email", User.class).setParameter("email", email).getSingleResult();
        }catch (NoResultException nre){

        }
        
        return user ;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = null;
        
        try{        
        user = em.createQuery("select c from User c where "
                + "c.username = :username",User.class).setParameter("username", username)
                .getSingleResult();
         }catch (NoResultException nre){
         }
        return user ;
    }

    @Override
    public String checkExistence(String email, String username) {
        User user = this.findUserByUsername(username);
        User user1 = this.findUserByEmail(email);
            if(user != null){
                return "this username already exist";
            }
            else if(user1 != null){
                return "this email already exist";
            }
            else if( user1 != null && user != null){
                return "this username and email already exist";
            }
            return "OK";
            
    }

    @Override
    public String getUserDiscriminatorValue(Long id) {
        User user= null;
        try {
            user = this.find(id);
        } catch (Exception e) {
        }
        if (user != null)
        return user.getDiscriminatorValue();
        else return "User Not found";
    }

 
        
    
    

}
