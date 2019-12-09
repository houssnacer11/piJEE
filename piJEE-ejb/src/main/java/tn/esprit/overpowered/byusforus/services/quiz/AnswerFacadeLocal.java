/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.quiz.Answer;

/**
 *
 * @author Yassine
 */
@Local
public interface AnswerFacadeLocal {

    void create(Answer answer);

    void edit(Answer answer);

    void remove(Answer answer);

    Answer find(Object id);

    List<Answer> findAll();

    List<Answer> findRange(int[] range);

    int count();

    Answer findByChoiceAndQuiz(Long choiceId, Long QuizId);

}
