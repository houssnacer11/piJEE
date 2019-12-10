/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.authentication;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.authentication.Auth2FA;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.services.users.UserFacade;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 */
@Stateful
public class AuthenticationFacade implements AuthenticationFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Session finalizeLogin(String uid, String auth2FAToken) {
        Auth2FA auth2FA = new Auth2FAFacade().geAuth2FAByUid(em, uid, auth2FAToken);
        if (auth2FA == null)
            return null;
        
        Session s = new Session();
        s.setUid(auth2FA.getUid());
        s.setUser(auth2FA.getUser());
        em.remove(auth2FA);
        em.persist(s);
        return s;
    }
    @Override
    public String login(String username, String password) throws NoSuchAlgorithmException {
        Logger.getAnonymousLogger().info("given password " + password + "Username "+ username);
        byte[] pwd = password.getBytes(StandardCharsets.UTF_8);
        User user = new UserFacade().getUserByUsername(em, username);
        
        if (user == null)
            return null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = new byte[pwd.length + user.getSalt().length];
        System.arraycopy(pwd, 0, hashBytes, 0, pwd.length);
        System.arraycopy(user.getSalt(), 0, hashBytes, pwd.length, user.getSalt().length);
        Logger.getAnonymousLogger().info("*PASS*****" +user.getPassword());
         ArrayList<Byte> a;
        a = new ArrayList<>();
        for (byte b : user.getPassword())
            a.add(b);
        a.add((byte)101);
        a.add((byte)101);
        for (byte b: digest.digest(hashBytes) )
            a.add(b);
        a.add((byte)101);
                a.add((byte)101);

        String s = new String();
        for (byte b: a)
            s += b;
        
         Logger.getAnonymousLogger().info("tow byte arr " + s);
        if (Arrays.equals(user.getPassword(), digest.digest(hashBytes))) {
            // Create new 2FA token + random token for identification
            String uid = UUID.randomUUID().toString();
            int token = gen2FAToken();
            Auth2FA towFactorAuth = new Auth2FA(token, uid, user);
            // Persist 2FA token
            em.persist(towFactorAuth);
            try {
                // Send email
                // Username and password are redacted
                System.out.println("****"+towFactorAuth.getToken());
                if (MailSender.sendMail("smtp.gmail.com", "587",
                        "pidevnoreply@gmail.com", "pidevnoreply@gmail.com",
                        "pidevpidev", user.getEmail(), "Authentication code", "Your code is " + towFactorAuth.getToken()))
                                return towFactorAuth.getUid();

            } catch (MessagingException ex) {
                Logger.getAnonymousLogger().info("Erreur");
                //Logger.getLogger(AuthenticationFacade.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
        } else {
            return null;
        }
        //return null;
        return String.valueOf(user.getId());
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int logout() {
        return 0;
    }

    @Override
    public int isLoggedIn(final String token) {
        return 0;
    }

    private String genToken() {
        return "succes";
    }

    private int gen2FAToken() {
        int randomNum = ThreadLocalRandom.current().nextInt(1666, 9999 + 1);
        return randomNum;
    }

}
