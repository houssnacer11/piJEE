/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import tn.esprit.overpowered.byusforus.entities.quiz.QuestionType;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.Skill;

/**
 *
 */
@ManagedBean
@javax.faces.bean.SessionScoped
public class Enums implements Serializable {

    /**
     * Creates a new instance of Enums
     */
    public Enums() {

    }

    public QuestionType[] getQuestionTypes() {
        return QuestionType.values();
    }

    public Set<Skill> getSkills() {
        Set<Skill> skillSet = new HashSet<>();
        skillSet.addAll(Arrays.asList(Skill.values()));
        return skillSet;
    }

    public ExpertiseLevel[] getExpertiseLevels() {
        return ExpertiseLevel.values();
    }

}
