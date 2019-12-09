package tn.esprit.overpowered.byusforus.managedbeans.quiz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil;
import tn.esprit.overpowered.byusforus.managedbeans.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tn.esprit.overpowered.byusforus.entities.quiz.Answer;
import tn.esprit.overpowered.byusforus.entities.quiz.Choice;
import tn.esprit.overpowered.byusforus.entities.quiz.Quiz;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.services.quiz.AnswerFacadeLocal;
import tn.esprit.overpowered.byusforus.services.quiz.ChoiceFacadeLocal;
import tn.esprit.overpowered.byusforus.services.quiz.QuizTryFacadeLocal;
import util.authentication.Authenticator;

@ManagedBean
@javax.faces.bean.SessionScoped
public class QuizTryController implements Serializable {

    @EJB
    private AnswerFacadeLocal answerFacade;

    @EJB
    private ChoiceFacadeLocal choiceFacade;

    private static final long serialVersionUID = 1L;

    @EJB
    private QuizTryFacadeLocal ejbFacade;
    private List<QuizTry> items = null;
    private QuizTry selected;

    private String selections;

    private String recordingFile;

    private String recordingName;

    private String breachType = "";

    private String recordingBlob;

    public String getRecordingBlob() {
        return recordingBlob;
    }

    public void setRecordingBlob(String recordingBlob) {
        this.recordingBlob = recordingBlob;
    }

    public String getRecordingName() {
        return recordingName;
    }

    public void setRecordingName(String recordingName) {
        this.recordingName = recordingName;
    }

    public String getBreachType() {
        return breachType;
    }

    public void setBreachType(String breachType) {
        this.breachType = breachType;
    }

    public String getRecordingFile() {
        return recordingFile;
    }

    public void setRecordingFile(String recordingFile) throws IOException {
        this.recordingFile = recordingFile;
    }

    public ChoiceFacadeLocal getChoiceFacade() {
        return choiceFacade;
    }

    public void setChoiceFacade(ChoiceFacadeLocal choiceFacade) {
        this.choiceFacade = choiceFacade;
    }

    public QuizTryFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(QuizTryFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getSelections() {
        return selections;
    }

    public void setSelections(String selections) throws IOException {
        this.selections = selections;
    }

    public void onBlobBase64Sent() throws FileNotFoundException, IOException {
        String path = "../standalone/deployments/piJEE-web-1.0.war/";
        this.recordingName = "QUIZ_TRY_" + new Random().nextInt() + ".ts.webm";
        String blobBase64 = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("blobBase64Name");
        byte[] blob = java.util.Base64.getDecoder().decode(blobBase64.split(",")[1]);
        this.recordingBlob = blobBase64;
        try (FileOutputStream fos = new FileOutputStream(path + recordingName)) {
            fos.write(blob);
        }

    }

    public void listen() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        File f = new File("87azeaz.txt");
        f.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(params.get("choiceQuestion1"));
            writer.write(params.get("choiceQuestion2"));
        }
    }

    public float calculateScore(Choice choice) {
        float score = 0f;
        return score;
    }

    public void createNewFile(String fileName) throws IOException {
        File f = new File(fileName + ".txt");
        f.createNewFile();
    }

    public String breachDetected() throws IOException {
        this.createNewFile("87azeaz breach");
        this.breachType = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
        return "quiz_results?faces-redirect=true";
    }

    public String submitTry(Quiz quiz) throws IOException {
        QuizTry qt = new QuizTry();
        qt.setRecording(recordingName);
        qt.setFinishDate(new Date());
        qt.setQuiz(quiz);
        // Un-comment this when I connect with candidate
        qt.setCandidate((Candidate) Authenticator.currentSession.getUser());
        ArrayList<Answer> answerList = new ArrayList<>();
        if (!this.breachType.equals("NO_BREACH")) {
            // breach happened
            // get candidate application and set status to breach type
        } else {

//        // Get choice by id
            ArrayList<Answer> answerListToPersist = new ArrayList<>();
            float score = 0f;
            float totalPoints = 0f;
            float candidatePoints = 0f;
            for (String id : selections.split(",")) {
                Choice c = choiceFacade.find(Long.parseLong(id));
                totalPoints += c.getQuestion().getQuestionPoints();
                if (c.getIsCorrectChoice()) {
                    candidatePoints += c.getQuestion().getQuestionPoints();
                }
                Answer answer = new Answer();
                answer.setAnswer(c);
                answer.setQuestion(c.getQuestion());
                answerFacade.create(answer);
                answerList.add(answer);
            }
            score = candidatePoints / totalPoints;
            for (Answer a : answerList) {
                answerListToPersist.add(answerFacade.findByChoiceAndQuiz(a.getAnswer().getIdChoice(), a.getQuestion().getIdQuestion()));
            }
            qt.setAnswers(answerListToPersist);
            qt.setPercentage(100 * score);
        }
        this.selected = qt;
        ejbFacade.create(qt);
        return "quiz_results?faces-redirect=true";
    }

    public QuizTryController() {
    }

    public QuizTry getSelected() {
        return selected;
    }

    public void setSelected(QuizTry selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private QuizTryFacadeLocal getFacade() {
        return ejbFacade;
    }

    public QuizTry prepareCreate() {
        selected = new QuizTry();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("QuizTryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("QuizTryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("QuizTryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<QuizTry> getItems() {
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

    public QuizTry getQuizTry(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<QuizTry> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<QuizTry> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = QuizTry.class)
    public static class QuizTryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuizTryController controller = (QuizTryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "quizTryController");
            return controller.getQuizTry(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof QuizTry) {
                QuizTry o = (QuizTry) object;
                return getStringKey(o.getIdQuizTry());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), QuizTry.class.getName()});
                return null;
            }
        }

    }

}
