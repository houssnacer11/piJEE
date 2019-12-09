/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;

/**
 *
 * @author Yassine
 */
@Remote
public interface QuizTryFacadeRemote {

    void create(QuizTry quizTry);

    void edit(QuizTry quizTry);

    void remove(QuizTry quizTry);

    QuizTry find(Object id);

    List<QuizTry> findAll();

    List<QuizTry> findRange(int[] range);

    int count();
}
