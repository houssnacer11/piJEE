/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;
import tn.esprit.overpowered.byusforus.util.MailSender;


/**
 *
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal, EmployeeFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public Long createEmployee(Employee employee) {
        this.create(employee);
        return employee.getId();
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee emp = this.find(employee.getId());
        if(emp != null)
        {
            this.edit(employee);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
       Employee emp = this.find(employeeId);
        
        if(emp != null){
         this.remove(emp);
        }
       
    }

    @Override
    public Employee searchEmployeeById(Long employeeId) {
       return this.find(employeeId);
    }

    @Override
    public List<Employee> searchEmployeeByLastName(String name) {
         return em.createQuery("select e from Employee e where e.lastName"
                 + " LIKE CONCAT('%',:name,'%')", Employee.class)
                 .setParameter("name", name)
                 .getResultList();
    }

    @Override
    public List<Employee> searchEmployeeByPosition(String position) {
                List<Employee> employees = this.findAll();
        for (Employee emp : employees) {
            for (String exp : emp.getExperiences()) {
                if (exp.contains(position)) {
                    employees.add(emp);
                }
            }
        }
        return employees;
    }

    @Override
    public Long subscribe(Long companyId, Long employeeId) {
        CompanyProfile company = em.find(CompanyProfile.class, companyId);
        Employee employee = this.find(employeeId);
        if(!employee.getSubscribedCompanies().contains(company) &&
                !(company.getSubscribers().contains(employee))){
        employee.getSubscribedCompanies().add(company);
        company.getSubscribers().add(employee);
        return company.getId();
        }
        return -1L;
    }

    @Override
    public List<JobOffer> customJobOfferList(Long employeeId) {
       return this.find(employeeId).getRegisteredOffers();
    }

    @Override
    public List<CompanyProfile> subscriptionList(Long employeeId) {
        return this.find(employeeId).getSubscribedCompanies();
    }
      
    
    //JOB OFFER
        @Override
    public Long createJobOffer(JobOffer jobOffer, Long idPManager, String gmailPassword) {
        Employee pManager = em.find(Employee.class, idPManager);
        CompanyProfile company = jobOffer.getCompany();
        HRManager hRManager = company.getCompanyHRManager();
        String to = hRManager.getEmail();
        em.persist(jobOffer);
        
        
        try {
            if(MailSender.sendMail("smtp.gmail.com", "587", pManager.getEmail(),
                    "JOB POST APPROVAL REQUEST OF" + pManager.getLastName(),
                    pManager.getUsername(), gmailPassword, to, "Dear Mr/Mrs"
                    + " "+ hRManager.getLastName() + " " + hRManager.getFirstName()+ 
                            " I Need approval over"
                            + " The JOB OFFER titled" + jobOffer.getTitle())){
                
                return jobOffer.getId();
            }   } catch (MessagingException ex) {
            Logger.getLogger(EmployeeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           return -1L;
}
    
}
