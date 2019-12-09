/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.authentication.Auth2FA;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
public class MessageRepository {

    public ArrayList<Message> getNewestMessages(EntityManager em, User u, Date t) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.sentTime > :time and m.from = :u", Message.class);
        query.setParameter("time", t);
        query.setParameter("from", u);
        ArrayList<Message> result = new ArrayList();
        result.addAll(query.getResultList());
        for (Message m : result) {
            if (m.getFrom().equals(u) && m.isHiddenBySender()) {
                result.remove(m);
            }
            if (m.getTo().equals(u) && m.isHiddenByReceiver()) {
                result.remove(m);
            }
        }
        return result;
    }

    public ArrayList<Message> getAllMessages(EntityManager em, User u) {
        ArrayList<Message> result = new ArrayList();
        result.addAll(em.createQuery(
                "SELECT m FROM Message m WHERE m.from = :u OR m.to = :u ", Message.class)
                .setParameter("u", u).getResultList());

        for (Message m : result) {
            if (m.getFrom().equals(u) && m.isHiddenBySender()) {
                result.remove(m);
            }
            if (m.getTo().equals(u) && m.isHiddenByReceiver()) {
                result.remove(m);
            }
        }

        return result;
    }
}
