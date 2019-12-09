/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 * @author pc
 */
@Entity
@DiscriminatorValue(value = "COMPANY_ADMIN")
public class CompanyAdmin extends Employee implements Serializable {

    private static final long serialVersionUID = 29L;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COMP_ADMIN_ID")
    private CompanyProfile companyProfile;

    public CompanyAdmin() {
    }

    public CompanyAdmin(String username, String email, String firstName, String lastName, byte[] password) {
        super(username, email, firstName, lastName, password);
    }

    public CompanyAdmin(String username, String email, String firstName, String lastName) {
        super(username, email, firstName, lastName);
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
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
