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
import java.util.List;

import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.reclamation.ReponseReclamation;



@Local
public interface ReponseReclamationLocal {
	 	
	public int AddReponse(ReponseReclamation reponse);
	public int RemoveReponse(ReponseReclamation reponse);
	public List<ReponseReclamation> FindReponseByidReclamation(int id);
	public int CalculTotalReponse(int idReclamation);
	

}
