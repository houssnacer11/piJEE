/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Workshop;
import tn.esprit.overpowered.byusforus.entities.posting.Post;

/**
 *
 * @author pc
 */
@Entity
public class CompanyProfile implements Serializable {

    private static final long serialVersionUID = 30L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "COMPANY_ID")
    private Long id;

    private String name;
    private String picName;
    private int numViews;
    private String summary;
    private String sectorOfActivity;
    private String website;
    private String companySize;
    private int dateOfCreation;
    
    @OneToMany
    private Set<Post> posts;

    @OneToOne(mappedBy = "companyProfile", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private CompanyAdmin companyAdmin;

    @OneToOne(mappedBy = "companyProfile", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private HRManager companyHRManager;

    @OneToMany(mappedBy = "company")
    private List<JobOffer> listOfOffers;

    @ManyToMany(mappedBy = "subscribedCompanies",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Professional> subscribers;

    @OneToMany(mappedBy = "company", cascade = {ALL}, fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(mappedBy = "companyProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<ProjectManager> projectManagers;

    @OneToMany(mappedBy = "company", cascade = {ALL}, fetch = FetchType.LAZY)
    private List<Event> events;

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY)
    private List<Workshop> workshops;

    public CompanyProfile() {
        this.name="";
        this.numViews=0;
        this.sectorOfActivity="";
        this.summary="";
        this.website="";
        this.companyAdmin= new CompanyAdmin();
        this.companyHRManager = new HRManager();
        this.projectManagers = new ArrayList<>();
        this.employees = new ArrayList<>();
        
    }

    public CompanyProfile(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getNumViews() {
        return numViews;
    }

    public void setNumViews(int numViews) {
        this.numViews = numViews;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSectorOfActivity() {
        return sectorOfActivity;
    }

    public void setSectorOfActivity(String sectorOfActivity) {
        this.sectorOfActivity = sectorOfActivity;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public int getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(int dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    public HRManager getCompanyHRManager() {
        return companyHRManager;
    }

    public void setCompanyHRManager(HRManager companyHRManager) {
        this.companyHRManager = companyHRManager;
    }

    public List<JobOffer> getListOfOffers() {
        return listOfOffers;
    }

    public void setListOfOffers(List<JobOffer> listOfOffers) {
        this.listOfOffers = listOfOffers;
    }

    public List<Professional> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Professional> subscribers) {
        this.subscribers = subscribers;
    }



    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<ProjectManager> getProjectManagers() {
        return projectManagers;
    }

    public void setProjectManagers(List<ProjectManager> projectManagers) {
        this.projectManagers = projectManagers;
    }
    
    

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<Workshop> workshops) {
        this.workshops = workshops;
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
        if (!(object instanceof CompanyProfile)) {
            return false;
        }
        CompanyProfile other = (CompanyProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.CompanyProfile[ id=" + id + " ]";
    }

}
