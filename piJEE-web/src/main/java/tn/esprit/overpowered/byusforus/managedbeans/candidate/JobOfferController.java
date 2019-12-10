/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.candidate;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote;

/**
 *
 */
@ManagedBean
@SessionScoped
public class JobOfferController implements Serializable {

    private List<JobOffer> jobOffers;
    @EJB
    private JobOfferFacadeRemote jobFacade;
    
    public String jobofferList()
    {
        jobOffers = jobFacade.findAll();
        return "/views/candidate/jobOfferView?faces-redirect=true";
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public JobOfferFacadeRemote getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobOfferFacadeRemote jobFacade) {
        this.jobFacade = jobFacade;
    }

    
}
