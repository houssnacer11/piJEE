package tn.esprit.overpowered.byusforus.managedbeans.quiz;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import LicenceManager.LicenceFacadeRemote;
import Licenses.Licence;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.services.authentication.AuthenticationFacadeRemote;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.EmployeeFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.CompanyAdminFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.HRManagerFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.ProjectManagerFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.UserFacadeRemote;
import tn.esprit.overpowered.byusforus.util.Role;
import util.authentication.Authenticator;

/**
 *
 * @author pc
 */
@ManagedBean(name = "signUpBean")
@SessionScoped
public class SignUpBean implements Serializable {

    @EJB
    private UserFacadeRemote userFacade;
    @EJB
    private CandidateFacadeRemote candidateFacade;

    @EJB
    private AuthenticationFacadeRemote authFacade;

    @EJB
    private LicenceFacadeRemote licenceFacade;

    @EJB
    private CompanyAdminFacadeRemote compAdminFacade;

    @EJB
    private HRManagerFacadeRemote hrManagerFacade;

    @EJB
    private ProjectManagerFacadeRemote pManagerFacade;

    @EJB
    private EmployeeFacadeRemote employeeFacade;

    /**
     * Creates a new instance of SignUpBean
     */
    //Information for SignUp
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Candidate candidate;
    private CompanyProfile compProfile;
    private CompanyAdmin compAdmin;
    private HRManager hrManager;
    private ProjectManager pManager;
    private Employee employee;
    private String signUpCode;
    private String signUpUserCode;
    private String companyName;
    private String licenceID;
    private String licencePass;
    private Role role;
    private Role[] roles;
    private List<Skill> selectedSkills;
    private Set<ExpertiseLevel> selectedLevels;

    //Information for SignIn
    private String login;
    private String pass;
    private String authUid;
    private String authToken;

