/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.messaging;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import tn.esprit.overpowered.byusforus.entities.messaging.Message;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Remote
public interface MessagingRemote {
    
    public void sendMessage(Message m, Long a, Long b);
    public ArrayList<Message> getMessages(Long userId, Date t);
    public ArrayList<Message> getMyMessages(Long userId);
    public void hideMessage(Long userId, Long messageId);
    public void seeMessage(Long userId, Long messageId);
    public EntityManager getEntityManager();
}
