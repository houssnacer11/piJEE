/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.quiz;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Yassine
 */
@Entity
public class Choice implements Serializable {

    private static final long serialVersionUID = 22L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChoice;
    private String choiceText;
    private Boolean isCorrectChoice;
    private float choicePoints;
    @ManyToOne
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Choice() {
        this.isCorrectChoice = false;
        this.choicePoints = 0;
    }

    public Choice(String choiceText, Boolean isCorrectChoice, float choicePoints) {
        this.choiceText = choiceText;
        this.isCorrectChoice = isCorrectChoice;
        this.choicePoints = choicePoints;
    }

    public Long getIdChoice() {
        return idChoice;
    }

    public void setIdChoice(Long idChoice) {
        this.idChoice = idChoice;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public Boolean getIsCorrectChoice() {
        return isCorrectChoice;
    }

    public void setIsCorrectChoice(Boolean isCorrectChoice) {
        this.isCorrectChoice = isCorrectChoice;
    }

    public float getChoicePoints() {
        return choicePoints;
    }

    public void setChoicePoints(float choicePoints) {
        this.choicePoints = choicePoints;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChoice != null ? idChoice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choice)) {
            return false;
        }
        Choice other = (Choice) object;
        if ((this.idChoice == null && other.idChoice != null) || (this.idChoice != null && !this.idChoice.equals(other.idChoice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tn.esprit.overpowered.byusforus.entities.Choice[ id=" + idChoice + " ]";
    }
}
