/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;

/**
 *
 */
@Remote
public interface JobOfferFacadeRemote {

    void create(JobOffer jobOffer);

    void edit(JobOffer jobOffer);

    void remove(JobOffer jobOffer);

    JobOffer find(Object id);

    List<JobOffer> findAll();

    List<JobOffer> findRange(int[] range);

    int count();
    
    public Long addOffer(JobOffer jobOffer);
    public void updateOffer(JobOffer jobOffer);
    public void deleteOffer(Long idOffer);
    public List<JobOffer> searchByTitle(String title);
    public List<JobOffer> searchByLocation(String location);
    public List<JobOffer> searchByDate(Date date);
    public List<JobOffer> searchByExpertise(ExpertiseLevel expLevel);
    public List<JobOffer> viewOffersBydate(List <JobOffer> offers);
    public List<JobOffer> viewOffersByUserSkill(List <JobOffer> offers, Long idUser);
    public List<JobOffer> viewAllOffers();
    public JobOffer searchJobOfferByTitle(String title);
    public List<JobOffer> generalSearch(String title, String location, ExpertiseLevel expLevel);
    
    
    
}
