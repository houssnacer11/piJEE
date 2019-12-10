/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 */
@Entity
@DiscriminatorValue(value = "HUMAN_RESOURCES_MANAGER")
public class HRManager extends Employee implements Serializable {

    private static final long serialVersionUID = 32L;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COMP_HR_ID")
    private CompanyProfile companyProfile;

    @OneToMany(mappedBy = "hRManager", cascade = {PERSIST, MERGE},fetch = FetchType.EAGER)
    private Set<JobOffer> jobOffers;

    public HRManager(String username, String email, String firstName, String lastName, byte[] password) {
        super(username, email, firstName, lastName, password);
    }

    public HRManager(String username, String email, String firstName, String lastName) {
        super(username, email, firstName, lastName);
    }

    public HRManager() {
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Set<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(Set<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
    }

}

