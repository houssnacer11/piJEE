/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 *
 */
@Stateless
public class CandidateApplicationFacade extends AbstractFacade<CandidateApplication> implements CandidateApplicationFacadeLocal, CandidateApplicationFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateApplicationFacade() {
        super(CandidateApplication.class);
    }

    @Override
    public CandidateApplication getApplicationByCandidateId(Long candidateId, Long jobOfferId) {
        CandidateApplication cdtApp = null;
        try {
            cdtApp = em.createQuery(
                    "SELECT ca FROM CandidateApplication ca WHERE "
                    + "ca.candidate.id  = :cid and ca.jobOffer.id = :jib", CandidateApplication.class)
                    .setParameter("cid", candidateId)
                    .setParameter("jib", jobOfferId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return cdtApp;
    }

    @Override
    public void updateCandidateApplication(int id, String additionalInfo, JobApplicationState appState) {
        int cdtApp = em.createQuery(
                "update CandidateApplication ca set ca.additionalInfo = :adinfo , ca.jobApplicationState = :jas WHERE ca.id = :caid")
                .setParameter("adinfo", additionalInfo)
                .setParameter("jas", appState)
                .setParameter("caid", id)
                .executeUpdate();
    }

    @Override
    public List<CandidateApplication> getCandidateApplicationByJobOFfer(Long jobOfferId) {
        List<CandidateApplication> cdtApp = em.createQuery(
                "SELECT ca FROM CandidateApplication ca WHERE "
                + " ca.jobOffer.id = :jib", CandidateApplication.class)
                .setParameter("jib", jobOfferId)
                .getResultList();
        return cdtApp;
    }

    @Override
    public List<CandidateApplication> getCandidateApplicationByCdtId(Long cdtid) {
        List<CandidateApplication> cdtApp = em.createQuery(
                "SELECT ca FROM CandidateApplication ca WHERE "
                + " ca.candidate.id = :cib", CandidateApplication.class)
                .setParameter("cib", cdtid)
                .getResultList();
        return cdtApp;
    }

    @Override
    public void sendMail(String to, String subject, String body) {
        try {
            MailSender.sendMail("smtp.gmail.com", "587",
                    "pidevnoreply@gmail.com", "pidevnoreply@gmail.com",
                    "pidevpidev", to, subject, body);
        } catch (MessagingException ex) {
            Logger.getLogger(CandidateApplicationFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CandidateApplication getCAppByMotivLetter(String motivLetter) {
        CandidateApplication cdtApp = em.createQuery(
                "SELECT ca FROM CandidateApplication ca WHERE "
                + "ca.motivationLetter = :motiv", CandidateApplication.class)
                .setParameter("motiv", motivLetter)
                .getSingleResult();
        return cdtApp;
    }

}
