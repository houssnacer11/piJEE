/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.quiz.Answer;

/**
 *
 * @author Yassine
 */
@Remote
public interface AnswerFacadeRemote {

    void create(Answer answer);

    void edit(Answer answer);

    void remove(Answer answer);

    Answer find(Object id);

    List<Answer> findAll();

    List<Answer> findRange(int[] range);

    int count();
    
}
