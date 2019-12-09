/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.reclamation;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.DELETE;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author amine
 */
@Entity
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamation;
    @Enumerated(EnumType.STRING)
    private TypeReclamation type;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReclamation;
    @Enumerated(EnumType.STRING)
    private EtatReclamation etat;
    private String fichier_a_joindre;
    @OneToMany(mappedBy = "reclamation", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)//Slave
    private List<ReponseReclamation> reponses;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user = new User();
    private static final long serialVersionUID = 26L;

    public Reclamation() {
        super();
    }

    public int getIdReclamation() {
        return this.idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public TypeReclamation getType() {
        return this.type;
    }

    public void setType(TypeReclamation type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EtatReclamation getEtat() {
        return this.etat;
    }

    public void setEtat(EtatReclamation etat) {
        this.etat = etat;
    }

    public String getFichier_a_joindre() {
        return this.fichier_a_joindre;
    }

    public void setFichier_a_joindre(String fichier_a_joindre) {
        this.fichier_a_joindre = fichier_a_joindre;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reclamation [idReclamation=" + idReclamation + ", type=" + type + ", description=" + description
                + ", dateReclamation=" + dateReclamation + ", etat=" + etat + ", fichier_a_joindre=" + fichier_a_joindre
                + ", user=" + user + "]";
    }

}
