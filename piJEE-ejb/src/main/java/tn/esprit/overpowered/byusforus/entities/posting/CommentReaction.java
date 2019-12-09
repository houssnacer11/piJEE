/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.posting;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Entity
public class CommentReaction implements Serializable {

    private static final long serialVersionUID = 18L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Comment on;

    @ManyToOne
    User by;

    public Comment getOn() {
        return on;
    }

    public void setOn(Comment on) {
        this.on = on;
    }

    public User getBy() {
        return by;
    }

    public void setBy(User by) {
        this.by = by;
    }

}
