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

import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.reclamation.EtatReclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.Reclamation;
import tn.esprit.overpowered.byusforus.entities.reclamation.TypeReclamation;

@Remote
public interface ReclamationRemote
{
	public int addReclamation(Reclamation r);
	public List<Reclamation> All();
	public List<Reclamation> FindByType(TypeReclamation type);
	public int EditEtat(int idReclamation, EtatReclamation etat);
	public Reclamation FindByNomOuPrenomUser(String nom,String prenom);
	public Reclamation FindByUsername(String UserName);
	public int CalculNombreTotalReclamationSelonType(TypeReclamation type);
	public int CalculNombreTotalReclamationSelonEtat(EtatReclamation etat);
	public int CalculNombreTotalReclamation();
	public List<Reclamation> FindByEtat(EtatReclamation etat);
	public Reclamation FindById(int id);
	public int DeleteReclamation();
	public int CalculNombreReclamationAsupprimer();
}
