/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.Service;
import ui.LeggTilFagKlasse;
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
    private validering.Passord passV = new validering.Passord();
    
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
            if(sendNyPass(temp, error, false)){
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
    public String endrePassord(HttpSession sess, @ModelAttribute("passord") Passord pass, BindingResult error, Model model) throws Exception{
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            Bruker bruker = (Bruker) service.hentBruker(brukerb.getEpost());
        bruker.setEtternavn("asdasd");
        Passord valider = new Passord();
        if(!hasher.check(pass.getPassord(),bruker.getPassord())){
            model.addAttribute("melding", "feilmelding.gammeltPassord");
            model.addAttribute("passord", new Passord());
            return "EndrePassordRed";
        }
        valider.validate(pass, error);
        if(error.hasErrors()){
            model.addAttribute("passord", new Passord());
            model.addAttribute("melding", "feilmelding.passordGenerell");
            return "EndrePassordRed";
        }else{
            bruker.setPassord(pass.getPassord1());
            if(service.endreBruker(bruker)){
                model.addAttribute("bruker", bruker);
                return "MinSide";
            }
        }
        model.addAttribute("melding", "feilmelding.passordGenerell");
        model.addAttribute("passord", new Passord());
        return "EndrePassordRed";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
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
        pass.setPassord1(nyttPassord);
        passV.validate(pass, error);
        if(error.hasErrors()){
            nyttPassord = genererPassord(error);
        }
        return nyttPassord;
    }    
    
    private Boolean sendNyPass(Bruker temp, BindingResult error, boolean nyBruker){
        passV.setGenerert(true);
        String mottaker = temp.getEpost();
        String tema = "Nytt passord for bruker: "+temp.getEpost();
        String tema2 = "Ny bruker opprettet for: "+temp.getEpost()+" hos Study Easy";
        String nyttPassord = genererPassord(error);
        temp.setPassord(nyttPassord);
        Email email = new Email();
        String melding= 
                "Dine nye innloggingsdetaljer er: \n \n "
                + "Logg inn på: http://localhost:8084/Study_Easy/ \n \n"
                + "Brukernavn: "+temp.getEpost()+"\n "
                + "Passord: "+nyttPassord+"\n \n "
                + "Vi anbefaler at du bytter dette passordet ved neste innlogging. \n \n "
                + "Hilsen Study Easy teamet";
        if(!nyBruker){
            if(email.sendEpost(mottaker, tema, melding)){
                if(service.endreBruker(temp)){
                    return true;
                }
            }
        }else{
            if(email.sendEpost(mottaker, tema2, melding)){
                if(service.nyBruker(temp)){
                    return true;
                }
            }
        }
        return false; 
    }

    @RequestMapping(value="LeggTilBrukerLagre")
    public String leggTilBrukerLagre(@Valid @ModelAttribute("nyBruker") Bruker bruker, @RequestParam("tilgangnivaa")String tilgang, Model model, BindingResult error, HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            if(error.hasErrors()){
            model.addAttribute("melding", "feilmelding.nyBrukerValidering");
            return "LeggTilBruker";
        }
        if(tilgang.equals("Elev")){
            bruker.setTilgangsniva(0);
        }else if(tilgang.equals("Lærer")){
            bruker.setTilgangsniva(1);
        }else if(tilgang.equals("Timeplansansvarlig")){
            bruker.setTilgangsniva(2);
        }
        model.addAttribute("passord", new Passord());
        Bruker b = null;
        try{
            b = service.hentBruker(bruker);
        }catch (Exception e){
            
        }
        if(b == null){
            System.out.println("B lik null");
            if(sendNyPass(bruker, error, true)){
                returnerMinSide(model, (BrukerB)sess.getAttribute("brukerBean"));
                return "MinSide";
            }
        }
        model.addAttribute("nyBruker", new Bruker());
        return "LeggTilBruker";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping(value="brukerOversikt")
    public String test(Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "BrukerOversikt";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping(value="NyttAbonemennt")
    public String nyttAbonemennt(Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "NyttAbonnement";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("BekreftBestilling")
    public String bekreftBestilling(Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("rom", new Rom());
            return "BekreftBestilling";
        }
        model.addAttribute("bruker", new Bruker());
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
        System.out.println("Bookinger: " + eventListe.size());
        for(RomBestilling best : eventListe){
            System.out.println(best.getBestillingsID());
        }
        long msek20Min = 20*60*1000;
        for (RomBestilling romBestilling : eventListe) {
            System.out.println(romBestilling.getStartDato().getTime()-now.getTime()+" "+msek20Min);
            if(romBestilling.getStartDato().getTime()-now.getTime()<msek20Min){
                romBestilling.setKlokkesjekk(true);
            }
        }
        model.addAttribute("event", new KalenderEvent());
        model.addAttribute("reservasjonsliste", eventListe);
        List<KalenderEvent> kalenderEventListe = service.getKalenderEventEier(brukerb);
        model.addAttribute("kalenderEventListe", kalenderEventListe);
        model.addAttribute("resultat", new SlettAbonnementValg());
        model.addAttribute("bruker", brukerb);
    }
    @RequestMapping("LeggTilFagLagre")
    public String leggTilFagLagre(@ModelAttribute(value="leggTilFagKlasse")LeggTilFagKlasse nyttFag ,Model model, BindingResult error, HttpSession sess){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            if(brukerb.getTilgangsniva()==2){
                List<Fag> fagListe = service.getFagKlasse(nyttFag.getKlasseID());
                String nyttFagID = nyttFag.getFagID();
                for (Fag fag : fagListe) {
                    if(nyttFagID.equalsIgnoreCase(fag.getFagID())){
                        LeggTilFagKlasse leggTilFagKlasse = new LeggTilFagKlasse();
                        model.addAttribute("melding", "feilmelding.leggTilNyttFag");
                        model.addAttribute("nyBruker", new Bruker());
                        model.addAttribute("passord", new Passord());
                        model.addAttribute("bruker", brukerb);
                        List<Klasse> listeKlasser = service.getAlleKlasser();
                        for (Klasse klasse : listeKlasser) {
                            leggTilFagKlasse.addKlasseListe(klasse.getNavn());
                        }
                        List<Fag> midlListe = service.getAlleFag();
                        ArrayList<String> fagListe2 = new ArrayList<String>();
                        for (Fag fag2 : midlListe) {
                            fagListe2.add(fag2.getFagID());
                        }
                        model.addAttribute("leggTilFagKlasse", leggTilFagKlasse);
                        model.addAttribute("fagListe", fagListe2);
                        return "LeggTilBruker";
                    }
                }
                if(service.leggTilFagKlasse(nyttFagID, nyttFag.getKlasseID())){
                    returnerMinSide(model, brukerb);
                    return "MinSide";
                }
            }else{
                returnerMinSide(model, brukerb);
                return "MinSide";
            }
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("NyttFag")
    private String nyttFag(@ModelAttribute("nyttFag")Fag fag, Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            if(service.leggTilFag(fag)){
                returnerMinSide(model, brukerb);
                return "MinSide";
            }
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
