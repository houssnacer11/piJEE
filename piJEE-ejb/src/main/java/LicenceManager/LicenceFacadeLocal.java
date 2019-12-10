/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LicenceManager;

import Licenses.Licence;
import java.util.List;
import javax.ejb.Local;

/**
 *
 */
@Local
public interface LicenceFacadeLocal {

    void create(Licence licence);

    void edit(Licence licence);

    void remove(Licence licence);

    Licence find(Object id);

    List<Licence> findAll();

    List<Licence> findRange(int[] range);

    int count();
    
    public boolean verifyLicenceID(Long licenceID);
    
    public boolean verifyUser(String userPass);
    
    public boolean verifyLicenceInfo(Licence licence);
    
    public Long createLicence(Licence licence);
    
}
