/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.quiz;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

/**
 *
 */
@Entity
public class Quiz implements Serializable {

    private static final long serialVersionUID = 24L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String details;
    private float percentageToPass;
    private int duration;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Question> questions;
    @ManyToOne
    private JobOffer jobOffer;
//    @OneToMany
//    List<QuizTry> quizTries;

    public Quiz() {
        
    }

    public Quiz(String name, String details, float percentageToPass) {
        this.name = name;
        this.details = details;
        this.percentageToPass = percentageToPass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getPercentageToPass() {
        return percentageToPass;
    }

    public void setPercentageToPass(float percentageToPass) {
        this.percentageToPass = percentageToPass;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

//    public List<QuizTry> getQuizTries() {
//        return quizTries;
//    }
//
//    public void setQuizTries(List<QuizTry> quizTries) {
//        this.quizTries = quizTries;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", name=" + name + ", details=" + details + ", percentageToPass=" + percentageToPass + ", questions=" + questions + '}';
    }

}
