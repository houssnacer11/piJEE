/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;

/**
 *
 * @author pc
 */
@Remote
public interface EmployeeFacadeRemote {

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findRange(int[] range);

    int count();
    
    //EMPLOYEE
    public Long createEmployee(Employee employee);
    
    public void updateEmployee(Employee employee);
    
    public void deleteEmployee(Long employeeId);
    
    public Employee searchEmployeeById(Long employeeId);
    
    public List<Employee> searchEmployeeByLastName(String name);
    
    public List<Employee> searchEmployeeByPosition(String position);
    
    public Long subscribe(Long companyId, Long employeeId);
    
    public List<JobOffer> customJobOfferList(Long employeeId);
    
    public List<CompanyProfile> subscriptionList(Long employeeId);
    
    public Long createJobOffer(JobOffer jobOffer, Long idPManager, String gmailPassword);
    
    
}
