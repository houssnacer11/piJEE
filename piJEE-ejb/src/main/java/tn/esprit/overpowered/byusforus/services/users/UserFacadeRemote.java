/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author EliteBook
 */
@Remote
public interface UserFacadeRemote {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();
    
    public Long createUser(User user);
    
    public User findUserByEmail(String email);
    
    public User findUserByUsername(String username);
    
    public String checkExistence(String email, String username);
    
    public String getUserDiscriminatorValue(Long id);
    

    
}
