 package kontroller;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Fag;
import beans.KalenderEvent;
import beans.Klasse;
import beans.Passord;
import beans.Rom;
import beans.RomBestilling;
import beans.SlettAbonnementValg;
import email.Email;
import static java.lang.Thread.sleep;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import konfig.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;
import ui.FormFinnRom;
import ui.FormVelgRom;
import ui.LeggTilFagKlasse;

/**
 * 
 * @author Stein-Erik
 */
@Controller
public class Hovedkontroller {
    
    @Autowired
    private Service service;
    private Test test;
    
    
    @RequestMapping(value = "/*")
    public String start(Model model, HttpSession sess){
        BrukerB brukerBean = (BrukerB) sess.getAttribute("brukerBean");
        
        if(brukerBean != null && brukerBean.isInnlogget()){
            model.addAttribute("bruker", brukerBean);
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
        System.out.println("Jeg kjører nå");
        List<Abonemennt> liste = service.getAbonemenntFraBruker(brukerb);
        model.addAttribute("abonemenntListe", liste);
        KalenderEvent ke = new KalenderEvent();
        ke.setEpost(brukerb.getEpost());
        Date dato = Calendar.getInstance().getTime();
        Timestamp now = new Timestamp(dato.getTime());
        ke.setStartTid(now);
        List<RomBestilling> eventListe = service.getReserverteRom(ke);
        long msek20Min = 20*60*1000;
        Email email = new Email();
        String tema = "Husk bekrefting oppmøte på din romreservasjon";
        String melding="Hei. \n\nDet nå er på tide å bekrefte oppmøte for din romreservasjon. Legg deg inn på http://localhost:8080/Study_Easy/ og gå inn på min side for å bekrefte reservanen."
                + " Hvis romreservasjon ikke blir bekreftet, "
                + "vil den bli fjernet og du vil få en anmerkning på din bruker. \nFor mange anmerkninger kan føre til at brukeren blir sperret."
                + "\n\nHilsen Study Easy teamet.";
        for (RomBestilling romBestilling : eventListe) {
            System.out.println(romBestilling.getStartDato().getTime()-now.getTime()+" "+msek20Min);
            long diff = romBestilling.getStartDato().getTime()-now.getTime();
            /*if(diff<msek20Min/2){
                KalenderEvent ke2 = new KalenderEvent();
                ke2.setRom(romBestilling.getRomId());
                ke2.setStartTid(romBestilling.getStartDato());
                ke2.setSluttTid(romBestilling.getSluttDato());
                ke2.setEpost(brukerb.getEpost());
                service.slettBooking(ke2);
            }else*/ if(diff<msek20Min){
                romBestilling.setKlokkesjekk(true);
                email.sendEpost(brukerb.getEpost(), tema, melding);
            }
        }
        model.addAttribute("event", new KalenderEvent());
        model.addAttribute("reservasjonsliste", eventListe);
        List<KalenderEvent> kalenderEventListe = service.getKalenderEventEier(brukerb);
        model.addAttribute("kalenderEventListe", kalenderEventListe);
        model.addAttribute("resultat", new SlettAbonnementValg());
        model.addAttribute("bruker", brukerb);
    }
    
    /*private void sjekk(){
        final KalenderEvent ke = new KalenderEvent();
        ke.setEpost("steinerikbjornnes@gmail.com");
        Date dato = Calendar.getInstance().getTime();
        final Timestamp now = new Timestamp(dato.getTime());
        ke.setStartTid(now);
        final int periode = 1000;
        final int ms10Min = 10*60*1000;
        final int ms15Min = 15*60*1000;
        Thread asd = new Thread(){
            public void run(){
                try{
                    ke.setStartTid(new Timestamp(now.getTime()+ms15Min));
                    List<RomBestilling> eventListe = service.getReserverteRom(ke);
                    for (RomBestilling romBestilling : eventListe) {
                        System.out.println(now.getTime());
                        System.out.println(ke.getStartTid().getTime());
                        System.out.println(now.getTime()-ke.getStartTid().getTime());
                        System.out.println(ms10Min);
                        if(now.getTime()-ke.getStartTid().getTime()>ms10Min && !romBestilling.isSjekketInn()){
                            KalenderEvent t = new KalenderEvent();
                            t.setEpost(ke.getEpost());
                            t.setStartTid(romBestilling.getStartDato());
                            t.setSluttTid(romBestilling.getSluttDato());
                            t.setRom(romBestilling.getRomId());
                            service.slettBooking(ke);
                            //Legg til anmerkning
                        }
                    }
                    now.setTime(now.getTime()+periode);
                    sleep(periode);
                }catch (InterruptedException ex){
                    Logger.getLogger(Hovedkontroller.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        };
        asd.run();
    }*/
    
    @RequestMapping("MinSide")
    public String minSide(HttpSession sess, Model model, HttpServletRequest req){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            returnerMinSide(model, brukerb);
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

                    Email email = new Email();
                    List<Abonemennt> abonemennt = service.getBrukerAbonnement(brukerb.getEpost());
                    String melding = "Hendelse slettet av " + brukerb.getFornavn();
                    for (Abonemennt abn : abonemennt){
                        email.sendEpost(abn.getEierid(), "Ny hendelse", melding);
                    }

                }catch(Exception e){
                    model.addAttribute("slettFeilMelding", "feilmelding.kunneIkkeSletteAbonnement");
                }
            }
            if("Slett".equals(req.getParameter("slettBrukerAbKnapp"))) {
                //prøver å slette abonemennt med brukerepost og den valgte koden,
                // fanger exception viss ikke
                try{
                    service.slettAbonemennt(new Abonemennt(brukerb.getEpost(), valgt, 0));
                }
                catch(Exception e){
                    model.addAttribute("slettFeilMelding", "feilmelding.kunneIkkeSletteAbonnement");
                }

            }
            if("Slett".equals(req.getParameter("slettHendelseKnapp"))) {
                //prøver å slette abonemennt med brukerepost og den valgte koden,
                // fanger exception viss ikke
                String[] split = valgt.split(" ");
                
                    try{
                        KalenderEvent kalenderEvent = new KalenderEvent();
                        kalenderEvent.setId(Integer.parseInt(split[0]));
                        kalenderEvent.setEpost(brukerb.getEpost());
                        if (service.getKalenderEventHidden(kalenderEvent).equals(true)){
                    
                            service.fjernKalenderEvent(kalenderEvent);
                            
                        }else{

                            service.fjernKalenderEvent(kalenderEvent);

                            Email email = new Email();
                            List<Abonemennt> abonemennt = service.getBrukerAbonnement(brukerb.getEpost());
                            String melding = "Hendelse slettet av " + brukerb.getFornavn();
                            for (Abonemennt abn : abonemennt){
                            email.sendEpost(abn.getEierid(), "Ny hendelse", melding);  
                        }
                    }
                }
                catch(Exception e){
                    model.addAttribute("slettFeilMelding", "feilmelding.kunneIkkeSletteHendelse");
                }
            }
            
