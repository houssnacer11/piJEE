
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;

/**
 *
 * @author pc
 */
@Local
public interface CompanyProfileFacadeLocal {

    void create(CompanyProfile companyProfile);

    void edit(CompanyProfile companyProfile);

    void remove(CompanyProfile companyProfile);

    CompanyProfile find(Object id);

    List<CompanyProfile> findAll();

    List<CompanyProfile> findRange(int[] range);

    int count();
    
}

