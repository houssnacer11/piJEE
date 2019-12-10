/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Workshop;

/**
 *
 */
@Local
public interface WorkshopFacadeLocal {

    void create(Workshop workshop);

    void edit(Workshop workshop);

    void remove(Workshop workshop);

    Workshop find(Object id);

    List<Workshop> findAll();

    List<Workshop> findRange(int[] range);

    int count();
    
}
