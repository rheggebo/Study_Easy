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
public class Passord{
     //@Size(min=8)
      //@Pattern(regexp="^[a-z]{1,}[A-Z]{1,}[0-9]{1,}[~`!@#$%^&*()\\-\\_=+[{\\]}\\|;:\'\",<\\.>/?]{2,}$")
    private String passord;
    private String passord2;

    
    /*public void validate(Object o, Errors errors) {
      Passord pass = (Passord) o;
      String nyttPassord = pass.getPassord();
      if(nyttPassord.equals(nyttPassord.toLowerCase())||nyttPassord.equals(nyttPassord.toUpperCase())){
        errors.rejectValue("passord", "feilmelding.smastorepassord");
        errors.rejectValue("passord2", "feilmelding.smastorepassord");
      }
        int spesial = 0;
        for (int i = 0; i < nyttPassord.length(); i++) {
            for (int j = 0; j < arr.length; j++) {
                Object object = arr[j];

            }
        }

        if(spesial<2){
            errors.rejectValue("passord", "feilmelding.spesialpassord");
        }

        if(passord.length()<8){
            errors.rejectValue("passord", "feilmelding.lengdepassord");
        }

        if(!passord.equals(passord2)){
            errors.rejectValue("passord", "feilmelding.ulikepassord");
        }
  }

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
  }
