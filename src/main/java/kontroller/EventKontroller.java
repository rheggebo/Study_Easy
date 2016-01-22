/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Abonemennt;
import beans.BrukerB;
import beans.KalenderEvent;
import beans.NyEvent;
import beans.Rom;
import beans.RomBestilling;
import beans.SlettAbonnementValg;
import email.Email;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.FormFinnRom;
import ui.FormVelgRom;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class EventKontroller {
    @Autowired
    Service service;
    
    @InitBinder
    public void initBinder(WebDataBinder binder){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping(value="omvei")
    public String omvei(HttpSession sess, Model model){
        
        model.addAttribute("nyHendelse", new NyEvent());
        return "OpprettHendelse";
    }
    @RequestMapping(value="OpprettHendelse")
    public String opprettHendelse(@ModelAttribute("nyHendelse") KalenderEvent event, @RequestParam("notat")String notat, @RequestParam("valg")String off, @RequestParam("startdato")Date startDato, @RequestParam("starttid")String startTid, @RequestParam("sluttdato")Date sluttDato, @RequestParam("starttid")String sluttTid, HttpSession sess, HttpServletResponse response, Model model, HttpServletRequest request){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        String stampString = "" + new Timestamp(startDato.getTime());
        stampString = (stampString.split(" "))[0] + " " + startTid + ":00";
        Timestamp start = Timestamp.valueOf(stampString);
        
        stampString = "" + new Timestamp(sluttDato.getTime());
        stampString = (stampString.split(" "))[0] + " " + startTid + ":00";
        Timestamp slutt = Timestamp.valueOf(stampString);
        
        //false er offentlig, true er privat
        boolean privat = false;
        if (off.equals("Privat")){
            privat = true;
        }
        
        if (slutt.before(start)){
            model.addAttribute("melding", "feilmelding.eventSluttForStart");
        }
        else{
            event.setStartTid(start);
            event.setSluttTid(slutt);
            event.setPrivat(privat);
            event.setNotat(notat);
            event.setEpost(brukerb.getEpost());
            event.setTilhorerEvent(0);
            event.setEierNavn(brukerb.getFornavn() + " " + brukerb.getEtternavn());

            service.leggTilEvent(event);
            
            Email email = new Email();
            List<Abonemennt> abonemennt = service.getBrukerAbonnement(brukerb.getEpost());
            String melding = "Ny heldelse lagt til av " + brukerb.getFornavn() + " " + brukerb.getEtternavn() + ". Tittel: " + event.getTittel() + ". Start: " + event.getStartTid() +
                    " , Slutt: " + event.getSluttTid() + ". Notat: " + event.getNotat();
            for (Abonemennt abn : abonemennt){
                email.sendEpost(abn.getEierid(), "Ny hendelse", melding);
            }
        }
        return "OpprettHendelse";
    }
    
    @RequestMapping(value="BestilleRom")
    public String bestilleRom(@ModelAttribute("rom") Rom rom, @RequestParam(required=false, value="privat") boolean privat,
            @RequestParam("startDato")Date startDato, @RequestParam("sluttDato")Date sluttDato, @RequestParam("fag")String fag,
            @RequestParam("notat")String notat, @RequestParam("tittel")String tittel,HttpSession sess, Model model){
        KalenderEvent ke = new KalenderEvent();
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        ke.setEpost(brukerb.getEpost());
        ke.setEierNavn(brukerb.getFornavn()+" "+brukerb.getEtternavn());
        startDato.setTime(startDato.getTime());
        sluttDato.setTime(0);
        ke.setStartTid(new Timestamp(startDato.getTime()));
        ke.setSluttTid(new Timestamp(sluttDato.getTime()));
        ke.setRom(rom.getRomNavn());
        ke.setFag(fag);
        ke.setType(brukerb.getTilgangsniva());
        ke.setPrivat(privat);
        ke.setNotat(notat);
        ke.setTittel(tittel);
        if(service.leggTilKalenderEvent(ke)){
            return "MinSide";
        }else{
            model.addAttribute("rom", rom);
            model.addAttribute("melding", "feilmelding.rombestillingGenerell");
            return "BestilleRom";
        } 
    }
    
    @RequestMapping("finnromdata")
    public String finnRom(@ModelAttribute FormFinnRom formFinnRom, Model model, HttpSession sess, HttpServletRequest req/*, 
            @RequestParam(value="notat", required=false)String notat, @RequestParam(value="tittel",required=false)String tittel, 
            @RequestParam(value="fag", required=false)String fag*/){
        KalenderEvent ke = new KalenderEvent();
        Rom rom = new Rom();
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        ke.setEpost(brukerb.getEpost());
        ke.setEierNavn(brukerb.getFornavn()+" "+brukerb.getEtternavn());
        ke.setRom(rom.getRomNavn());
        ke.setType(brukerb.getTilgangsniva());
        int fra = formFinnRom.getFraTid()/100;
        int til = formFinnRom.getVarighet();
        if(brukerb.getTilgangsniva()<1){
            //tilDato = fromFinnRom.getFraDato();
        }
        ke.setStartTid(new Timestamp(formFinnRom.getFraDato().getTime()+fra*3600000));
        ke.setSluttTid(new Timestamp(formFinnRom.getFraDato().getTime()+(fra+til)*3600000));
        ArrayList<String> innhold = new ArrayList<String>();
        
        if(formFinnRom.getSkjerm()>0){
            innhold.add("skjerm "+formFinnRom.getSkjerm());
        }
        if(formFinnRom.getTavle()>0){
            innhold.add("tavle "+formFinnRom.getTavle());
        }
        if(formFinnRom.getSitteplasser()>0){
            rom.setAntStolplasser(formFinnRom.getSitteplasser());
        }
        if(formFinnRom.getProjektor()>0){
            innhold.add("prosjektor "+formFinnRom.getProjektor());
        }
        if(formFinnRom.getStorrelse()>0){
            rom.setStorrelse(formFinnRom.getStorrelse());
        }
        
        if(formFinnRom.getRomtype().equalsIgnoreCase("Forelesningssal")){
            rom.setType(3);
        }else if(formFinnRom.getRomtype().equalsIgnoreCase("Moterom")){
            rom.setType(2);
        }else if(formFinnRom.getRomtype().equalsIgnoreCase("Grupperom")){
            rom.setType(1);
        }
        rom.setInnhold(innhold);
        List<Rom> liste = service.getRom(rom, ke, (formFinnRom.getStorrelse()>0), (formFinnRom.getSitteplasser()>0));
        model.addAttribute("liste", liste);
        model.addAttribute("event", ke);
        model.addAttribute("fraDato", formFinnRom.getFraDato());
        sess.setAttribute("asd", ke);
        req.setAttribute("sitteplass", true);
        System.out.println(formFinnRom.getSitteplasser());
        /*ke.setNotat(notat);
        ke.setTittel(tittel);*/        
        return "FinnRom";
    }
    
    @RequestMapping("BookRom")
    public String bookRom(@ModelAttribute("event")KalenderEvent event, HttpSession sess, Model model, HttpServletRequest req){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        KalenderEvent asd = (KalenderEvent)sess.getAttribute("asd");
        sess.removeAttribute("asd");
        String rom = event.getRom();
        String[] tab = rom.split(" ");
        event = asd;
        event.setRom(tab[1]);
        event.setTittel("testing");
        event.setNotat("notatet");
        if(service.leggTilBooking(event)){
            model.addAttribute("bruker", bruker);
            returnerMinSide(model, bruker);
            return "MinSide";
        }
        model.addAttribute("event", new KalenderEvent());
        return "FinnRom";
    }
    
    private void returnerMinSide(Model model, BrukerB brukerb){
        List<Abonemennt> liste = service.getAbonemenntFraBruker(brukerb);
        model.addAttribute("abonemenntListe", liste);
        KalenderEvent ke = new KalenderEvent();
        ke.setEpost(brukerb.getEpost());
        Date dato = Calendar.getInstance().getTime();
        Timestamp now = new Timestamp(dato.getTime());
        ke.setStartTid(now);
        List<RomBestilling> eventListe = service.getReserverteRom(ke);
        long msek20Min = 20*60*1000;
        for (RomBestilling romBestilling : eventListe) {
            System.out.println(now.getTime()-romBestilling.getStartDato().getTime()+" "+msek20Min);
            if(now.getTime()-romBestilling.getStartDato().getTime()<msek20Min){
                romBestilling.setKlokkesjekk(true);
            }
        }
        model.addAttribute("event", new KalenderEvent());
        model.addAttribute("reservasjonsliste", eventListe);
        model.addAttribute("bruker", brukerb);
        model.addAttribute("resultat", new SlettAbonnementValg());
    }
    
    @RequestMapping("VelgRomSok")
    public String velgRom(@ModelAttribute FormVelgRom formVelgRom, HttpSession sess, Model model){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        KalenderEvent ke = new KalenderEvent();
        int fra = formVelgRom.getFraTid()/100;
        int til = formVelgRom.getVarighet();
        ke.setStartTid(new Timestamp(formVelgRom.getFraDato().getTime()+fra*3600000));
        ke.setSluttTid(new Timestamp(formVelgRom.getFraDato().getTime()+til*3600000));
        System.out.println(ke.getSluttTid()+" "+ke.getSluttTid());
        ke.setType(bruker.getTilgangsniva()+1);
        List<Rom> liste = service.getRomSVG(ke);
        model.addAttribute("bruker", bruker);
        model.addAttribute("liste", liste);
        return "VelgRom";
    }
    
    @RequestMapping("VelgRomRed")
    public String velgRomRed(@ModelAttribute FormVelgRom formVelgRom,HttpSession sess, Model model){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        Rom rom = new Rom();
        System.out.println(rom.getRomNavn());
        rom.setRomID(formVelgRom.getRomId());
	rom = service.getRom(rom); 
        System.out.println(rom.getRomNavn());
        model.addAttribute("bruker", bruker);
        model.addAttribute("rom", rom);
        return "VelgRomRed";
    }
    
    @RequestMapping("SlettBooking")
    public String slettBooking(@ModelAttribute("event")KalenderEvent ke, HttpSession sess, Model model){
        BrukerB bruker = (BrukerB)sess.getAttribute("brukerBean");
        String[] info = ke.getRom().split(" ");
        String rom = info[1];
        String[] startDato = info[3].split("-");
        String[] startTid = info[4].split(":");
        String[] sluttDato = info[6].split("-");
        String[] sluttTid = info[7].split(":");
        ke.setEpost(bruker.getEpost());
        ke.setRom(rom);
        System.out.println("Oppretter timestamp "+startTid[2].substring(0,2));
        ke.setStartTid(new Timestamp(Integer.parseInt(startDato[0])-1900,Integer.parseInt(startDato[1])-1,Integer.parseInt(startDato[2]),
                Integer.parseInt(startTid[0]), Integer.parseInt(startTid[1]), Integer.parseInt(startTid[2].substring(0,2)), 0));
        ke.setSluttTid(new Timestamp(Integer.parseInt(sluttDato[0])-1900,Integer.parseInt(sluttDato[1])-1,Integer.parseInt(sluttDato[2]),
                Integer.parseInt(sluttTid[0]), Integer.parseInt(sluttTid[1]), Integer.parseInt(sluttTid[2].substring(0,2)), 0));
        System.out.println("Opprettet timestamp, skal slette booking");
        System.out.println(ke.getRom()+" "+ke.getStartTid()+" "+ke.getEpost());
        RomBestilling booking = service.getRomBooking(ke);
        if(booking.getTilhorerEvent() == 1){
            service.slettKalenderEvent(booking);
        }
        if(service.slettBooking(ke)){
            System.out.println("Slettet booking");
            returnerMinSide(model, bruker);
            return "MinSide";
        }
        System.out.println("skal legge til feilmelding");
        model.addAttribute("melding", "feilmelding.slettBooking");
        returnerMinSide(model, bruker);
        return "MinSide";
    }
}
