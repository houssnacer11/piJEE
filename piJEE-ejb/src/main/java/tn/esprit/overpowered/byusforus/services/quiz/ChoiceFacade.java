/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.quiz.Choice;
import tn.esprit.overpowered.byusforus.entities.quiz.Question;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

/**
 *
 * @author Yassine
 */
@Stateless
public class ChoiceFacade extends AbstractFacade<Choice> implements ChoiceFacadeLocal, ChoiceFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoiceFacade() {
        super(Choice.class);
    }

    @Override
    public void create(Choice entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Choice> getByQuestionId(Long id) {
        ArrayList<Choice> choicesList;
        return choicesList = new ArrayList<>(em.createQuery("select c from Choice c where c.question.idQuestion = :questionid").setParameter("questionid", id)
                .getResultList());

    }

}
