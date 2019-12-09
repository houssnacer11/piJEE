/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.entrepriseprofile;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.OfferStatus;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *

 */
@Entity
public class JobOffer implements Serializable {

    private static final long serialVersionUID = 14L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_OFFER_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    @Column(nullable = false)
    private String description;

    private String city;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    @Temporal(TemporalType.DATE)
    private Date dateOfArchive;

    @Enumerated(EnumType.STRING)
    private ExpertiseLevel expertiseLevel;

    @ElementCollection(targetClass = Skill.class)
    @JoinTable(name = "T_JOB_OFFER_Skills")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    @Column(nullable = false)
    private Integer peopleNeeded;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "T_COMP_JOB", joinColumns = {
        @JoinColumn(name = "COMPANY_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "JOB_OFFER_ID")})
    private CompanyProfile company;

    @ManyToOne(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HRManager hRManager;

    @ManyToMany(mappedBy = "registeredOffers",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidate> registeredCandidates;

    @ManyToOne
    @JoinTable(name = "PROJECT_MANAGER_OFFERS")
    private ProjectManager pManager;

    public JobOffer() {
        this.dateOfCreation = new Date();
        this.offerStatus = OfferStatus.PENDING;
        this.skills = new HashSet<>();
        this.title = "";
        this.city = "";
        this.description = "";
        this.expertiseLevel = ExpertiseLevel.JUNIOR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Date getDateOfArchive() {
        return dateOfArchive;
    }

    public void setDateOfArchive(Date dateOfArchive) {
        this.dateOfArchive = dateOfArchive;
    }

    public ExpertiseLevel getExpertiseLevel() {
        return expertiseLevel;
    }

    public void setExpertiseLevel(ExpertiseLevel expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Integer getPeopleNeeded() {
        return peopleNeeded;
    }

    public void setPeopleNeeded(Integer peopleNeeded) {
        this.peopleNeeded = peopleNeeded;
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
    }

    public HRManager gethRManager() {
        return hRManager;
    }

    public void sethRManager(HRManager hRManager) {
        this.hRManager = hRManager;
    }

    public ProjectManager getpManager() {
        return pManager;
    }

    public void setpManager(ProjectManager pManager) {
        this.pManager = pManager;
    }

    public List<Candidate> getRegisteredCandidates() {
        return registeredCandidates;
    }

    public void setRegisteredCandidates(List<Candidate> registeredCandidates) {
        this.registeredCandidates = registeredCandidates;

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobOffer)) {
            return false;
        }
        JobOffer other = (JobOffer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.JobOffer[ id=" + id + " ]";
    }

}
