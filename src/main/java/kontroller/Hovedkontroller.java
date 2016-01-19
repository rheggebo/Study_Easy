 package kontroller;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Passord;
import beans.Rom;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;

/**
 * 
 * @author Stein-Erik
 */
@Controller
public class Hovedkontroller {
    
    @Autowired
    private Service service;
    
    @RequestMapping(value = "/*")
    public String start(Model model, HttpSession sess){
        BrukerB brukerBean = (BrukerB) sess.getAttribute("brukerBean");
        
        if(brukerBean != null && brukerBean.isInnlogget()){
            model.addAttribute("bruker", brukerBean);
            return "Forside";
        }
        Bruker bruker = new Bruker();
        bruker.setTilgangsniva(2);
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping(value="logInSjekk")
    public String logIn(@ModelAttribute("bruker") Bruker bruker, Model model, HttpSession sess){
        if(bruker.getEpost() != null && !bruker.getEpost().equals("")
                && bruker.getPassord() != null && !bruker.getPassord().equals("")){
            try{
                if(service.sjekkPassord(bruker.getEpost(), bruker.getPassord())){
                    BrukerB brukerBean = new BrukerB(service.hentBruker(bruker));
                    brukerBean.setInnlogget(true);
                    sess.setAttribute("brukerBean", brukerBean);
                    model.addAttribute("bruker", brukerBean);
                    return "Forside";
                }
            }catch (Exception e){
                
            }
                
        }
        model.addAttribute("melding", "feilmelding.login");
        bruker.setPassord("");
        return "Innlogging";
    }
    
    @RequestMapping("MinSide")
    public String minSide(HttpSession sess, Model model, HttpServletRequest req){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            List<Abonemennt> liste = service.getAbonemenntFraBruker(brukerb);
            model.addAttribute("abonemenntListe", liste);
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("VelgRom")
    public String velgRom(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            return "VelgRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("Kontakt")
    public String kontakt(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            return "Kontakt";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("Forside")
    public String forside(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            return "Forside";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("FinnRom")
    public String finnRom(Model model, HttpSession sess){        
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            model.addAttribute("rom", new Rom());
            return "FinnRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("SokeSide")
    public String sokeSide(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            return "SokeSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("LeggTilBruker")
    public String leggTilBruker(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb.getTilgangsniva()==2){
            if(brukerb != null && brukerb.isInnlogget()){
                model.addAttribute("nyBruker", new Bruker());
                model.addAttribute("passord", new Passord());
                model.addAttribute("bruker", brukerb);
                return "LeggTilBruker";
            }
        }else{
            model.addAttribute("bruker", brukerb);
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("loggUt")
    public String loggUt(HttpSession sess, Model model){
        sess.removeAttribute("brukerBean");
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("valgtRom")
    public String valgtRom(@ModelAttribute("rom")Rom romForm, Model model){
        Rom rom = service.getRom(romForm);
        model.addAttribute("rom", rom);
        return "FinnRom";
    }
}
