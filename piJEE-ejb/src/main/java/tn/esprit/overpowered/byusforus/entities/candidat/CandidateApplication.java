/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.candidat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;

/**
 *
 * @author Yassine
 */
@Entity
public class CandidateApplication implements Serializable {

    private static final long serialVersionUID = 8L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Candidate candidate;
    private String motivationLetter;
    private String resume;
    @Enumerated(EnumType.STRING)
    private JobApplicationState jobApplicationState;
    private String additionalInfo;
    @ManyToOne
    private JobOffer jobOffer;
    @ManyToMany
    private List<Quiz> quizzesTaken;

    public CandidateApplication() {
        this.quizzesTaken = new ArrayList<>();
        this.jobApplicationState = JobApplicationState.PENDING;
    }

    public CandidateApplication(String resume, JobOffer jobOffer) {
        this.resume = resume;
        this.jobOffer = jobOffer;
        this.jobApplicationState = JobApplicationState.PENDING;
        this.quizzesTaken = new ArrayList<>();
    }

    public CandidateApplication(String motivationLetter, String resume, JobOffer jobOffer) {
        this.motivationLetter = motivationLetter;
        this.resume = resume;
        this.jobOffer = jobOffer;
        this.quizzesTaken = new ArrayList<>();
        this.jobApplicationState = JobApplicationState.PENDING;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getMotivationLetter() {
        return motivationLetter;
    }

    public void setMotivationLetter(String motivationLetter) {
        this.motivationLetter = motivationLetter;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public JobApplicationState getJobApplicationState() {
        return jobApplicationState;
    }

    public void setJobApplicationState(JobApplicationState jobApplicationState) {
        this.jobApplicationState = jobApplicationState;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public List<Quiz> getQuizzesTaken() {
        return quizzesTaken;
    }

    public void setQuizzesTaken(List<Quiz> quizzesTaken) {
        this.quizzesTaken = quizzesTaken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CandidateApplication)) {
            return false;
        }
        CandidateApplication other = (CandidateApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.candidat.NewEntity[ id=" + id + " ]";
    }

}
