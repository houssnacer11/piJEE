/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Notif;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.Professional;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.entities.util.OfferStatus;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 * @author pc
 */
@Stateless
public class HRManagerFacade extends AbstractFacade<HRManager> implements HRManagerFacadeLocal, HRManagerFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HRManagerFacade() {
        super(HRManager.class);
    }

    @Override
    public void createOffer(Long idManager, JobOffer offer) {
        HRManager hrm = em.find(HRManager.class, idManager);
        offer.setCompany(hrm.getCompanyProfile());
        offer.sethRManager(hrm);
        em.persist(offer);
        CompanyProfile comp = hrm.getCompanyProfile();
        if (comp.getSubscribers() != null) {
            for (Professional cdt : comp.getSubscribers()) {
                em.persist(new Notif(offer.getTitle(), cdt.getId(), offer.getId()));
            }
        }

    }

    @Override
    public boolean approveJobOffer(String titleJobOffer) {
        JobOffer jobOffer = em.createQuery("select j from JobOffer j where "
                + "j.title= :titre", JobOffer.class)
                .setParameter("titre", titleJobOffer)
                .getSingleResult();
        jobOffer.setOfferStatus(OfferStatus.AVAILABLE);

        try {
            if (MailSender.sendMail("smtp.gmail.com", "587", "toussaint.kebou@gmail.com",
                    "toussaint.kebou@gmail.com",
                    "Laurel@@2019", jobOffer.getpManager().getEmail(),
                    "RESPONSE TO JOB OFFER REQUEST",
                    "Your request has been granted and Enterprise Subscribers have"
                    + " been notified")) {
                CompanyProfile comp = jobOffer.getCompany();
                if (comp.getSubscribers() != null) {
                    for (Professional cdt : comp.getSubscribers()) {
                        em.persist(new Notif(jobOffer.getTitle(), cdt.getId(), jobOffer.getId()));
                    }
                }

                return true;
            }

        } catch (MessagingException ex) {
            Logger.getLogger(HRManagerFacade.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public boolean declineJobOffer(String titleJobOffer, String motif) {
        JobOffer jobOffer = em.createQuery("select j from JobOffer j where "
                + "j.title= :titre", JobOffer.class)
                .setParameter("titre", titleJobOffer)
                .getSingleResult();
        jobOffer.setOfferStatus(OfferStatus.REJECTED);
        try {
            if (MailSender.sendMail("smtp.gmail.com", "587", "toussaint.kebou@gmail.com",
                    "toussaint.kebou@gmail.com",
                    "Laurel@@2019", jobOffer.getpManager().getEmail(),
                    "RESPONSE TO JOB OFFER REQUEST",
                    "Your request has been rejected:"
                    + " been notified")) {
                return true;
            }

        } catch (MessagingException ex) {
            Logger.getLogger(HRManagerFacade.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }
    
        @Override
    public void deleteOffer(Long idOffer) {
        JobOffer offer = em.find(JobOffer.class,idOffer);
        em.remove(offer);
    }

    @Override
    public void archiveOffer(Long idOffer) {
        JobOffer offer = em.find(JobOffer.class,idOffer);
        offer.setOfferStatus(OfferStatus.ARCHIVED);
    }

    @Override
    public Long createHRManager(HRManager hrManger) {
        em.persist(hrManger);
        return hrManger.getId();
    }

    @Override
    public boolean affecterHRtoCompany(Long hrManagerId, String compName) {
        HRManager hrm = em.find(HRManager.class, hrManagerId);
        CompanyProfile comp = null;
        try {
            comp = em.createQuery("select c from CompanyProfile c "
                    + "where c.name "
                    + "= :compname", CompanyProfile.class).setParameter("compname", compName).getSingleResult();
            hrm.setCompanyProfile(comp);
            comp.setCompanyHRManager(hrm);
        } catch (NoResultException e) {
        }
        return comp != null;

    }

    @Override
    public List<Notif> retrieveUserNofifs(Long userId) {
        List<Notif> notifs = null;

        try {
            notifs = em.createQuery("SELECT N FROM Notif N where N.cdtId= :id", Notif.class)
                    .setParameter("id", userId)
                    .getResultList();
        } catch (NoResultException nre) {
        }
        return notifs;
    }



}