            returnerMinSide(model, brukerb);
            //model.addAttribute("resultat", new SlettAbonnementValg());
            return "MinSide";
            
        }

        model.addAttribute("bruker", new Bruker()); 
        return "Innlogging";
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
            FormFinnRom fFR = new FormFinnRom();
            model.addAttribute("formFinnRom", fFR);
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
    public String leggTilBruker(@ModelAttribute LeggTilFagKlasse leggTilFagKlasse,HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            if(brukerb.getTilgangsniva()==2){
                model.addAttribute("nyBruker", new Bruker());
                model.addAttribute("passord", new Passord());
                model.addAttribute("bruker", brukerb);
                List<Klasse> listeKlasser = service.getAlleKlasser();
                ArrayList<String> listeK = new ArrayList<String>();
                for (Klasse klasse : listeKlasser) {
                    listeK.add(klasse.getNavn());
                }
                List<Fag> midlListe = service.getAlleFag();
                ArrayList<String> fagListe = new ArrayList<String>();
                for (Fag fag : midlListe) {
                    fagListe.add(fag.getFagID());
                }
                model.addAttribute("nyKlasse", new Fag());
                model.addAttribute("nyttFag", new Fag());
                model.addAttribute("fagListe", fagListe);
                model.addAttribute("klasseListe", listeK);
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
    public String valgtRom(@ModelAttribute("rom")Rom romForm, Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
              Rom rom = service.getRom(romForm);
              model.addAttribute("rom", rom);
              return "FinnRom";  
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
