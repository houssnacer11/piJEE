/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.authentication;

import java.security.NoSuchAlgorithmException;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;

/**
 *
 * @author EliteBook
 */
@Remote
public interface AuthenticationFacadeRemote {

    Session finalizeLogin(String uid, String auth2FAToken);
    String login(String username, String password) throws NoSuchAlgorithmException;

    int logout();

    int isLoggedIn(final String token);
    
}
