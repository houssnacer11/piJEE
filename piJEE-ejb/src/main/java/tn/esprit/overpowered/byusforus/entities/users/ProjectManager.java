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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "PROJECT_MANAGER")
public class ProjectManager extends Employee implements Serializable {

    private static final long serialVersionUID = 33L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COMP_PM_ID")
    private CompanyProfile companyProfile;

    @OneToMany(mappedBy = "pManager", cascade = {PERSIST, MERGE},fetch = FetchType.EAGER)
    private Set<JobOffer> managerOffers;

    public ProjectManager(String username, String email, String firstName, String lastName, byte[] password) {
        super(username, email, firstName, lastName, password);
    }

    public ProjectManager(String username, String email, String firstName, String lastName) {
        super(username, email, firstName, lastName);
    }

    public ProjectManager() {
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Set<JobOffer> getManagerOffers() {
        return managerOffers;
    }

    public void setManagerOffers(Set<JobOffer> managerOffers) {
        this.managerOffers = managerOffers;
    }

    @Override
    public Set<Skill> getSkills() {
        return skills;
    }

    @Override
    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}

