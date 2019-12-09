/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.Posting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.FilenameUtils;
import org.jboss.logging.Logger;
import org.omg.PortableInterceptor.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tn.esprit.overpowered.byusforus.entities.posting.Post;
import tn.esprit.overpowered.byusforus.managedbeans.util.FileDetector;
import tn.esprit.overpowered.byusforus.services.posting.Posting;
import tn.esprit.overpowered.byusforus.services.posting.PostingLocal;
import util.authentication.Authenticator;

/**
 *
 * @author aminos
 */
@ManagedBean(name = "postingModel")
@SessionScoped
public class PostingModel {

    @EJB
    PostingLocal postsEJB;

    private UploadedFile uploadedFile;
    private String filePath;
    private Post post = new Post();

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    private static final Logger logger = Logger.getLogger(PostingModel.class);

    public void upload(FileUploadEvent event) throws IOException, TikaException {
        uploadedFile = event.getFile();
        filePath = saveFile(uploadedFile);
        if (filePath == null) {
            uploadedFile = null;
        }
    }

    public void createPost() {
        if (filePath != null) {
            post.setFilePath(filePath);
            post.setFileType(uploadedFile.getContentType());
        }

        org.owasp.html.PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String safeHTML = policy.sanitize(this.post.getText());

        this.post.setText(safeHTML);
        this.postsEJB.createPost(post, Authenticator.currentSession.getUser().getId());
        this.post = new Post();

        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "newsFeed.xhtml");
    }

    public ArrayList<Post> getPosts() {
        ArrayList<Post> result = this.postsEJB.getPosts(Authenticator.currentSession.getUser().getId());
        logger.info("Posts number: " + result.size());
        return result;
    }

    private String saveFile(UploadedFile uF) throws IOException, TikaException {
        boolean fail = checkUploadedFile(uF);
        String filename;

        if (!fail) {
            // save file

            filename = System.getenv("UPLOAD_LOCATION")
                    + "/" + UUID.randomUUID()
                    + uF.getFileName();
            Path file = Paths.get(filename);
            logger.warn(file.getFileName());
            Files.write(file, uF.getContents());
            logger.info(uF.getFileName());

            logger.info(uF.getContentType());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Media uploaded successfully!"));
            //byte[] contents = uploadedFile.getContents(); // Or getInputStream()
            // ... Save it, now!
            return filename;
        }
        return null;
    }

    private boolean checkUploadedFile(UploadedFile uF) throws IOException, TikaException {
        String fileName = uF.getFileName();
        String contentType = uF.getContentType();
        File testFile = new File("/tmp/" + UUID.randomUUID() + uF.getFileName());
        FileUtils.writeByteArrayToFile(testFile, uF.getContents());
        if (contentType.contains("png")) {
            if (!FileDetector.isPNG(testFile)) {
                testFile.delete();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileName + " invalid file!"));
                return true;
            }
        } else if (contentType.contains("jpg") || contentType.contains("jpeg")) {
            if (!FileDetector.isJPG(testFile)) {
                testFile.delete();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileName + " invalid file!"));
                return true;
            }
        } else if (contentType.contains("mp4")) {
            if (!FileDetector.isMP4(testFile)) {
                testFile.delete();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileName + " invalid file!"));
                return true;
            }
        }
        testFile.delete();
        return false;
    }

    public void checkAuth() throws IOException {
        if (Authenticator.currentSession == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/webapp/signUp.xhtml");
        }
    }

    public String comeBaby() {
        return "/views/posts/newsFeed?faces-redirect=true";
    }

}
