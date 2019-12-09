package tn.esprit.overpowered.byusforus.managedbeans.quiz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import tn.esprit.overpowered.byusforus.entities.candidat.CandidateApplication;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.Part;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateApplicationFacadeLocal;
import tn.esprit.overpowered.byusforus.util.JobApplicationState;
import util.authentication.Authenticator;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.EventType;
import com.itextpdf.kernel.pdf.canvas.parser.PdfCanvasProcessor;
import com.itextpdf.kernel.pdf.canvas.parser.data.IEventData;
import com.itextpdf.kernel.pdf.canvas.parser.data.TextRenderInfo;
import com.itextpdf.kernel.pdf.canvas.parser.filter.TextRegionEventFilter;
import com.itextpdf.kernel.pdf.canvas.parser.listener.FilteredEventListener;
import com.itextpdf.kernel.pdf.canvas.parser.listener.LocationTextExtractionStrategy;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.mail.MessagingException;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.util.MailSender;

@ManagedBean
@SessionScoped
public class CandidateApplicationController implements Serializable {

    @EJB
    private CandidateApplicationFacadeLocal ejbFacade;
    private List<CandidateApplication> items = null;
    private CandidateApplication selected;
    private Part file;
    private float userScore;

    public CandidateApplicationController() {
    }

    public float getUserScore() {
        return userScore;
    }

    public void setUserScore(float userScore) {
        this.userScore = userScore;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public CandidateApplication getSelected() {
        return selected;
    }

    public void setSelected(CandidateApplication selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CandidateApplicationFacadeLocal getFacade() {
        return ejbFacade;
    }

    public CandidateApplication prepareCreate() {
        selected = new CandidateApplication();
        initializeEmbeddableKey();
        return selected;
    }

    public String showApplicationForm(JobOffer jobOffer) {
        selected = new CandidateApplication();
        selected.setJobOffer(jobOffer);
        return "/views/front/JobApplication/apply_to_job_form?faces-redirect=true";
    }

    public void saveResumeFile() throws IOException {
        String randomFileName = new Random().nextInt() + file.getSubmittedFileName();
        this.selected.setResume(randomFileName);
        try (InputStream input = file.getInputStream()) {
            File f = new File(randomFileName);
            Files.copy(input, f.toPath());
            Files.copy(f.toPath(), new File("../standalone/deployments/piJEE-web-1.0.war/", randomFileName).toPath());
        }
    }

    public String submitApplication() {
        this.selected.setCandidate((Candidate) Authenticator.currentSession.getUser());
        this.selected.setAdditionalInfo("Waiting for company decision");
        this.create();
        return "/views/candidate/candidatesView?faces-redirect=true";
    }

    public String inviteToTakeQuiz() {
        try {
            MailSender.sendMail("smtp.gmail.com", "587",
                    "pidevnoreply@gmail.com", "pidevnoreply",
                    "pidevpidev", this.selected.getCandidate().getEmail(), "You have been invited!",
                    "You have been invited to pass a quiz for the following job offer: "
                    + this.selected.getJobOffer().getTitle());
        } catch (MessagingException e) {

        }
        this.selected.setJobApplicationState(JobApplicationState.INVITED_FOR_QUIZ);
        this.selected.setAdditionalInfo("Waiting for candidate to take quiz");
        this.update();
        return "List?faces-redirect=true";
    }

    public String rejectCandidacy() {
        try {
            MailSender.sendMail("smtp.gmail.com", "587",
                    "pidevnoreply@gmail.com", "pidevnoreply",
                    "pidevpidev", this.selected.getCandidate().getEmail(), "Your application results",
                    "We are sorry to inform you that your application for the job offer "
                    + this.selected.getJobOffer().getTitle() + "has been refused.");
        } catch (MessagingException e) {

        }
        this.selected.setJobApplicationState(JobApplicationState.REFUSED);
        this.selected.setAdditionalInfo("candidacy refuesd.");
        this.update();
        return "List?faces-redirect=true";
    }

    public Boolean hasAlreadyApplied(Long jobOfferId) {
        Long currentCandidateID = Authenticator.currentSession.getUser().getId();
        if (ejbFacade.getApplicationByCandidateId(currentCandidateID, jobOfferId) == null) {
            return false;
        }
        CandidateApplication cApp = ejbFacade.getApplicationByCandidateId(currentCandidateID, jobOfferId);
        this.selected = cApp;
        return cApp != null;
    }

    public int numberOfCandidacies() {
        List<CandidateApplication> allApplications = ejbFacade.getCandidateApplicationByJobOFfer(this.selected.getJobOffer().getId());
        return allApplications.size();
    }

    public int getCandidateRanking() throws IOException {
        List<CandidateApplication> allApplications = ejbFacade.getCandidateApplicationByJobOFfer(this.selected.getJobOffer().getId());
        int candidateRanking = 0;
        Map<Float, Candidate> candidateScoreMap = new TreeMap<>(Collections.reverseOrder());
        for (CandidateApplication cApp : allApplications) {
            candidateScoreMap.put(getCandidateScore(cApp), cApp.getCandidate());
        }
        int index = 1;
        for (Candidate cd : candidateScoreMap.values()) {
            if (cd.getEmail().equals(this.selected.getCandidate().getEmail())) {
                return index;
            }
            index++;
        }

        return candidateRanking;
    }

    private float getCandidateScore(CandidateApplication cApp) throws IOException {
        float matchingSkills = 0f;
        float nbSkills = cApp.getJobOffer().getSkills().size();
//        float nbSkills = jobOffer.getSkills().size();
        float skillsScore = 0f;
        Set<Skill> skillSet = getSkillsFromResume(cApp);
        return skillsScore = (skillSet.size() / nbSkills);
    }

    public Set<Skill> getSkillsFromResume(CandidateApplication cApp) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(cApp.getResume()));
        Rectangle rect = new Rectangle(pdfDoc.getFirstPage().getArtBox());

        FontFilter fontFilter = new FontFilter(rect);
        FilteredEventListener listener = new FilteredEventListener();
        LocationTextExtractionStrategy extractionStrategy = listener.attachEventListener(new LocationTextExtractionStrategy(), fontFilter);
        new PdfCanvasProcessor(listener).processPageContent(pdfDoc.getFirstPage());

        String actualText = extractionStrategy.getResultantText();
        float matchingSkills = 0f;
//        float nbSkills = this.selected.getJobOffer().getSkills().size();
        float nbSkills = cApp.getJobOffer().getSkills().size();
//        float nbSkills = jobOffer.getSkills().size();
        float skillsScore = 0f;
        Set<Skill> skillSet = new HashSet<>();
        for (Skill skill : cApp.getJobOffer().getSkills()) {
//        for (Skill skill : jobOffer.getSkills()) {
            if (actualText.toLowerCase().contains(skill.name().toLowerCase())) {
                matchingSkills++;
                skillSet.add(skill);
            }
        }
        skillsScore = (matchingSkills / nbSkills);
        FileOutputStream fos = new FileOutputStream("resultat1112.txt");
        fos.write(actualText.getBytes());
        FileOutputStream foskill = new FileOutputStream("skillset.txt");
        foskill.write(skillSet.toString().getBytes());
        foskill.write(String.valueOf(skillsScore).getBytes());
        pdfDoc.close();
        return skillSet;
    }

