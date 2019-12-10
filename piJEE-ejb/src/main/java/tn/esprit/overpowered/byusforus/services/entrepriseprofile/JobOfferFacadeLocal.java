/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

/**
 *
 */
@Local
public interface JobOfferFacadeLocal {

    void create(JobOffer jobOffer);

    void edit(JobOffer jobOffer);

    void remove(JobOffer jobOffer);

    JobOffer find(Object id);

    List<JobOffer> findAll();

    List<JobOffer> findRange(int[] range);

    int count();
    
}
