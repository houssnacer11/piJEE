/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.authentication;

import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.authentication.Auth2FA;

/**
 *
 */
@Stateful
public class Auth2FAFacade implements Auth2FAFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public Auth2FA geAuth2FAByUid(EntityManager em, String uid, String auth2FAToken) {
        TypedQuery<Auth2FA> query = em.createQuery("SELECT t FROM Auth2FA t WHERE t.uid = :uid", Auth2FA.class);
        query.setParameter("uid", uid);
        List<Auth2FA> auth2FAList = query.getResultList();
        if (!auth2FAList.isEmpty()) {
           Auth2FA auth2FA = auth2FAList.get(0);
           String strToken = Integer.toString(auth2FA.getToken());
           if (strToken.equals(auth2FAToken))
               return auth2FA;
           else
               return null;
        }
        else
            return null;
    }
}
