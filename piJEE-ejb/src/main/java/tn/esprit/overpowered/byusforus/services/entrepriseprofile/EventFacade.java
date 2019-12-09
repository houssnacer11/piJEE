/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;

/**
 *
 * @author pc
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> implements EventFacadeLocal, EventFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }

    @Override
    public Long createEvent(Event event) {
        this.create(event);
        return event.getId();
    }

    @Override
    public void editEvent(Event event) {
        this.edit(event);
    }

    @Override
    public void deleteEvent(Event event) {
        this.remove(event);
    }

    @Override
    public List<Event> searchEventByName(String eventName) {
       return em.createQuery("select e from Event e where e.name like CONCAT('%',:name,'%')",Event.class)
               .setParameter("name", eventName)
               .getResultList();
    }

    @Override
    public List<Event> viewAllEvent() {
        return this.findAll();
    }

    @Override
    public Event findEvent(Long idEvent) {
        return this.find(idEvent);
    }

}
