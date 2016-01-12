/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Stein-Erik
 */
public class Passord implements Validator{
    //@NotNull
    //@Size(min=8)
    //@Pattern(regexp="^[a-z]{1,}[A-Z]{1,}[0-9]{1,}[~`!@#$%^&*()\\-\\_=+[{\\]}\\|;:\'\",<\\.>/?]{2,}$")
    private String passord;
    private String passord2;
    private boolean generert;

    public boolean isGenerert() {
        return generert;
    }

    public void setGenerert(boolean generert) {
        this.generert = generert;
    }
    
    

    public String getPassord2() {
        return passord2;
    }

    public void setPassord2(String passord2) {
        this.passord2 = passord2;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validate(Object o, Errors errors) {
        String spesialbok = "~`!@#$%^&*()-_=+[{]}|;:'\",<.>/?]$€£";
        Passord pass = (Passord) o;
        String nyttPassord = pass.getPassord();
        if(nyttPassord.equals(nyttPassord.toLowerCase())||nyttPassord.equals(nyttPassord.toUpperCase())){
            errors.rejectValue("passord2", "feilmelding.smastorepassord");
        }
        int spesial = 0;
        for (int i = 0; i < nyttPassord.length(); i++) {
            for (int j = 0; j < spesialbok.length(); j++) {
                if(nyttPassord.charAt(i)==spesialbok.charAt(j)){
                    spesial++;
                }
            }
        }
        if(spesial<2){
            errors.rejectValue("passord", "feilmelding.spesialpassord");
        }
        
        if(passord.length()<8){
            errors.rejectValue("passord", "feilmelding.lengdepassord");
        }
        
        if(!generert && !passord.equals(passord2)){
            errors.rejectValue("passord", "feilmelding.ulikepassord");
        }
    }
}
