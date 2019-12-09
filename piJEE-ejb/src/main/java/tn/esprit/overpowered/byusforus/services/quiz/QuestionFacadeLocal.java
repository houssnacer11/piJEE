/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;

/**
 *
 * @author Yassine
 */
@Local
public interface QuestionFacadeLocal {

    void create(Question question);

    void edit(Question question);

    void remove(Question question);

    Question find(Object id);

    List<Question> findAll();

    List<Question> findRange(int[] range);

    int count();
    
    ArrayList<Question> findByQuizId(Integer id);
    
}
