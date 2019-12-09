/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.data;

import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
public class InfoTracker {

    public static void saveData(String key, Object data, FacesContext currentContext) {

        currentContext.getExternalContext().getSessionMap().put(key, data);
    }

    public static Object retrieveData(String key, FacesContext currentContext) {
        return currentContext.getExternalContext().getSessionMap().get(key);
    }

}
