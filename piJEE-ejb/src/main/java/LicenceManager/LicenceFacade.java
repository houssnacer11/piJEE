/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LicenceManager;

import Licenses.Licence;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 */
@Stateless
public class LicenceFacade extends AbstractFacade<Licence> implements LicenceFacadeRemote,LicenceFacadeLocal {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicenceFacade() {
        super(Licence.class);
    }

    @Override
    public boolean verifyLicenceID(Long licenceID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyUser(String userPass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyLicenceInfo(Licence licence) {
        Licence licenceInfo = null;
        try {
            licenceInfo = em.createQuery("select l from Licence l where "
                    + "l.companyLicenceId= :licenceID AND "
                    + "l.companyName= :companyName AND "
                    + "l.userPass= :userPass AND "
                    + "l.userRole= :userRole",Licence.class)
                    .setParameter("licenceID", licence.getCompanyLicenceId())
                    .setParameter("companyName", licence.getCompanyName())
                    .setParameter("userPass", licence.getUserPass())
                    .setParameter("userRole", licence.getUserRole())
                    .getSingleResult();
        } catch (NoResultException nre) {
        }
        return licenceInfo != null;
    }

    @Override
    public Long createLicence(Licence licence) {
        this.create(licence); 
        return licence.getId();
    }
    
}
