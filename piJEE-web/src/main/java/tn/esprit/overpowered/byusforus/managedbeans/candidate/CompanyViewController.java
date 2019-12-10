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
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.services.users.CompanyProfileFacadeLocal;

/**
 *
 */
@ManagedBean
@SessionScoped
public class CompanyViewController implements Serializable {
    private CompanyProfile selectedCompany;
    private List<CompanyProfile> companies;
    
    @EJB
    private CompanyProfileFacadeLocal compFacade;
    
    public String companyDetails()
    {
        return "/views/candidate/companyDetails?faces-redirect=true";
    }
    
    public String listComp()
    {
        companies = compFacade.findAll();
        return "/views/candidate/companiesView?faces-redirect=true";
    }

    public CompanyProfile getSelectedCompanies() {
        return selectedCompany;
    }

    public void setSelectedCompanies(CompanyProfile selectedCompanies) {
        this.selectedCompany = selectedCompanies;
    }

    public CompanyProfileFacadeLocal getCompFacade() {
        return compFacade;
    }

    public void setCompFacade(CompanyProfileFacadeLocal compFacade) {
        this.compFacade = compFacade;
    }

    public CompanyProfile getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(CompanyProfile selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public List<CompanyProfile> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyProfile> companies) {
        this.companies = companies;
    }
    
}
