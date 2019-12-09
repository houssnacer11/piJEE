/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;

/**
 *
 * @author pc
 */
@Local
public interface HRManagerFacadeLocal {

    void create(HRManager hRManager);

    void edit(HRManager hRManager);

    void remove(HRManager hRManager);

    HRManager find(Object id);

    List<HRManager> findAll();

    List<HRManager> findRange(int[] range);

    int count();
    
}
