/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Stateful
public class Messaging implements MessagingRemote, MessagingLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")z
    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void sendMessage(Message m, Long senderId, Long receiverId) {
        User from = em.find(User.class, senderId);
        m.setFrom(from);
        User to = em.find(User.class, receiverId);
        m.setTo(to);
        //em.merge(m);
        em.persist(m);
    }

    @Override
    public ArrayList<Message> getMessages(Long userId, Date t) {
        User u = em.find(User.class, userId);
        MessageRepository msgR = new MessageRepository();
        User au = em.find(User.class, u.getId());
        
        return msgR.getNewestMessages(em, au, t);
    }

    @Override
    public ArrayList<Message> getMyMessages(Long userId) {
        User u = em.find(User.class, userId);
        MessageRepository msgR = new MessageRepository();
        return msgR.getAllMessages(em, u);

    }

    @Override
    public void hideMessage(Long userid, Long messageId) {
        User u = em.find(User.class, userid);
        Message m = em.find(Message.class, messageId);
        if (m.getFrom().equals(u)) {
            m.setHiddenBySender(true);
        } else if (m.getTo().equals(u)) {
            m.setHiddenByReceiver(true);
        }
    }

    @Override
    public void seeMessage(Long userid, Long messageid) {
        
        User u = em.find(User.class, messageid);
        Message message = em.find(Message.class, messageid);
        em.getTransaction().begin();
        message.setSeenByReceiver(true);
        em.getTransaction().commit();

    }

}
