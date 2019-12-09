/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.Reclamation;

/**
 *
 * @author amine
 */
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.overpowered.byusforus.entities.reclamation.Reclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.TypeReclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.EtatReclamation;

@Stateless
public class ReclamationService  implements ReclamationLocal,ReclamationRemote
{
	@PersistenceContext(unitName="piJEE-ejb")
	public EntityManager em;

	@Override
	public int addReclamation(Reclamation r) {
		em.persist(r);
		return r.getIdReclamation();
		
	}
	
	@Override
	public List<Reclamation> All() {
		return em.createQuery("select r from Reclamation r order By r.dateReclamation DESC ",Reclamation.class).getResultList();
	}

	@Override
	public List<Reclamation> FindByType(TypeReclamation type) {
		return em.createQuery("select r from Reclamation r where r.type= :t  order By r.dateReclamation DESC",Reclamation.class).setParameter("t",type).getResultList();
	}
	@Override
	public List<Reclamation> FindByEtat(EtatReclamation etat) {
		return em.createQuery("select r from Reclamation r where r.etat= :e  order By r.dateReclamation DESC",Reclamation.class).setParameter("e",etat).getResultList();
	}

	@Override
	public int EditEtat(int idReclamation, EtatReclamation etat) {
		 return em.createQuery("UPDATE Reclamation SET etat= :etat where idReclamation= :id").setParameter("etat",etat).setParameter("id", idReclamation).executeUpdate();
	}

	@Override
	public Reclamation FindByNomOuPrenomUser(String nom, String prenom) {
		return null;
		}

	@Override
	public Reclamation FindByUsername(String UserName) {
		return null;
		}

	@Override
	public int CalculNombreTotalReclamationSelonType(TypeReclamation type) {
		return 0;
	}

	@Override
	public int CalculNombreTotalReclamation() {
		return 0;
	}

	@Override
	public int CalculNombreTotalReclamationSelonEtat(EtatReclamation etat) {
		return 0;
	}

	@Override
	public Reclamation FindById(int id) {
		return em.createQuery("select r from Reclamation r where r.idReclamation= :id",Reclamation.class ).setParameter("id",id).getSingleResult();
	}

	@Override
	public int DeleteReclamation() {
		return 0;
 		
	}

	@Override
	public int CalculNombreReclamationAsupprimer() {
		return 0;
		
	}



}