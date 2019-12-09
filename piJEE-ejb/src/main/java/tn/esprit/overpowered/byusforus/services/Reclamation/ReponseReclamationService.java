/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.Reclamation;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.reclamation.ReponseReclamation;

/**
 *
 * @author amine
 */

@Stateless
public class ReponseReclamationService implements ReponseReclamationRemote,ReponseReclamationLocal
{

	@PersistenceContext(unitName="piJEE-ejb")
	public EntityManager em;
	@Override
	public int AddReponse(ReponseReclamation reponse) {
		em.merge(reponse);
		em.persist(reponse);
		return reponse.getIdReponse();
	}
	@Override
	public int RemoveReponse(ReponseReclamation reponse) {
		return em.createQuery("DELETE FROM ReponseReclamation WHERE idReponse= :id").setParameter("id",reponse.getIdReponse()).executeUpdate();
	}
	@Override
	public List<ReponseReclamation> FindReponseByidReclamation(int id) {
		return em.createQuery("select r from ReponseReclamation r where r.idReclamation= :id order By r.dateReponse ASC",ReponseReclamation.class).setParameter("id",id).getResultList();
	}
	@Override
	public int CalculTotalReponse(int idReclamation) {
		Long lol= em.createQuery("select count(r) from ReponseReclamation r where r.idReclamation= :id",Long.class).setParameter("id",idReclamation).getSingleResult();
		return lol.intValue();
	}
	


}

