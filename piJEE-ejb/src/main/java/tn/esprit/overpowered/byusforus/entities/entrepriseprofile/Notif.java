/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.entrepriseprofile;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author pc
 */
@Entity
public class Notif implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String message;
    
    private Long cdtId;
    
    private Long offId;
    
    private boolean seen;

    public Notif() {
    }

    public Notif(String message, Long cdtId,Long offId) {
        this.message = message;
        this.cdtId = cdtId;
        this.seen = false;
        this.offId = offId;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCdtId() {
        return cdtId;
    }

    public void setCdtId(Long cdtId) {
        this.cdtId = cdtId;
    }

    public Long getOffId() {
        return offId;
    }

    public void setOffId(Long offId) {
        this.offId = offId;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
    
    
}
