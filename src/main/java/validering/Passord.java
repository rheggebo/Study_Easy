/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validering;

import beans.Bruker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Stein-Erik
 */
public class Passord implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Passord.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Bruker bruker = (Bruker) o;
        String nyttPassord = bruker.getPassord();
        
        if(nyttPassord.toLowerCase().equals(nyttPassord) || nyttPassord.toUpperCase().equals(nyttPassord)){
            errors.rejectValue("passord", "storsmaPassord");
        }
        
        
    }
    
}
