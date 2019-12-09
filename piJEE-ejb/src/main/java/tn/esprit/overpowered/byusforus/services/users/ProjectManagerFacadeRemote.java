/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;

/**
 *
 * @author pc
 */
@Remote
public interface ProjectManagerFacadeRemote {

    void create(ProjectManager projectManager);

    void edit(ProjectManager projectManager);

    void remove(ProjectManager projectManager);

    ProjectManager find(Object id);

    List<ProjectManager> findAll();

    List<ProjectManager> findRange(int[] range);

    int count();

    public boolean createJobOfferRequest(JobOffer jobOffer, Long idPManager);

    public Long createPManager(ProjectManager pManager);

    public boolean affecterPMtoCompany(Long pManagerId, String compName);
    
        //Company
    public CompanyProfile retrieveCompanyInfo(Long idPR);

}
