package tn.esprit.overpowered.byusforus.entities.Paiement;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.ws.rs.ext.ParamConverter.Lazy;
import java.util.Date;
import javax.persistence.Temporal;
import tn.esprit.overpowered.byusforus.entities.users.User;

@Entity
public class Paiment implements Serializable {

    private static final long serialVersionUID = 3L;
    @OneToOne
    private Cheque cheque;
    @OneToOne
    private Virement virement;
    @OneToOne
    private BankCard bankcard;
    @OneToOne
    private Premium oeuvreDeclaration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Enumerated(EnumType.STRING)
    private EtatTitle Title;

    private int Price;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Date;
    private int Status;

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public EtatTitle getTitle() {
        return Title;
    }

    public void setTitle(EtatTitle title) {
        Title = title;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Virement getVirement() {
        return virement;
    }

    public void setVirement(Virement virement) {
        this.virement = virement;
    }

    public BankCard getBankcard() {
        return bankcard;
    }

    public void setBankcard(BankCard bankcard) {
        this.bankcard = bankcard;
    }
//	public Paiment(Cheque cheque, Virement virement, BankCard bankcard, int iD, EtatTitle title, int price, int date,
//			int status) {
//		super();
//		this.cheque = cheque;
//		this.virement = virement;
//		this.bankcard = bankcard;
//		ID = iD;
//		Title = title;
//		Price = price;
//		Date = date;
//		Status = status;
//	}
//	

    @Override
    public String toString() {
        return "Paiment [cheque=" + cheque + ", virement=" + virement + ", bankcard=" + bankcard + ", ID=" + ID
                + ", Title=" + Title + ", Price=" + Price + ", Date=" + Date + ", Status=" + Status + "]";
    }

    public Premium getOeuvreDeclaration() {
        return oeuvreDeclaration;
    }

    public void setOeuvreDeclaration(Premium oeuvreDeclaration) {
        this.oeuvreDeclaration = oeuvreDeclaration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
