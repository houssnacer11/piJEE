/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.authentication;

import java.io.Serializable;
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
public class Auth2FA implements Serializable {

    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Auth2FA(String uid, User user) {
        this.uid = uid;
        this.user = user;
    }
    
    
    private int token;

    public int getToken() {
        return token;
    }

    public String getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public Auth2FA() {
    }

    public Auth2FA(int token, String uid, User user) {
        this.token = token;
        this.uid = uid;
        this.user = user;
    }
    private String uid;
    
    @OneToOne
    private User user;

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
        if (!(object instanceof Auth2FA)) {
            return false;
        }
        Auth2FA other = (Auth2FA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.authentication.Auth2FA[ id=" + id + " ]";
    }
    
}
