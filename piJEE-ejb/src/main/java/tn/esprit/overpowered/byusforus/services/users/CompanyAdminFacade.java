/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;

/**
 *
 * @author pc
 */
@Stateless
public class CompanyAdminFacade extends AbstractFacade<CompanyAdmin> implements CompanyAdminFacadeLocal, CompanyAdminFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompanyAdminFacade() {
        super(CompanyAdmin.class);
    }

    @Override
    public Long addCompanyAdmin(CompanyAdmin companyAdmin) {
        this.create(companyAdmin);
        return companyAdmin.getId();
    }

    @Override
    public void updateCompanyAdmin(CompanyAdmin companyAdmin) {
        this.edit(companyAdmin);
    }

    @Override
    public void deleteCompanyAdmin(Long idAdmin) {
        this.remove(this.find(idAdmin));
    }

    @Override
    public Long createCompanyProfile(CompanyProfile compProfile) {
        em.persist(compProfile);
        return compProfile.getId();
    }

    @Override
    public void bindCompanyAdminToCompanyProfile(Long idAmin, Long idComp) {
        CompanyAdmin compAdmin = this.find(idAmin);
        CompanyProfile compProfile = em.find(CompanyProfile.class, idComp);

        if ((compProfile != null) && (compAdmin != null)) {
            compAdmin.setCompanyProfile(compProfile);
            compProfile.setCompanyAdmin(compAdmin);
        }
    }

    @Override
    public void bindCompanyHRToCompanyProfile(Long idHR, Long idComp) {
        HRManager hrManager = em.find(HRManager.class, idHR);
        CompanyProfile compProfile = em.find(CompanyProfile.class, idComp);

        if ((compProfile != null) && (hrManager != null)) {
            hrManager.setCompanyProfile(compProfile);
            compProfile.setCompanyHRManager(hrManager);
        }
    }

    @Override
    public void bindCompanyPMToCompanyProfile(Long idPM, Long idComp) {
        ProjectManager pManager = em.find(ProjectManager.class, idPM);
        CompanyProfile compProfile = em.find(CompanyProfile.class, idComp);

        if ((compProfile != null) && (pManager != null)) {
            pManager.setCompanyProfile(compProfile);
            List<ProjectManager> liste = new ArrayList<>();
            if (compProfile.getProjectManagers() == null) {
                liste.add(pManager);
                compProfile.setProjectManagers(liste);
            } else {
                compProfile.getProjectManagers().add(pManager);
            }
        }
    }

    @Override
    public void bindEmployeeToCompanyProfile(Long idEmp, Long idComp) {
        Employee employee = em.find(Employee.class, idEmp);
        CompanyProfile compProfile = em.find(CompanyProfile.class, idComp);

        if ((compProfile != null) && (employee != null)) {
            employee.setCompany(compProfile);
            List<Employee> liste = new ArrayList<>();
            if (compProfile.getEmployees() == null) {
                liste.add(employee);
                compProfile.setEmployees(liste);
            } else {
                compProfile.getEmployees().add(employee);
            }
        }
    }

    @Override
    public void updateCompanyProfile(CompanyProfile compProfile) {
        em.merge(compProfile);

    }

    @Override
    public CompanyProfile viewCompanyProfile(Long idComp) {
        if ((em.find(CompanyProfile.class, idComp)) != null) {
            return em.find(CompanyProfile.class, idComp);
        } else {
            return null;
        }

    }

    @Override
    public List<CompanyProfile> searchCompanyProfileByName(String name) {
        List<CompanyProfile> companies = em.createQuery(
                "select p from CompanyProfile p where p.name"
                + " LIKE CONCAT('%',:nom,'%')",
                CompanyProfile.class).setParameter("nom", name).getResultList();
        return companies;
    }

    @Override
    public CompanyProfile searchCompanyProfileById(Long id) {
        return em.find(CompanyProfile.class, id);
    }

    @Override
    public void deleteCompanyProfile(Long idComp) {
        CompanyProfile company = em.find(CompanyProfile.class, idComp);

        em.remove(company);
    }

    @Override
    public Long createEvent(Event event) {
        CompanyProfile company = em.find(CompanyProfile.class,
                event.getCompany().getId());
        if (company.getEvents().contains(event)) {
            return -1L;
        }
        em.persist(event);
        return event.getId();
    }

    @Override
    public void updateEvent(Event event) {
        em.merge(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        Event event = em.find(Event.class, eventId);
        em.remove(event);
    }

    @Override
    public Event searchEventById(Long eventId) {
        return em.find(Event.class, eventId);
    }

    @Override
    public Event findEvent(String name) {
        Event e = null;
        try {
            e = em.createQuery("SELECT E FROM Event E where E.name= :name", Event.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
        }
        return e;
    }

    @Override
    public List<Event> searchEventByDate(Date startDate, Date endDate) {
        List<Event> events = em.createQuery("select e from Event e where"
                + " e.startDate= :startDate and e.endDate= :endDate", Event.class)
                .setParameter("startDate", startDate, TemporalType.DATE)
                .setParameter("endDate", endDate, TemporalType.DATE)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> searchEventByDate(Date startDate) {
        return em.createQuery("select e from Event e where"
                + " e.startDate= :startDate", Event.class)
                .setParameter("startDate", startDate, TemporalType.DATE)
                .getResultList();
    }

    @Override
    public List<Event> searchEventByName(String name) {
        return em.createQuery("select e from Event e where"
                + " e.startDate LIKE CONCAT('%',:name,'%')", Event.class)
                .getResultList();
    }

    @Override
    public List<Event> searchEventByLocation(String location) {
        return em.createQuery("select e from Event e where"
                + " e.location LIKE CONCAT('%',:location,'%')", Event.class)
                .getResultList();
    }

    @Override
    public List<Event> viewAllEvents() {
        List<Event> events = new ArrayList<>();
        try {
            events = em.createQuery("SELECT E FROM Event E").getResultList();
        } catch (Exception e) {
        }
        return events;
    }

    @Override
    public List<CompanyProfile> findAllCompanies() {
        List<CompanyProfile> compList = em.createQuery("select c"
                + " from CompanyProfile c",
                CompanyProfile.class).getResultList();
        return compList;
    }

    @Override
    public List<JobOffer> jobOffersByCompany(Long compId) {
        CompanyProfile comp = em.find(CompanyProfile.class, compId);
        List<JobOffer> listJobs = comp.getListOfOffers();
        List<JobOffer> jobOffers = new ArrayList<>();
        for (JobOffer job : listJobs) {
            jobOffers.add(em.find(JobOffer.class, job.getId()));
        }
        return jobOffers;
    }

    @Override
    public CompanyProfile checkCompanyExistence(String compName) {
        CompanyProfile company = null;
        try {
            company = em.createQuery("SELECT C FROM CompanyProfile C where C.name= :name", CompanyProfile.class)
                    .setParameter("name", compName)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return company;
    }

}
