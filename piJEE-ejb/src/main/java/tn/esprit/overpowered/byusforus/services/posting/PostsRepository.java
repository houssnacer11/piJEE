/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.posting;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import tn.esprit.overpowered.byusforus.entities.posting.Post;
import tn.esprit.overpowered.byusforus.entities.users.Professional;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
public class PostsRepository {
    
    public static ArrayList<Post> getOwnPosts(EntityManager em, User user) {
         ArrayList<Post> result = new ArrayList();
        result.addAll(em.createQuery(
                "SELECT p FROM Post p WHERE p.by = :u ", Post.class)
                .setParameter("u", user).getResultList());
        return result;
    }
    public static ArrayList<Post> getPosts(EntityManager em, User user) {
         ArrayList<Post> result = new ArrayList();
         result.addAll(getOwnPosts(em, user));
         Professional professionalAccount = (Professional) user;
         for (User u: professionalAccount.getContacts())
             result.addAll(getOwnPosts(em, u));
        return result;
    }
    
    public static ArrayList<Post> getPostsByDate(EntityManager em, User user, Date date) {
        ArrayList<Post> result = new ArrayList();
        result.addAll(getPosts(em, user).
                stream()
                .filter((Post p)->p.getCreated().after(date))
                .collect(Collectors.toList()));
        return result;
    }
}
