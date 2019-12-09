/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;

/**
 *
 * @author pc
 */
@Local
public interface ProjectManagerFacadeLocal {

    void create(ProjectManager projectManager);

    void edit(ProjectManager projectManager);

    void remove(ProjectManager projectManager);

    ProjectManager find(Object id);

    List<ProjectManager> findAll();

    List<ProjectManager> findRange(int[] range);

    int count();
    
}
