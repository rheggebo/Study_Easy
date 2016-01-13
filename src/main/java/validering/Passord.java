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
     //@Size(min=8)
      //@Pattern(regexp="^[a-z]{1,}[A-Z]{1,}[0-9]{1,}[~`!@#$%^&*()\\-\\_=+[{\\]}\\|;:\'\",<\\.>/?]{2,}$")
        private String passord;
    private String passord1;
    private String passord2;
    private boolean generert;
    
    public String getPassord1() {
        return passord1;
    }

    public void setPassord1(String passord1) {
        this.passord1 = passord1;
    }
    
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
        return Passord.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        String spesialbok = "~`!@#$%^&*()-_=+[{]}|;:'\",<.>/?]$€£";
        Passord pass = (Passord) o;
        String nyttPassord = pass.getPassord1();
        if(nyttPassord.equals(nyttPassord.toLowerCase())||nyttPassord.equals(nyttPassord.toUpperCase())){
            errors.rejectValue("passord", "feilmelding.smastorepassord");
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
            System.out.println("Passord, spesial");
            errors.rejectValue("passord1", "feilmelding.spesialpassord");
        }
        
        if(passord1.length()<8){
            System.out.println("Passord, lengde");
            errors.rejectValue("passord1", "feilmelding.lengdepassord");
        }
        
        if(!generert && !passord1.equals(passord2)){
            System.out.println("Passord, ulike");
            errors.rejectValue("passord", "feilmelding.ulikepassord");
        }
    }
}