/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.quiz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;

/**
 *
 */
@Entity
public class QuizTry implements Serializable {

    private static final long serialVersionUID = 25L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuizTry;
    @ManyToOne
    private Quiz quiz;
    private float percentage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    private String recording;
    @OneToMany
    List<Answer> answers;
    @OneToOne
    private Candidate candidate;

    public QuizTry(Candidate candidate) {
        this.candidate = candidate;
        startDate = new Date();
        recording = "QUIZ_TRY_" + candidate.getId() + ".ts";
    }

    public QuizTry() {
        startDate = new Date();
        recording = "QUIZ_TRY_" + new Random().nextInt() + ".ts";
    }

    public Long getIdQuizTry() {
        return idQuizTry;
    }

    public void setIdQuizTry(Long idQuizTry) {
        this.idQuizTry = idQuizTry;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuizTry != null ? idQuizTry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idQuizTry fields are not set
        if (!(object instanceof QuizTry)) {
            return false;
        }
        QuizTry other = (QuizTry) object;
        if ((this.idQuizTry == null && other.idQuizTry != null) || (this.idQuizTry != null && !this.idQuizTry.equals(other.idQuizTry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuizTry{" + "idQuizTry=" + idQuizTry + ", quiz=" + quiz + ", percentage=" + percentage + ", startDate=" + startDate + ", finishDate=" + finishDate + ", recording=" + recording + ", answers=" + answers + ", candidate=" + candidate + '}';
    }

}
