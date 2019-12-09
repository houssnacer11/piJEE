/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.quiz.Answer;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

/**
 *
 * @author Yassine
 */
@Stateless
public class AnswerFacade extends AbstractFacade<Answer> implements AnswerFacadeLocal, AnswerFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnswerFacade() {
        super(Answer.class);
    }

    @Override
    public Answer findByChoiceAndQuiz(Long choiceId, Long questionId) {
        return (Answer) em.createQuery("select a from Answer a where a.answer.idChoice =:idchoice and a.question.idQuestion=:questionId")
                .setParameter("idchoice", choiceId).setParameter("questionId", questionId)
                .getResultList().get(0);
    }

}
