/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verktøy;

import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Stein-Erik
 */
public class Passordgenerator {
    private String nyttPassord;
    private final String alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?0123456789";
    private final String alfabet2 = "~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
    
    
    public String genererPassord(){
        return RandomStringUtils.random(15, 0, 0, false, false, alfabet.toCharArray());
    }
    
    public static void main(String[] args) {
        System.out.println("Nytt passord: "+new Passordgenerator().genererPassord());
    }
    
}
