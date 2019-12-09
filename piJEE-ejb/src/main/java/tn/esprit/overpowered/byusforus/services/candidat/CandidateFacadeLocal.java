/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;

/**
 *
 * @author EliteBook
 */
@Local
public interface CandidateFacadeLocal {

      void create(Candidate candidate);

    void edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    List<Candidate> findAll();

    List<Candidate> findRange(int[] range);

    int count();

    public List<Candidate> searchByName(String name);

    public List<Candidate> searchByLastname(String lastname);

    public List<Candidate> searchByPosition(String position);

    public String addContact(Long currendCdtId, Long contactId);

    public CompanyProfile searchCompany(String companyName);

    public String subscribe(Long companyId, Long candidateId);

    public List<JobOffer> customJobOfferList(Long candidateId);

    public List<CompanyProfile> subscriptionList(Long candidateId);

    public String accountCreationConfirmation(String email);

    public String createCandidate(Candidate candidate);

    public String recommend(Long candidateId);

    //Cursus
    // public void affecterCursusCandidate(Long candidateId, Long cursusId);
    //CurriculumVitae
    /*
    public Long createCurriculumVitae(CurriculumVitae curriculumVitae);
    
    public void deleteCurriculumVitae(Long curriculumVitaeId);
    
    public Long updateCurriculumVitae(CurriculumVitae curriculumVitae);
    
    public Long findCurriculumVitae(Long curriculumVitaeId);
     */
    //Experience
    //public void affecterExperienceCandidate(Long expId,  Long candidateId);
    //Candidate List display
    //public ObservableList<Candidate> getObservableCandidate();
    public List<Candidate> findAllCandidate();

    public int incrementVisits(Long cdtId);

    public List<Candidate> searchByEmail(String email);

    public List<Candidate> friendsList(Long cdtId);
    //  public boolean checkContacts(Long cdtId, Candidate cdt );

    public List<Candidate> afficherCandidats();
    
    public String sendFriendRequest(Long currentId, Long friendId);
    
    public String acceptFriendRequest(Long currentId, Long friendId);
    
    public String rejectFriendRequest(Long currentId, Long friendId);
    
    public List<Candidate> friendRequestList(Long currentId);
    
    public List<Candidate> pendingList(Long currentId);
    
    public String deleteFriend(Long cdtId, Long friendId);
    
    public void editCandidate(Candidate cdt);
     
    public Set<String> getCandidateExperience(Long cdtId);
    
    public List<JobOffer> jobOfferByCompany(Long compId);
    
    public int friendRequestNumber(Long cdtId);
    
}