    public SignUpBean(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LicenceFacadeRemote getLicenceFacade() {
        return licenceFacade;
    }

    public void setLicenceFacade(LicenceFacadeRemote licenceFacade) {
        this.licenceFacade = licenceFacade;
    }

    public CompanyProfile getCompProfile() {
        return compProfile;
    }

    public void setCompProfile(CompanyProfile compProfile) {
        this.compProfile = compProfile;
    }

    public CompanyAdmin getCompAdmin() {
        return compAdmin;
    }

    public void setCompAdmin(CompanyAdmin compAdmin) {
        this.compAdmin = compAdmin;
    }

    public HRManager getHrManager() {
        return hrManager;
    }

    public void setHrManager(HRManager hrManager) {
        this.hrManager = hrManager;
    }

    public ProjectManager getpManager() {
        return pManager;
    }

    public void setpManager(ProjectManager pManager) {
        this.pManager = pManager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSignUpCode() {
        return signUpCode;
    }

    public void setSignUpCode(String signUpCode) {
        this.signUpCode = signUpCode;
    }

    public String getSignUpUserCode() {
        return signUpUserCode;
    }

    public void setSignUpUserCode(String signUpUserCode) {
        this.signUpUserCode = signUpUserCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLicenceID() {
        return licenceID;
    }

    public void setLicenceID(String licenceID) {
        this.licenceID = licenceID;
    }

    public String getLicencePass() {
        return licencePass;
    }

    public void setLicencePass(String licencePass) {
        this.licencePass = licencePass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAuthUid() {
        return authUid;
    }

    public void setAuthUid(String authUid) {
        this.authUid = authUid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserFacadeRemote getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacadeRemote userFacade) {
        this.userFacade = userFacade;
    }

    public CandidateFacadeRemote getCandidateFacade() {
        return candidateFacade;
    }

    public void setCandidateFacade(CandidateFacadeRemote candidateFacade) {
        this.candidateFacade = candidateFacade;
    }

    public AuthenticationFacadeRemote getAuthFacade() {
        return authFacade;
    }

    public void setAuthFacade(AuthenticationFacadeRemote authFacade) {
        this.authFacade = authFacade;
    }

    public CompanyAdminFacadeRemote getCompAdminFacade() {
        return compAdminFacade;
    }

    public void setCompAdminFacade(CompanyAdminFacadeRemote compAdminFacade) {
        this.compAdminFacade = compAdminFacade;
    }

    public HRManagerFacadeRemote getHrManagerFacade() {
        return hrManagerFacade;
    }

    public void setHrManagerFacade(HRManagerFacadeRemote hrManagerFacade) {
        this.hrManagerFacade = hrManagerFacade;
    }

    public ProjectManagerFacadeRemote getpManagerFacade() {
        return pManagerFacade;
    }

    public void setpManagerFacade(ProjectManagerFacadeRemote pManagerFacade) {
        this.pManagerFacade = pManagerFacade;
    }

    public EmployeeFacadeRemote getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacadeRemote employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public String SignUpAsCandidate() throws NoSuchAlgorithmException {
        candidate = new Candidate();
        candidate.setUsername(username);
        candidate.setEmail(email);
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setPassword(password.getBytes(StandardCharsets.UTF_8));
        String goTo = "null";
        String result = userFacade.checkExistence(candidate.getEmail(), candidate.getUsername());
        if (result.equals("OK")) {
            signUpCode = candidateFacade.accountCreationConfirmation(email);
            goTo = "/views/back/signUp/candidateConfirmSignUp?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "result", result);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return goTo;
    }

    public String SignUpAsCompanyStaff() throws NoSuchAlgorithmException {

        String goTo = "null";
        String result = userFacade.checkExistence(email, username);
        if (result.equals("OK")) {
            switch (role) {
                case ADMIN:
                    compAdmin = new CompanyAdmin(username, email, firstName, lastName);
                    compAdmin.setPassword(password.getBytes(StandardCharsets.UTF_8));
                    break;
                case HR:
                    hrManager = new HRManager(username, email, firstName, lastName);
                    hrManager.setPassword(password.getBytes(StandardCharsets.UTF_8));
                    break;
                case MANAGER:
                    pManager = new ProjectManager(username, email, firstName, lastName);
                    pManager.setPassword(password.getBytes(StandardCharsets.UTF_8));
                    break;
                default:
                    employee = new Employee(username, email, firstName, lastName);
                    employee.setPassword(password.getBytes(StandardCharsets.UTF_8));
                    break;
            }
            goTo = "/views/back/signUp/compVerifSignUp?faces-redirect=true";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "result", result);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return goTo;
    }

    public void submit() throws NoSuchAlgorithmException {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage("Successfull", msg);
        this.SignUpAsCandidate();
    }

    public String doLogin() throws NoSuchAlgorithmException {
        String goTo = "null";
        System.out.println("====Login" + login + " passssss===" + pass);
        authUid = authFacade.login(login, pass);
        if (authUid != null) {
            goTo = "/views/back/signUp/twoFAConfirm?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Unknow Information");
            FacesContext.getCurrentInstance().addMessage("Error", msg);
        }
        System.out.println("ààààààààà" + goTo);
        return goTo;
    }

    public String doFinalizeCandidateSignUp() {
        String goTo = "null";
        if (signUpCode.equals(signUpUserCode)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful", "Account Creation Please Login");
            FacesContext.getCurrentInstance().addMessage("Successful", msg);
            candidateFacade.createCandidate(candidate);
            goTo = "/signUp?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Code Error");
            FacesContext.getCurrentInstance().addMessage("ERROR", msg);
        }
        return goTo;
    }

    public String doFinalizeCompStaffSingUp() {
        String goTo = "null";
        if (signUpCode.equals(signUpUserCode)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful", "Account Creation Please Login");
            FacesContext.getCurrentInstance().addMessage("Successful", msg);

            switch (role) {
                case ADMIN:
                    compProfile = new CompanyProfile(companyName);
                    ;
                    Long adminId = compAdminFacade.addCompanyAdmin(compAdmin);
                    Long compId = compAdminFacade.createCompanyProfile(compProfile);
                    compAdminFacade.bindCompanyAdminToCompanyProfile(adminId, compId);
                    break;
                case HR:
                    compProfile = compAdminFacade.checkCompanyExistence(companyName);
                    Long hrID = hrManagerFacade.createHRManager(hrManager);
                    compAdminFacade.bindCompanyHRToCompanyProfile(hrID, compProfile.getId());
                    break;
                case MANAGER:
                    compProfile = compAdminFacade.checkCompanyExistence(companyName);
                    Long pmID = pManagerFacade.createPManager(pManager);
                    compAdminFacade.bindCompanyPMToCompanyProfile(pmID, compProfile.getId());
                    break;
                default:
                    compProfile = compAdminFacade.checkCompanyExistence(companyName);
                    Long empID = employeeFacade.createEmployee(employee);
                    compAdminFacade.bindEmployeeToCompanyProfile(empID, compProfile.getId());
                    break;
            }
            goTo = "/signUp?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "Code Error");
            FacesContext.getCurrentInstance().addMessage("ERROR", msg);
        }
        return goTo;
    }

    public String doFinalizeLogin() {
        String goTo = "null";
        Session session = authFacade.finalizeLogin(authUid, authToken);
        if (session != null) {
            Authenticator.currentSession = session;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", Authenticator.currentSession.getUser().getUsername());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            if (("CANDIDATE").equals(Authenticator.currentSession.getUser().getDiscriminatorValue())) {
                goTo = "/views/candidate/candidatesView?faces-redirect=true";//This is just for testing purpose until the actual page is created;
            } else {
                goTo = "/views/front/adminEntreprise/home?faces-redirect=true";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unsuccessful", "Check Your Code");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return goTo;
    }

    public Role[] Roles() {
        for (Role r : Role.values()) {
            System.out.println("****Roles:***" + r.name());
        }
        roles = Role.values();
        return Role.values();
    }

    public Skill[] Skills() {
        for (Skill r : Skill.values()) {
            System.out.println("****Skills:***" + r.name());
        }
        
        return Skill.values();
    }
    
    public ExpertiseLevel[] Levels(){
        for(ExpertiseLevel e : ExpertiseLevel.values())
            System.out.println("---------Levels------"+ e.name());
        return ExpertiseLevel.values();
    }

    public String verifyStaffInfo() {
        String goTo = "null";
        Licence licence = new Licence();
        licence.setCompanyName(companyName);
        licence.setCompanyLicenceId(licenceID);
        licence.setUserPass(licencePass);
        licence.setUserRole(role);
        if (licenceFacade.verifyLicenceInfo(licence)) {
            if (role.equals(Role.ADMIN)) {
                if (compAdminFacade.checkCompanyExistence(companyName) != null) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "This Company Already has an ADMIN");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    goTo = "/signUp?faces-redirect=true";
                } else {
                    signUpCode = candidateFacade.accountCreationConfirmation(email);
                    goTo = "/views/back/signUp/compConfirmSignUp?faces-redirect=true";
                }
            } else {
                if (compAdminFacade.checkCompanyExistence(companyName) == null) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "NO EXISING ADMIN FOR COMPANY");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    goTo = "/signUp?faces-redirect=true";
                } else {
                    signUpCode = candidateFacade.accountCreationConfirmation(email);
                    goTo = "/views/back/signUp/compConfirmSignUp?faces-redirect=true";
                }
            }

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unsuccessful", "Check Your Information");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return goTo;

    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/signUp?faces-redirect=true";
    }

    public SignUpBean() {
    }

    public List<Skill> getSelectedSkills() {
        return selectedSkills;
    }

    public void setSelectedSkills(List<Skill> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

    public Set<ExpertiseLevel> getSelectedLevels() {
        return selectedLevels;
    }

    public void setSelectedLevels(Set<ExpertiseLevel> selectedLevels) {
        this.selectedLevels = selectedLevels;
    }
    
    
}