    class FontFilter extends TextRegionEventFilter {

        public FontFilter(Rectangle filterRect) {
            super(filterRect);
        }

        @Override
        public boolean accept(IEventData data, EventType type) {
            if (type.equals(EventType.RENDER_TEXT)) {
                TextRenderInfo renderInfo = (TextRenderInfo) data;

                String text = renderInfo.getActualText();
                PdfFont font = renderInfo.getFont();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream("pdffont.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CandidateApplicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fos.write(font.getFontProgram().getFontNames().getFontName().getBytes());
                    fos.write(renderInfo.getText().getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(CandidateApplicationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (null != font) {
//                    String fontName = font.getFontProgram().getFontNames().getFontName();
//                    return fontName.endsWith("HOTXCE");
                    return true;
                }
//                if (null != text) {
//                    return text.contains("Linux");
//                }
            }
            return false;
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CandidateApplicationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CandidateApplicationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CandidateApplicationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CandidateApplication> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public CandidateApplication getCandidateApplication(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CandidateApplication> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CandidateApplication> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CandidateApplication.class)
    public static class CandidateApplicationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CandidateApplicationController controller = (CandidateApplicationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "candidateApplicationController");
            return controller.getCandidateApplication(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CandidateApplication) {
                CandidateApplication o = (CandidateApplication) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CandidateApplication.class.getName()});
                return null;
            }
        }

    }

}
