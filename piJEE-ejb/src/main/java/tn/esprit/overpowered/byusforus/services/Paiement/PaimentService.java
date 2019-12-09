package tn.esprit.overpowered.byusforus.services.Paiement;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.Paiement.Cheque;
import tn.esprit.overpowered.byusforus.entities.Paiement.Cheque;
import tn.esprit.overpowered.byusforus.entities.Paiement.Paiment;
import tn.esprit.overpowered.byusforus.entities.Paiement.Paiment;
import tn.esprit.overpowered.byusforus.entities.Paiement.Virement;
import tn.esprit.overpowered.byusforus.entities.Paiement.Virement;
import tn.esprit.overpowered.byusforus.entities.users.User;


@Stateless
@LocalBean
public class PaimentService implements PaimentRemote, PaimentLocal {

	@PersistenceContext(unitName = "piJEE-ejb")
	private EntityManager em;

	@Override
	public List<Paiment> DisplayPaimentDeclaration() {
		TypedQuery<Paiment> query = em
				.createQuery("select p from Paiment p where p.Title='declaration' order By p.Status", Paiment.class);
		return query.getResultList();
	}
        
	@Override
	public List<Paiment> DisplayPaimentDeposit() {
		TypedQuery<Paiment> query = em.createQuery("select p from Paiment p where p.Title='deposit' order By p.Status",
				Paiment.class);
		return query.getResultList();
	}

	@Override
	public List<Paiment> DisplayPaimentMemberShip() {
		TypedQuery<Paiment> query = em
				.createQuery("select p from Paiment p where p.Title='membershipfee' order By p.Status", Paiment.class);
		return query.getResultList();
	}

	@Override
	public void EditStatusPaiment(Paiment p) {
		// TODO Auto-generated method stub
		em.merge(p);
	}

	@Override
	public void AddPaiment(Paiment p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public Paiment DisplayPaimentHistorie(User u) {
		// TODO Auto-generated method stub
		List<Paiment> query = em
				.createQuery("select p from Paiment p where p.user='" + u.getId()
						+ "' and p.Title='membershipfee' order By p.ID desc", Paiment.class)
				.setMaxResults(1).getResultList();
		// = em.createQuery("select p from Paiment p where
		// p.user='"+u.getID()+"' and
		// p.Title='membershipfee'",Paiment.class).getSingleResult();
		return query.get(0);
	} 

	@Override
	public Long DisplayPaimentStatCotisationOk() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(p) from Paiment p where p.Title='membershipfee' and p.Status=1");

		return (Long) query.getSingleResult();
	}

	@Override
	public Long DisplayPaimentStatCotisationNo() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select count(p) from Paiment p where p.Title='membershipfee' and p.Status=0");
		return (Long) query.getSingleResult();
	}

	@Override
	public Paiment findById(int id) {
		// TODO Auto-generated method stub
		return em.find(Paiment.class, id);
	}

	@Override
	public Cheque addCheque(Cheque cheque) {
		// TODO Auto-generated method stub
		em.persist(cheque);
		return cheque;

	}

	@Override
	public Virement addVirement(Virement virement) {
		em.persist(virement);
		return virement;
	}

	@Override
	public List<Paiment> DisplayMyPaimentDeposit(User u) {
		TypedQuery<Paiment> query = em.createQuery(
				"select p from Paiment p where p.Title='deposit' and p.Status=1 and p.user=" + u.getId(),
				Paiment.class);
		return query.getResultList();
	}

	@Override
	public List<Paiment> DisplayMyPaimentDeclaration(User u) {
		TypedQuery<Paiment> query = em.createQuery(
				"select p from Paiment p where p.Title='declaration' and p.Status=1 and p.user=" + u.getId(),
				Paiment.class);
		return query.getResultList();
	}

	@Override
	public List<Paiment> DisplayMyPaimentMembership(User u) {
		TypedQuery<Paiment> query = em.createQuery(
				"select p from Paiment p where p.Title='membershipfee' and p.Status=1 and p.user=" + u.getId(),
				Paiment.class);
		return query.getResultList();
	}

}
