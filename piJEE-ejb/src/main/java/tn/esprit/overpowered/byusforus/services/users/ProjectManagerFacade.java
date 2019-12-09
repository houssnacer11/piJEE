/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 * @author pc
 */
@Stateless
public class ProjectManagerFacade extends AbstractFacade<ProjectManager> implements ProjectManagerFacadeLocal, ProjectManagerFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectManagerFacade() {
        super(ProjectManager.class);
    }

    @Override
    public boolean createJobOfferRequest(JobOffer jobOffer, Long idPManager) {
        ProjectManager pManager = this.find(idPManager);
        jobOffer.setCompany(pManager.getCompanyProfile());
        jobOffer.setpManager(pManager);
        jobOffer.sethRManager(pManager.getCompanyProfile().getCompanyHRManager());
        em.persist(jobOffer);
                try {
            if (MailSender.sendMail("smtp.gmail.com", "587", "pidevnoreply@gmail.com"
                    , "pidevnoreply@gmail.com","pidevpidev", jobOffer.gethRManager().getEmail(),
                    "JOB OFFER CREATION REQUEST",
                    "This is a job Offer creation request from "
                            + pManager.getUsername() + " of email " + pManager.getEmail()
                    + ":</br> "+ "Tile: "+jobOffer.getTitle()+ "</br> "+
                            "Location: "+jobOffer.getCity() + "</br> "+
                            "CompanyName: " + jobOffer.getCompany().getName()+ " .")) {
                return true;
            }

        } catch (MessagingException ex) {
            Logger.getLogger(HRManagerFacade.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public Long createPManager(ProjectManager pManager) {
        em.persist(pManager);
        return pManager.getId();
    }

    @Override
    public boolean affecterPMtoCompany(Long pManagerId, String compName) {
               ProjectManager pm = em.find(ProjectManager.class, pManagerId);
        CompanyProfile comp = null;
        try {
              comp = em.createQuery("select c from CompanyProfile c "
                + "where c.name "
                + "= :compname",CompanyProfile.class).setParameter("compname", compName).getSingleResult();
        pm.setCompanyProfile(comp);
        comp.getProjectManagers().add(pm);
        } catch (NoResultException e ) {
        }
            return comp != null ;
        
    }

    @Override
    public CompanyProfile retrieveCompanyInfo(Long idPR) {
     ProjectManager prManager = em.find(ProjectManager.class, idPR);
     Long id = prManager.getCompanyProfile().getId();
     CompanyProfile comp = new CompanyProfile("BOBO");
     
        try {
            /*comp= em.createQuery("SELECT C FROM CompanyProfile C,User U where :manager in C.projectManagers",CompanyProfile.class)
                    .setParameter("manager", prManager).getSingleResult();*/
           comp= em.find(CompanyProfile.class,1);
        } catch (NoResultException nre) {
            System.out.println("Unable to get any CompanyProfile for this Project Manager");
        }
     
     return comp;
    }


    
}
