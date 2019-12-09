
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Professional;

/**
 *
 * @author pc
 */
@Stateless
public class CompanyProfileFacade extends AbstractFacade<CompanyProfile> implements CompanyProfileFacadeLocal,CompanyProfileFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyProfileFacade() {
        super(CompanyProfile.class);
    }
    @Override
    public List<Professional> getSubscribersList(Long idAdmin){
    CompanyAdmin compAdmin = em.find(CompanyAdmin.class, idAdmin);
    Long idComp = compAdmin.getCompanyProfile().getId();
    CompanyProfile compProf = em.find(CompanyProfile.class, idComp);
        List<Professional> listCdt = compProf.getSubscribers();
        List<Professional> subscribersList = new ArrayList<>();
        for (Professional cdtt : listCdt) {
            subscribersList.add(em.find(Candidate.class, cdtt.getId()));
        }
        return subscribersList;
        
    }

    @Override
    public Long numberOfEmployees(Long idComp) {
        return em.createQuery("select count(e) from Employee e where e.company.id= :id", Long.class)
                .setParameter("id", idComp)
                .getSingleResult();
    }

    @Override
    public Long numberOfProjectManagers(Long idComp) {
         return em.createQuery("select count(p) from CompanyProfile e join e.projectManagers p where e.id= :id", Long.class)
                .setParameter("id", idComp)
                .getSingleResult();
    }
    
}
