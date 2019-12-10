/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.apache.commons.lang3.RandomStringUtils;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Professional;
import tn.esprit.overpowered.byusforus.util.MailSender;

/**
 */
@Stateless
public class CandidateFacade extends AbstractFacade<Candidate>
        implements CandidateFacadeRemote, CandidateFacadeLocal {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CandidateFacade() {
        super(Candidate.class);
    }

    @Override
    public String createCandidate(Candidate candidate) {
        em.persist(candidate);
        return candidate.getUsername();
    }

    @Override
    public List<Candidate> afficherCandidats() {
        List<Candidate> Lcandidats = em.createQuery("select p from Candidate p",
                Candidate.class).getResultList();
        return Lcandidats;
    }

    @Override
    public List<Candidate> searchByName(String name) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE "
                + "c.firstName  LIKE CONCAT('%',:name,'%')", Candidate.class)
                .setParameter("name", name)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByLastname(String lastname) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE "
                + "c.lastName  LIKE CONCAT('%',:lastName,'%')", Candidate.class)
                .setParameter("lastName", lastname)
                .getResultList();
        return cdt;
    }

    @Override
    public List<Candidate> searchByPosition(String position) {
        List<Candidate> cdts = this.findAll();
        for (Candidate cdt : cdts) {
            for (String exp : cdt.getExperiences()) {
                if (exp.contains(position)) {
                    cdts.add(cdt);
                }
            }
        }
        return cdts;

    }

    @Override
    public CompanyProfile searchCompany(String companyName) {
        CompanyProfile comp = (CompanyProfile) em.createQuery(
                "SELECT c FROM CompanyProfile c WHERE c.name LIKE :companyName")
                .setParameter("name", companyName)
                .setMaxResults(1)
                .getResultList();
        return comp;
    }

    /*
    @Override
    public void affecterExperienceCandidate(Long expId, Long candidateId) {
        Experience exp = em.find(Experience.class, expId);
        Candidate emp = em.find(Candidate.class, candidateId);
        if (exp != null && emp != null) {
            emp.getExperiences().add(exp);
            exp.setProfessional(emp);
        } else {
            System.out.println("Either candidate or Experience doent exist !");
        }
    }
     */
    @Override
    public List<JobOffer> customJobOfferList(Long candidateId) {
        List<JobOffer> jobList = em.createQuery("SELECT j from JobOffer j", JobOffer.class).getResultList();
        Candidate cdt = em.find(Candidate.class, candidateId);
        Set<String> exp = cdt.getExperiences();

        //System.out.println("theexperienceis: "+exp.get(1));
        List<JobOffer> customJobs = new ArrayList<>();
        for (JobOffer j : jobList) {
            for(String s: exp)
            {
                System.out.println("the experience is: "+s);
            if (j.getTitle().toLowerCase().contains(s.toLowerCase()) & s.length()!=0) {
                System.out.println("the title is: "+j.getTitle());
                customJobs.add(j);
            }
            }
        }
        return customJobs;
    }

    @Override
    public List<CompanyProfile> subscriptionList(Long candidateId) {
        Candidate cdt = this.find(candidateId);
        return cdt.getSubscribedCompanies();
    }

    //ACCOUNT CONFIRMATION CREATION
    @Override
    public String accountCreationConfirmation(String email) {
        /*       int length = 5;
    boolean useLetters = true;
    boolean useNumbers = false;
    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);*/
        int code = 10000 + new Random().nextInt(90000);
        System.out.println("this is the code " + code);
        try {
            MailSender.sendMail("smtp.gmail.com", "587", "pidevnoreply@gmail.com",
                    "pidevnoreply@gmail.com", "pidevpidev", email,
                    "Account creation Confirmation Mail",
                    "If you are receiving this Email then you are one step away from"
                    + " joining the BYUSFORUS group thanks you for your trust"
                    + " Confirm registration with following code "
                    + "<b>" + code + "</b>"
                    + "  Enjoy your stay on our platform");

            return Integer.toString(code);
        } catch (MessagingException ex) {
            Logger.getLogger(CandidateFacade.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("mailing failure");
        }

        return "mailing system down";

    }

    /*
    @Override
    public void affecterCursusCandidate(Long candidateId, Long cursusId) {
        Candidate cdt = em.find(Candidate.class, candidateId);
        Cursus cur = em.find(Cursus.class, cursusId);
        cdt.getCursus().add(cur);
        cur.setProfessionalCursus(cdt);
    }
     */
    @Override
    public String recommend(Long candidateId) {
        Candidate cdt = em.find(Candidate.class, candidateId);
        cdt.setRecommendations(cdt.getRecommendations() + 1);
        return "OK";

    }

    @Override
    public List<Candidate> findAllCandidate() {
        List<Candidate> cdtList = em.createQuery("select c from "
                + "Candidate c", Candidate.class).getResultList();
        return cdtList;
    }

    @Override
    public int incrementVisits(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        cdt.setVisits(cdt.getVisits() + 1);
        return cdt.getVisits();
    }

    @Override
    public List<Candidate> searchByEmail(String email) {
        List<Candidate> cdt = em.createQuery(
                "SELECT c FROM Candidate c WHERE c.email LIKE "
                + "CONCAT('%',:email,'%')", Candidate.class)
                .setParameter("email", email)
                .getResultList();
        return cdt;
    }

    @Override
    public String addContact(Long currentCdtId, Long contactId) {
        Candidate currentCdt = em.find(Candidate.class, currentCdtId);
        Candidate contact = em.find(Candidate.class, contactId);
        if (!currentCdt.getContacts().contains(contact)) {
            currentCdt.getContacts().add(contact);
            contact.getContacts().add(currentCdt);
            return "Contact Added";
        } else {
            return "Already Friends";
        }
    }

    /*
    @Override
    public boolean checkContacts(Long cdtId, Candidate cdt) {
        Candidate cdtt = em.find(Candidate.class, cdtId);
        return cdtt.getContacts().contains(cdt);
    }
     */
    @Override
    public List<Candidate> friendsList(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        List<Candidate> listCdt = cdt.getContacts();
        List<Candidate> contactsList = new ArrayList<>();
        for (Candidate cdtt : listCdt) {
            contactsList.add(em.find(Candidate.class, cdtt.getId()));
        }
        return contactsList;

        /*List<Long> idList = em.createQuery("SELECT c.contacts_id from"
                + " contacts c where c.Candidate_id = :cdtId",Long.class)
                .setParameter("cdtId", cdtId)
                .getResultList();
        List<Candidate> contacts = new ArrayList<>() ;
        for(Long id: idList)
        {
            contacts.add(em.find(Candidate.class, id));
        }
        return contacts;
         */
    }

    /*
    @Override
    public Experience getCandidateExperience(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        List<Experience> expTemp = cdt.getExperiences();
        if(expTemp.isEmpty())
        {
            return null;
        }
        else 
        {
        Long expId = expTemp.get(0).getId();
        return em.find(Experience.class, expId);
        }

    }
     */
    @Override
    public String subscribe(Long companyId, Long candidateId) {
        String info;
        Candidate cdt = em.find(Candidate.class,candidateId);
        CompanyProfile comp = em.find(CompanyProfile.class, companyId);
            List<Professional> subscribers = new ArrayList<>();
            List<CompanyProfile> subscribedCompanies = new ArrayList<>();
            if(cdt.getSubscribedCompanies()==null){
                subscribedCompanies.add(comp);
                cdt.setSubscribedCompanies(subscribedCompanies);
                    if(comp.getSubscribers()==null){
                        subscribers.add(cdt);
                        comp.setSubscribers(subscribers);
                        info = "Subscribed";
                    }
                    else{
                        comp.getSubscribers().add(cdt);
                        info = "Subscribed";
                    }
            }
            else {
                if(cdt.getSubscribedCompanies().contains(comp))
                    info="Already Subscribed";
                else{
                cdt.getSubscribedCompanies().add(comp);
                info = "Subscribed";
                }
                
            }
            return info;
    }


    /*
    @Override
    public Cursus getCandidateCursus(Long cdtId) {
         Candidate cdt = em.find(Candidate.class, cdtId);
        List<Cursus> expTemp = cdt.getCursus();
        if(expTemp.isEmpty())
        {
            return null;
        }
        else
        {
        Long expId = expTemp.get(0).getId();
        return em.find(Cursus.class, expId);
        }
        
    }

    @Override
    public String subscribe(Long companyId, Long candidateId) {
         Candidate currentCdt = em.find(Candidate.class, candidateId);
        CompanyProfile comp = em.find(CompanyProfile.class, companyId);
        if (!currentCdt.getContacts().contains(comp)) {
            currentCdt.getSubscribedCompanies().add(comp);
            comp.getSubscribers().add(currentCdt);
            return "Contact Added";
        } else {
            return "Already Friends";
        }
    }
     */
    @Override
    public Candidate findCandidate(Long cdtId) {
        Candidate cdt = new Candidate();
        cdt = em.find(Candidate.class, cdtId);
        return cdt;
    }

    @Override
    public String sendFriendRequest(Long currentId, Long friendId) {
        Candidate currentCdt = em.find(Candidate.class, currentId);
        Candidate friend = em.find(Candidate.class, friendId);
       if (currentCdt.getFriendRequests().contains(friend)) {
          return "Exist";
        } else {
            currentCdt.getFriendRequests().add(friend);
            friend.getFriendRequests().add(currentCdt);
            return "OK";
        }
    }

    @Override
    public String acceptFriendRequest(Long currentId, Long friendId) {
        this.addContact(currentId, friendId);
        Candidate currentCdt = em.find(Candidate.class, currentId);
        Candidate friend = em.find(Candidate.class, friendId);
        friend.getFriendRequests().remove(currentCdt);
        return "OK";
    }

    @Override
    public String rejectFriendRequest(Long currentId, Long friendId) {
        Candidate currentCdt = em.find(Candidate.class, currentId);
        Candidate friend = em.find(Candidate.class, friendId);
         friend.getFriendRequests().remove(currentCdt);

        return "OK";
    }

    @Override
    public List<Candidate> friendRequestList(Long currentId) {
        Candidate cdt = em.find(Candidate.class, currentId);
        Set<Candidate> listCdt = cdt.getPendingRequests();
        List<Candidate> friendRequests = new ArrayList<>();
        for (Candidate cdtt : listCdt) {
            friendRequests.add(em.find(Candidate.class, cdtt.getId()));
        }
        return friendRequests;
    }

    @Override
    public List<Candidate> pendingList(Long currentId) {
        Candidate cdt = em.find(Candidate.class, currentId);
        Set<Candidate> listCdt = cdt.getPendingRequests();
        List<Candidate> pendingRequests = new ArrayList<>();
        for (Candidate cdtt : listCdt) {
            pendingRequests.add(em.find(Candidate.class, cdtt.getId()));
        }
        return pendingRequests;
    }

    @Override
    public String deleteFriend(Long currentCdtId, Long contactId) {
        Candidate currentCdt = em.find(Candidate.class, currentCdtId);
        Candidate contact = em.find(Candidate.class, contactId);
        
            currentCdt.getContacts().remove(contact);
            contact.getContacts().remove(currentCdt);
            return "Contact deleted";
    }

    @Override
    public void editCandidate(Candidate cdt) {
        em.merge(cdt);
    }


    @Override
    public List<JobOffer> jobOfferByCompany(Long compId) {
       CompanyProfile comp = em.find(CompanyProfile.class, compId);
       List<JobOffer> jobs = new ArrayList<>();
       for(JobOffer j: comp.getListOfOffers())
       {
           JobOffer job = em.createQuery("select j from "
                   + "JobOffer j where j.title = :title",JobOffer.class)
                   .setParameter("title", j.getTitle())
                   .getSingleResult();
           jobs.add(job);
       }
       return jobs;
    }

    @Override
    public int friendRequestNumber(Long cdtId) {
        Candidate cdt = em.find(Candidate.class, cdtId);
        Set<Candidate> listCdt = cdt.getPendingRequests();
        int num = listCdt.size();
        return num;
    }

    @Override
    public Set<String> getCandidateExperience(Long cdtId) {
       Candidate cdt = em.find(Candidate.class, cdtId);
        Set<String> experiences = cdt.getExperiences();
        return experiences;
    }

}
