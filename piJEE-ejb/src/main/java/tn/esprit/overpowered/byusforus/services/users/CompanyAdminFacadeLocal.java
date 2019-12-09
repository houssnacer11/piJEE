/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;

/**
 *
 * @author pc
 */
@Local
public interface CompanyAdminFacadeLocal {

    void create(CompanyAdmin companyAdmin);

    void edit(CompanyAdmin companyAdmin);

    void remove(CompanyAdmin companyAdmin);

    CompanyAdmin find(Object id);

    List<CompanyAdmin> findAll();

    List<CompanyAdmin> findRange(int[] range);

    int count();
    
}
