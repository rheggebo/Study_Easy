package email;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andreasbergman
 */
public class Email extends javax.swing.JFrame {
    //EmailSetup email = new EmailSetup();
    private String USER_NAME = "therealstudyeasy";  // GMail user name (just the part before "@gmail.com")
    private String PASSWORD = "studyeasy123"; // GMail password

    /**
     * Creates new form email
     */
    public Email() {
    }

    public boolean sendEpost(String mottaker, String tema, String melding) {//GEN-FIRST:event_helpButton1ActionPerformed
        // TODO add your handling code here:
            String body = melding;
            String from = USER_NAME;
            String pass = PASSWORD;
            String[] to = { mottaker }; // list of recipient email addresses
            String subject = tema;	
            System.out.println("from: "+from+" pass: "+pass );
		        
        try {
            EmailSetup.sendFromGMail(from, pass, to, subject, body);
            System.out.println("Ingen feil, sender epost");
            return true;
        } catch (MessagingException ex) {
            System.out.println(ex);
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
