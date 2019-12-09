package tn.esprit.overpowered.byusforus.entities.Paiement; 

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Entity implementation class for Entity: Premium
 *
 */
@Entity

public class Premium implements Serializable {

    	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String Titre;
	@NotNull
	private String Categorie;
	@NotNull
	@Enumerated(EnumType.STRING)
	private EtatOeuvreDeclaration etat;
	@NotNull
	private String Description;
	private String Support;
	@Temporal(TemporalType.DATE)
	@Column(name="DateInitial")
	private Date DateInitial;
	@Temporal(TemporalType.DATE)
	@Column(name="DateFin")
	private Date DateFin;
	@Temporal(TemporalType.DATE)
	@Column(name="Deadline")
	private Date Deadline;
	@Temporal(TemporalType.DATE)
	@Column(name="DeadlineFirstAlerte")
	private Date DeadlineFirstAlerte;
	private int EtatDeadline;
	private int EtatFirstAlerte;
	private String path;
	
	
	private Paiment paiment;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}

	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSupport() {
		return Support;
	}
	public void setSupport(String support) {
		Support = support;
	}
	public Date getDateInitial() {
		return DateInitial;
	}
	public void setDateInitial(Date dateInitial) {
		DateInitial = dateInitial;
	}

	public String getCategorie() {
		return Categorie;
	}
	public void setCategorie(String categorie) {
		Categorie = categorie;
	}
	
	public Date getDateFin() {
		return DateFin;
	}
	public void setDateFin(Date dateFin) {
		DateFin = dateFin;
	}
	public Date getDeadline() {
		return Deadline;
	}
	public void setDeadline(Date deadline) {
		Deadline = deadline;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public EtatOeuvreDeclaration getEtat() {
		return etat;
	}
	public void setEtat(EtatOeuvreDeclaration etat) {
		this.etat = etat;
	}

	public Date getDeadlineFirstAlerte() {
		return DeadlineFirstAlerte;
	}
	public void setDeadlineFirstAlerte(Date deadlineFirstAlerte) {
		DeadlineFirstAlerte = deadlineFirstAlerte;
	}
	public int getEtatDeadline() {
		return EtatDeadline;
	}
	public void setEtatDeadline(int etatDeadline) {
		EtatDeadline = etatDeadline;
	}
	public int getEtatFirstAlerte() {
		return EtatFirstAlerte;
	}
	public void setEtatFirstAlerte(int etatFirstAlerte) {
		EtatFirstAlerte = etatFirstAlerte;
	}
	


	public Premium() {
		super();
	}
	@Override
	public String toString() {
		return "OeuvreDeclaration [id=" + id + ", Titre=" + Titre + ", Categorie=" + Categorie + ", etat=" + etat
				+ ", Description=" + Description + ", Support=" + Support + ", DateInitial=" + DateInitial
				+ ", DateFin=" + DateFin + ", Deadline=" + Deadline + ", DeadlineFirstAlerte=" + DeadlineFirstAlerte
				+ ", EtatDeadline=" + EtatDeadline + ", EtatFirstAlerte=" + EtatFirstAlerte + ", path=" + path + "]";
	}
	public Paiment getPaiment() {
		return paiment;
	}
	public void setPaiment(Paiment paiment) {
		this.paiment = paiment;
	}
	
	
  
   
}
