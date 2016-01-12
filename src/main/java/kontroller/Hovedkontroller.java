package kontroller;

import com.google.gson.Gson;
import beans.Bruker;
import beans.BrukerB;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Passord;
import beans.Rom;
import email.Email;
import java.util.HashMap;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Service;
import verktøy.Passordgenerator;

/**
 * 
 * @author Stein-Erik
 */
@Controller
public class Hovedkontroller {
    
    @Autowired
    private Service service;
    private Bruker testBruker = new Bruker();
    
    @RequestMapping(value = "/*")
    public String start(Model model, HttpSession sess){
        testBruker.setFornavn("Stein-Erik");
        testBruker.setEtternavn("Bjørnnes");
        testBruker.setEpost("steinerikbjornnes@gmail.com");
        testBruker.setFodedato(new Date(94, 04, 03));
        Klasse testKlasse = new Klasse();
        testKlasse.setNavn("Dataingeniør");
        testBruker.setKlasse(testKlasse);
        testBruker.setTelefonnummer(99475118);
        BrukerB brukerBean = (BrukerB) sess.getAttribute("brukerBean");
        
        if(brukerBean != null && brukerBean.isInnlogget()){
            return "Forside";
        }
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
    public String minSide(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        if(brukerb != null && brukerb.isInnlogget()){
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("VelgRom")
    public String velgRom(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "VelgRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("Kontakt")
    public String kontakt(HttpSession sess, Model model){
        return "Kontakt";
    }
    
    @RequestMapping("Forside")
    public String forside(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "Forside";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("FinnRom")
    public String finnRom(Model model, HttpSession sess){
        model.addAttribute("rom", new Rom());
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "FinnRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("SokeSide")
    public String sokeSide(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "SokeSide";
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
    @RequestMapping(value = "/events/getEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    String getVacation(HttpServletResponse response, HttpSession sess) {
        
        //kall til database for å finne relevant info.
        //ID, tittel, start, slutt, descr, rom, type, eiernavn, fag
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        List<KalenderEvent> events = service.getAlleEventsFraBruker(brukerb);
        
        String tittel = events.get(0).getTittel();
        System.out.println(tittel);
        
        String[] farger = {"#FFA500", "#00FF7F", "#00BFFF", "#FFFF00"};
        
        Map<String, Object> map;
        String jsonSend = "";
        
        for (KalenderEvent event : events){
            System.out.println(event.getTittel() + " " + event.getType());
            System.out.println(event.getStartTid());
            String start = "" + event.getStartTid();
            String slutt = "" + event.getSluttTid();
            map = new HashMap<String, Object>();
            map.put("id", event.getId());
            map.put("title", event.getTittel());
            map.put("start", start);
            map.put("end", slutt);
            map.put("color", farger[event.getType()]);
            String json = new Gson().toJson(map);
            if (!jsonSend.isEmpty()){
                jsonSend += ", ";
            }
            jsonSend += json;
        }

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return "[" + jsonSend + "]";
    }
}
