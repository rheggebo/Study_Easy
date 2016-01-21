 package kontroller;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.KalenderEvent;
import beans.Passord;
import beans.Rom;
import beans.RomBestilling;
import beans.SlettAbonnementValg;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;
import ui.FormVelgRom;

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
    
    private void returnerMinSide(Model model, BrukerB brukerb){
        List<Abonemennt> liste = service.getAbonemenntFraBruker(brukerb);
        model.addAttribute("abonemenntListe", liste);
        KalenderEvent ke = new KalenderEvent();
        ke.setEpost(brukerb.getEpost());
        Date dato = Calendar.getInstance().getTime();
        ke.setStartTid(new Timestamp(dato.getTime()));
        List<RomBestilling> eventListe = service.getReserverteRom(ke);
        model.addAttribute("reservasjonsliste", eventListe);
        List<KalenderEvent> kalenderEventListe = service.getKalenderEventEier(brukerb);
        model.addAttribute("kalenderEventListe", kalenderEventListe);
    }
    
    @RequestMapping("MinSide")
    public String minSide(HttpSession sess, Model model, HttpServletRequest req){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            returnerMinSide(model, brukerb);
            model.addAttribute("resultat", new SlettAbonnementValg());
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());

        return "Innlogging";
    }
    
    @RequestMapping(value="slett")
    public String getSlettAbonnement(@ModelAttribute("resultat") SlettAbonnementValg sa, HttpSession sess, Model model, HttpServletResponse response, HttpServletRequest req) {
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        String valgt = sa.getResultat();
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            if("Slett".equals(req.getParameter("slettFagAbKnapp"))) {
                //prøver å slette abonemennt med brukerepost og den valgte koden,
                // fanger exception viss ikke
                try{
                    service.slettAbonemennt(new Abonemennt(brukerb.getEpost(), valgt, 1));  
                }
                catch(Exception e){
                    model.addAttribute("meldingFag", "feilmelding.finnesIkkeAbonnement");
                }
            }
            if("Slett".equals(req.getParameter("slettBrukerAbKnapp"))) {
                //prøver å slette abonemennt med brukerepost og den valgte koden,
                // fanger exception viss ikke
                try{
                    service.slettAbonemennt(new Abonemennt(brukerb.getEpost(), valgt, 0));
                }
                catch(Exception e){
                    model.addAttribute("meldingBruker", "feilmelding.finnesIkkeAbonnement");
                }
            }
            if("Slett".equals(req.getParameter("slettHendelseKnapp"))) {
                //prøver å slette abonemennt med brukerepost og den valgte koden,
                // fanger exception viss ikke
                try{
                    //service.slettAbonemennt(new Abonemennt(brukerb.getEpost(), valgt, 0));
                    model.addAttribute("meldingHendelse", "feilmelding.finnesIkkeHendelse");
                }
                catch(Exception e){
                    model.addAttribute("meldingHendelse", "feilmelding.finnesIkkeHendelse");
                }
            }
            returnerMinSide(model, brukerb);
            model.addAttribute("resultat1", new SlettAbonnementValg());
            return "MinSide";
        } 

        model.addAttribute("bruker", new Bruker()); 
        return "MinSide";
    }
    
    @RequestMapping("VelgRom")
    public String velgRom(@ModelAttribute FormVelgRom fromVelgRom,HttpSession sess, Model model){
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
        if(brukerb != null && brukerb.isInnlogget()){
            if(brukerb.getTilgangsniva()==2){
                model.addAttribute("nyBruker", new Bruker());
                model.addAttribute("passord", new Passord());
                model.addAttribute("bruker", brukerb);
                return "LeggTilBruker";
            }else{
                model.addAttribute("bruker", brukerb);
                return "MinSide";
            }
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
