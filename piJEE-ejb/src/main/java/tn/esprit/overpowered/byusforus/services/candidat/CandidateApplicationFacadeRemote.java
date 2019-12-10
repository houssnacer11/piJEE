/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;

/**
 */
@Remote
public interface CandidateApplicationFacadeRemote {

    void create(CandidateApplication candidateApplication);

    void edit(CandidateApplication candidateApplication);

    void remove(CandidateApplication candidateApplication);

    CandidateApplication find(Object id);

    List<CandidateApplication> findAll();

    List<CandidateApplication> findRange(int[] range);

    int count();

    CandidateApplication getApplicationByCandidateId(Long candidateId, Long jobOfferId);

    void updateCandidateApplication(int id, String additionalInfo, JobApplicationState appState);

    List<CandidateApplication> getCandidateApplicationByJobOFfer(Long jobOfferId);

    List<CandidateApplication> getCandidateApplicationByCdtId(Long cdtid);

    void sendMail(String to, String subject, String body);

    CandidateApplication getCAppByMotivLetter(String motivLetter);

}
