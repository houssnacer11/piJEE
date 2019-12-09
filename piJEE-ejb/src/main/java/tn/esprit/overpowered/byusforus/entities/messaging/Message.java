/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.messaging;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Entity
public class Message implements Serializable {

    public Message() {
        this.sentTime = new Date();
        this.seenBySenderAt = this.sentTime;
    }

    private static final long serialVersionUID = 16L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        
       
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
    
    @OneToOne
    User from;
    @OneToOne
    User to;
    
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    boolean seenByReceiver = false;
    Date seenByReceiverAt;
    boolean seenBySender = true;
    Date seenBySenderAt;
    
    boolean hiddenByReceiver = false;
    boolean hiddenBySender = false;

    public boolean isHiddenByReceiver() {
        return hiddenByReceiver;
    }

    public void setHiddenByReceiver(boolean hiddenByReceiver) {
        this.hiddenByReceiver = hiddenByReceiver;
    }

    public boolean isHiddenBySender() {
        return hiddenBySender;
    }

    public void setHiddenBySender(boolean hiddenBySender) {
        this.hiddenBySender = hiddenBySender;
    }

    public boolean isSeenByReceiver() {
        return seenByReceiver;
    }

    public void setSeenByReceiver(boolean seenByReceiver) {
        this.seenByReceiver = seenByReceiver;
    }

    public Date getSeenByReceiverAt() {
        return seenByReceiverAt;
    }

    public void setSeenByReceiverAt(Date seenByReceiverAt) {
        this.seenByReceiverAt = seenByReceiverAt;
    }

    public boolean isSeenBySender() {
        return seenBySender;
    }

    public void setSeenBySender(boolean seenBySender) {
        this.seenBySender = seenBySender;
    }

    public Date getSeenBySenderAt() {
        return seenBySenderAt;
    }

    public void setSeenBySenderAt(Date seenBySenderAt) {
        this.seenBySenderAt = seenBySenderAt;
    }

    public boolean isDeletedByReceiver() {
        return deletedByReceiver;
    }

    public void setDeletedByReceiver(boolean deletedByReceiver) {
        this.deletedByReceiver = deletedByReceiver;
    }

    public boolean isDeletedBySender() {
        return deletedBySender;
    }

    public void setDeletedBySender(boolean deletedBySender) {
        this.deletedBySender = deletedBySender;
    }
    
    boolean deletedByReceiver;
    boolean deletedBySender;

 
    private Date sentTime;

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.messaging_notifications.Message[ id=" + id + " ]";
    }
    
}
