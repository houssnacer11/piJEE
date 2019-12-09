/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;

/**
 *
 * @author Yassine
 */
@Local
public interface QuizFacadeLocal {

    void create(Quiz quiz);

    void edit(Quiz quiz);

    void remove(Quiz quiz);

    Quiz find(Object id);

    List<Quiz> findAll();

    List<Quiz> findRange(int[] range);

    int count();

    Quiz getQuizByJobOfferId(Long jobOffer);

    Quiz getQuizByName(String name);
}
