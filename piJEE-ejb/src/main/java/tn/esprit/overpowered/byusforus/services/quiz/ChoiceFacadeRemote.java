/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.quiz.Choice;

/**
 *
 * @author Yassine
 */
@Remote
public interface ChoiceFacadeRemote {

    void create(Choice choice);

    void edit(Choice choice);

    void remove(Choice choice);

    Choice find(Object id);

    List<Choice> findAll();

    List<Choice> findRange(int[] range);

    int count();

    ArrayList<Choice> getByQuestionId(Long id);

}
