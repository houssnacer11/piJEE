/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.reclamation;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author amine
 */
@Entity
public class ReponseReclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReponse;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReponse;
    private static final long serialVersionUID = 27L;
    @ManyToOne //Master
    @JoinColumn(name = "idReclamation")
    private Reclamation reclamation = new Reclamation();
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user = new User();

    public ReponseReclamation() {

    }

    public int getIdReponse() {
        return this.idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReponse() {
        return this.dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    @Override
    public String toString() {
        return "ReponseReclamation [idReponse=" + idReponse + ", description=" + description + ", dateReponse="
                + dateReponse + ", reclamation=" + reclamation + "]";
    }

}
