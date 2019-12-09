/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Professional;

/**
 *
 * @author pc
 */
@Remote
public interface CompanyProfileFacadeRemote {

    void create(CompanyProfile companyProfile);

    void edit(CompanyProfile companyProfile);

    void remove(CompanyProfile companyProfile);

    CompanyProfile find(Object id);

    List<CompanyProfile> findAll();

    List<CompanyProfile> findRange(int[] range);
    
    List<Professional> getSubscribersList(Long idAdmin);

    int count();
    
    public Long numberOfEmployees(Long idComp);
    public Long numberOfProjectManagers(Long idComp);
        
}

