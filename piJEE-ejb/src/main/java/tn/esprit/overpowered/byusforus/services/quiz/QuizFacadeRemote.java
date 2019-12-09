/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;

/**
 *
 * @author Yassine
 */
@Remote
public interface QuizFacadeRemote {

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
