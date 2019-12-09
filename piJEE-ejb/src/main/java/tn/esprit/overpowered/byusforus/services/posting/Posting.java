/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.posting;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.posting.Post;
import tn.esprit.overpowered.byusforus.entities.users.User;

/**
 *
 * @author aminos
 */
@Stateful
public class Posting implements  PostingLocal, PostingRemote {
        @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void createPost(Post post, Long userId) {
        User tmp = em.find(User.class, userId);
        post.setBy(tmp);
        em.persist(post);
    }

    @Override
    public ArrayList<Post> getPosts(Long userId) {
        return PostsRepository.getPosts(em, em.find(User.class, userId));
    }

    @Override
    public void deletePost(Long postId) {
        em.remove(em.find(Post.class, postId));
    }

    @Override
    public Post getPost(Long p) {
        return em.find(Post.class, p);
    }

    @Override
    public void updatePost(Post p) {
        Post tmp = em.find(Post.class, p.getId());
        tmp.setText(p.getText());
        tmp.setFilePath(p.getFilePath());
        tmp.setFileType(p.getFileType());
        em.merge(tmp);
        em.flush();
    }

    @Override
    public ArrayList<Post> getPostsAfterDate(Long u, Date d) {
        return PostsRepository.getPostsByDate(em, em.find(User.class, u), d);
    }
    
    
}
