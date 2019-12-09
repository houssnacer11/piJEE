/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;
   
import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Notif;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;

/**
 *
 * @author pc
 */
@Remote
public interface HRManagerFacadeRemote {

    void create(HRManager hRManager);

    void edit(HRManager hRManager);

    void remove(HRManager hRManager);

    HRManager find(Object id);

    List<HRManager> findAll();

    List<HRManager> findRange(int[] range);

    int count();
    
    //OFFER
    public boolean approveJobOffer(String titleJobOffer);
    
    public boolean declineJobOffer(String titleJobOffer, String motif);
    
    public void createOffer(Long idManager,JobOffer offer);
    
    public void deleteOffer(Long idOffer);
    public void archiveOffer(Long idOffer);
    
    //HR
    public Long createHRManager(HRManager hrManger);
    
    public boolean affecterHRtoCompany(Long hrManagerId, String compName);
    
    //Notification service
    public List<Notif> retrieveUserNofifs(Long userId);
   

}
