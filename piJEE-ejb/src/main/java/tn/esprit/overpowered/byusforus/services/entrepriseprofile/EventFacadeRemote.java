/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;

/**
 *
 */
@Remote
public interface EventFacadeRemote {

    void create(Event event);

    void edit(Event event);

    void remove(Event event);

    Event find(Object id);

    List<Event> findAll();

    List<Event> findRange(int[] range);

    int count();
    public Long createEvent(Event event);
    public void editEvent(Event event);
    public void deleteEvent(Event event);
    public List<Event> searchEventByName(String eventName);
    public List<Event> viewAllEvent();
    public Event findEvent(Long idEvent);
    
    
    
}
