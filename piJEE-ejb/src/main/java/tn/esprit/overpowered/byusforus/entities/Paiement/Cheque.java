package tn.esprit.overpowered.byusforus.entities.Paiement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cheque implements Serializable {

    private static final long serialVersionUID = 2L;
    @OneToOne(mappedBy = "cheque")
    private Paiment paiment;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCheque;
    private int Num;
    private String bank;
    private int agence;
    private String Price;
    private int status;
    private String image;

    public Paiment getPaiment() {
        return paiment;
    }

    public void setPaiment(Paiment paiment) {
        this.paiment = paiment;
    }

    public int getIdCheque() {
        return IdCheque;
    }

    public void setIdCheque(int idCheque) {
        IdCheque = idCheque;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getAgence() {
        return agence;
    }

    public void setAgence(int agence) {
        this.agence = agence;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
