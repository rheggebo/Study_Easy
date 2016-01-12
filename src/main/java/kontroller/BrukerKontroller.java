/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Bruker;
import beans.BrukerB;
import beans.Passord;
import email.Email;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;
import verktøy.Passordgenerator;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class BrukerKontroller {
    @Autowired
    private Service service;
    private Passordgenerator generator = new Passordgenerator();
    private Bruker testBruker = new Bruker();
    
    @RequestMapping(value = "glemtPassord")
    public String glemtPassord(Model model){
        model.addAttribute("bruker", new Bruker());
        return "Glemsk";
    }
    
    @RequestMapping(value="sendNyttPassord")
    public String glemsk(@ModelAttribute("bruker") Bruker bruker, Model model, HttpServletRequest request, Errors errors){
        String sjekk = bruker.getEpost();
        Bruker temp = null;
        try{
            temp = service.hentBruker(sjekk);
        }catch (Exception e){
            
        }   
        if(temp != null){
            if(sendNyPass(temp, errors)){
                    return "Innlogging";
                }else{
                    model.addAttribute("melding", "feilmelding.email");
                    return "Glemsk";
                }
        }
        
        
        model.addAttribute("melding", "feilside.email");
        model.addAttribute("bruker", new Bruker());
        return "Glemsk";
    }
    
    @RequestMapping("EndrePassord")
    public String endrePassord(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("EndrePassordRed")
    public String endrePassordRed(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        if(brukerb != null && brukerb.isInnlogget()){
            return "EndrePassordRed";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    private String genererPassord(Errors errors){
        String nyttPassord = generator.genererPassord();
        Passord pass = new Passord();
        pass.setGenerert(true);
        pass.setPassord(nyttPassord);
        pass.validate(pass, errors);
        if(errors.hasErrors()){
            System.out.println(errors.getErrorCount()+ nyttPassord);
            nyttPassord = genererPassord(errors);
        }
        return nyttPassord;
    }    
    
    private Boolean sendNyPass(Bruker temp, Errors errors){
        String mottaker = temp.getEpost();
        String tema = "Nytt passord for bruker: "+temp.getEpost();
        String nyttPassord = genererPassord(errors);
        temp.setPassord(nyttPassord);
        Email email = new Email();
        String melding= 
                "Dine nye innloggingsdetaljer er: \n \n "
                + "Brukernavn: "+temp.getEpost()+"\n "
                + "Passord: "+nyttPassord+"\n \n "
                + "Vi anbefaler at du bytter dette passordet ved neste innlogging. \n \n "
                + "Hilsen Study Easy teamet";
        if(service.endreBruker(temp)){
            if(email.sendEpost(mottaker, tema, melding)){
                System.out.println("Email sendt!****");
                return true;
            }
            System.out.println("Email ikke sendt :( *****");
        }
        return false; 
    }

    @RequestMapping("MinSideRed")
    public String minSideRed(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        if(brukerb != null && brukerb.isInnlogget()){
            return "MinSideRed";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
