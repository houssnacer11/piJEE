/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.quiz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

/**
 *
 * @author Yassine
 */
@Stateless
public class QuizFacade extends AbstractFacade<Quiz> implements QuizFacadeLocal, QuizFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuizFacade() {
        super(Quiz.class);
    }

    @Override
    public Quiz getQuizByJobOfferId(Long jobOffer) {
        Quiz quiz = null;
        try {
            quiz = em.createQuery(
                    "SELECT q FROM Quiz q WHERE "
                    + "q.jobOffer.id  = :cid ", Quiz.class)
                    .setParameter("cid", jobOffer).getSingleResult();
            return quiz;
        } catch (NoResultException e) {
        }
        return quiz;
    }

    @Override
    public Quiz getQuizByName(String name) {
        Quiz quiz = null;
        try {
            quiz = em.createQuery(
                    "SELECT q FROM Quiz q WHERE "
                    + "q.name = :name ", Quiz.class)
                    .setParameter("name", name).getSingleResult();
            return quiz;
        } catch (NoResultException e) {
        }
        return quiz;
    }

}
