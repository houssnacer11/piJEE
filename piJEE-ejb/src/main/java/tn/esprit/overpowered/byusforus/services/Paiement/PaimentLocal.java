
package tn.esprit.overpowered.byusforus.services.Paiement;

import java.util.List;

import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.Paiement.Cheque;
import tn.esprit.overpowered.byusforus.entities.Paiement.Paiment;
import tn.esprit.overpowered.byusforus.entities.Paiement.Virement;
import tn.esprit.overpowered.byusforus.entities.users.User;



@Local
public interface PaimentLocal {

	public Paiment findById(int id);

	public List<Paiment> DisplayPaimentDeclaration();

	public List<Paiment> DisplayPaimentDeposit();

	public List<Paiment> DisplayPaimentMemberShip();

	public void EditStatusPaiment(Paiment p);

	public void AddPaiment(Paiment p);

	public Paiment DisplayPaimentHistorie(User u);

	public Long DisplayPaimentStatCotisationOk();

	public Long DisplayPaimentStatCotisationNo();

	public Cheque addCheque(Cheque cheque);

	public Virement addVirement(Virement virement);

	public List<Paiment> DisplayMyPaimentDeclaration(User u);

	public List<Paiment> DisplayMyPaimentDeposit(User u);

	public List<Paiment> DisplayMyPaimentMembership(User u);

}
