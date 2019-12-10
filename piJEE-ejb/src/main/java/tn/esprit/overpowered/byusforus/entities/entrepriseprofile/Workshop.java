/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.entities.entrepriseprofile;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 */
@Entity
@DiscriminatorValue( value = "WORKSHOP")
public class Workshop extends Event implements Serializable {

    private static final long serialVersionUID = 15L;


 
   
    
    
}
