/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.Professional;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.OfferStatus;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 */
@Stateless
public class JobOfferFacade extends AbstractFacade<JobOffer> implements JobOfferFacadeLocal, JobOfferFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobOfferFacade() {
        super(JobOffer.class);
    }

    @Override
    public Long addOffer(JobOffer jobOffer) {
        this.create(jobOffer);
        return jobOffer.getId();
    }

    @Override
    public void updateOffer(JobOffer jobOffer) {
        this.edit(jobOffer);
    }

    @Override
    public void deleteOffer(Long idOffer) {
        this.remove(this.find(idOffer));
    }

    @Override
    public List<JobOffer> searchByTitle(String title) {
        List<JobOffer> offers = em.createQuery(
                "select j from JobOffer j where j.title"
                + " LIKE CONCAT('%',:titre,'%')",
                JobOffer.class).setParameter("titre", title).getResultList();
        return this.viewOffersBydate(offers);

    }

    @Override
    public List<JobOffer> searchByLocation(String location) {
List<JobOffer> offers = em.createQuery(
                "select j from JobOffer j where j.city"
                + " LIKE CONCAT('%',:location,'%')",
                JobOffer.class).setParameter("location", location).getResultList();
        return this.viewOffersBydate(offers);
    }

    @Override
    public List<JobOffer> searchByDate(Date date) {
        List<JobOffer> offers = null;
        try {
            offers = em.createQuery("select j from JobOffer j where j.dateOfCreation "
                + "= :givenDate", JobOffer.class).setParameter(""
                        + "givenDate", date).getResultList();
            
        } catch (NoResultException nre) {
        }
        return offers;
    }

    @Override
    public List<JobOffer> searchByExpertise(ExpertiseLevel expLevel) {
        List<JobOffer> offers = em.createQuery("select j from JobOffer j where"
                + " j.expertiseLevel = :niveau").setParameter("niveau",
                        expLevel).getResultList();
        return this.viewOffersBydate(offers);

    }

    @Override
    public List<JobOffer> viewOffersByUserSkill(List<JobOffer> offers, Long idUser) {

        List<JobOffer> userSkillOffers = new ArrayList<>();
        Professional user = em.find(Professional.class, idUser);

        Set<Skill> userSkills = new HashSet<>();

        userSkills = user.getSkills();

        for (Skill skill : userSkills) {
            for (JobOffer j : offers) {
                if (j.getSkills().contains(skill)
                        && !userSkillOffers.contains(j)) {
                    userSkillOffers.add(j);

                }
            }
        }

        return userSkillOffers;

    }

    @Override
    public List<JobOffer> viewAllOffers() {
        List<JobOffer> offers = null;
        OfferStatus status = OfferStatus.ARCHIVED;
        try {
            offers = em.createQuery("select o from JobOffer o where o.offerStatus!= :status",
                    JobOffer.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (NoResultException nre) {
        }
        return offers;
    }

    @Override
    public List<JobOffer> viewOffersBydate(List<JobOffer> offers) {
        List<JobOffer> orderedListByDate = new ArrayList<>();
        List<Date> dates = new ArrayList<>();

        for (JobOffer j : offers) {
            dates.add(j.getDateOfCreation());
        }
        Collections.sort(dates, new Comparator<Date>() {

            //Overiding the compare fonction so as to compare date types
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        for (Date d : dates) {
            for (JobOffer j : offers) {
                if (d.equals(j.getDateOfCreation())) {
                    orderedListByDate.add(j);
                } else {
                }
            }
        }

        return orderedListByDate;
    }

    @Override
    public JobOffer searchJobOfferByTitle(String title) {
        
        JobOffer job = null;
        try {
            job = em.createQuery("SELECT j from JobOffer j where j.title= :titre",
                JobOffer.class).setParameter("titre", title)
                .getSingleResult();
        } catch (Exception e) {
        }
        return job;
    }

    @Override
    public List<JobOffer> generalSearch(String title, String location, ExpertiseLevel expLevel) {
        List<JobOffer> offers = null;
                
                try{ 
                    offers = em.createQuery(
                "select j from JobOffer j where j.title"
                + " LIKE CONCAT('%',:titre,'%') or "
                        + "j.city LIKE CONCAT('%',:locate,'%') or "
                        + "j.expertiseLevel = :niveau ",
                JobOffer.class).setParameter("titre", title)
                            .setParameter("locate", location)
                            .setParameter("niveau", expLevel)
                            .getResultList();
                }catch(NoResultException nre){
                
                }
            return offers;    
    }


}
