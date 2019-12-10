/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.entreprise;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 */
@ManagedBean(name = "hRManagerBean")
@SessionScoped
public class HRManagerBean implements Serializable {

    /**
     * Creates a new instance of HRManagerBean
     */
    public HRManagerBean() {
    }
    @ManagedProperty(value = "#{adminBean}")
    AdminBean adminBean;
    
    
}
