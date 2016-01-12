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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;
import verktøy.Passordgenerator;
import verktøy.PasswordHasher;

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
    private PasswordHasher hasher = new PasswordHasher();
    
    @RequestMapping(value = "glemtPassord")
    public String glemtPassord(Model model){
        model.addAttribute("bruker", new Bruker());
        return "Glemsk";
    }
    
    @RequestMapping(value="sendNyttPassord")
    public String glemsk(@ModelAttribute("bruker") Bruker bruker, Model model, HttpServletRequest request, BindingResult error){
        String sjekk = bruker.getEpost();
        Bruker temp = null;
        try{
            temp = service.hentBruker(sjekk);
        }catch (Exception e){
            
        }   
        if(temp != null){
            if(sendNyPass(temp, error)){
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
    public String endrePassord(HttpSession sess, @ModelAttribute("passord") Passord pass, BindingResult error, Model model){
        System.out.println("Endrer passord*********");
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        Bruker bruker = (Bruker) service.hentBruker(brukerb.getEpost());
        String gammeltPassord = "";
        try{
            gammeltPassord = hasher.getSaltedHash(pass.getPassord());
        }catch (Exception e){
            error.rejectValue("passord", "feilmelding.passordGenerell");
            model.addAttribute("passord", new Passord());
            return "EnrePassordRed";
        }
        if(gammeltPassord.equals(bruker.getPassord())){
            
        }else{
            error.rejectValue("passord", "feilmelding.gammeltPassord");
            model.addAttribute("passord", new Passord());
            return "EnrePassordRed";
        }
        pass.validate(pass, error);
        if(error.hasErrors()){
            model.addAttribute("passord", new Passord());
            return "EnrePassordRed";
        }else{
            bruker.setPassord(pass.getPassord1());
            if(service.endreBruker(bruker)){
                return "MinSide";
            }
        }
        error.rejectValue("passord", "feilmelding.passordGenerell");
        model.addAttribute("passord", new Passord());
        return "EnderPassordRed";
    }
    
    @RequestMapping("EndrePassordRed")
    public String endrePassordRed(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("passord", new Passord());
            return "EndrePassordRed";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    private String genererPassord(BindingResult error){
        String nyttPassord = generator.genererPassord();
        Passord pass = new Passord();
        pass.setGenerert(true);
        pass.setPassord(nyttPassord);
        pass.validate(pass, error);
        if(error.hasErrors()){
            System.out.println(error.getErrorCount()+ nyttPassord);
            nyttPassord = genererPassord(error);
        }
        return nyttPassord;
    }    
    
    private Boolean sendNyPass(Bruker temp, BindingResult error){
        String mottaker = temp.getEpost();
        String tema = "Nytt passord for bruker: "+temp.getEpost();
        String nyttPassord = genererPassord(error);
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
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            return "MinSideRed";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
