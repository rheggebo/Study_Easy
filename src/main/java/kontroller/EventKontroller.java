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
import java.util.GregorianCalendar;
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
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import ui.FormFinnRom;
import ui.FormRedRom;
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
    
    /*@RequestMapping(value="omvei")
    public String omvei(HttpSession sess, Model model){
        model.addAttribute("nyHendelse", new NyEvent());
        return "OpprettHendelse";
    }*/
    
    @RequestMapping(value="OpprettHendelse")
    public String opprettHendelse(@ModelAttribute("nyHendelse") KalenderEvent event, @RequestParam("notat")String notat, @RequestParam("valg")String off, @RequestParam("startdato")Date startDato, @RequestParam("starttid")String startTid, @RequestParam("sluttdato")Date sluttDato, @RequestParam("starttid")String sluttTid, HttpSession sess, HttpServletResponse response, Model model, HttpServletRequest request){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
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
            
            if (!privat){
                Email email = new Email();
                List<Abonemennt> abonemennt = service.getBrukerAbonnement(brukerb.getEpost());
                String melding = "Ny heldelse lagt til av " + brukerb.getFornavn() + " " + brukerb.getEtternavn() + ". Tittel: " + event.getTittel() + ". Start: " + event.getStartTid() +
                        " , Slutt: " + event.getSluttTid() + ". Notat: " + event.getNotat();
                for (Abonemennt abn : abonemennt){
                    email.sendEpost(abn.getEierid(), "Ny hendelse", melding);
                }
            }
        }
        return "OpprettHendelse";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("finnromdata")
    public String finnRom(@ModelAttribute("formFinnRom") FormFinnRom formFinnRom, Model model, HttpSession sess, HttpServletRequest req){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            KalenderEvent ke = new KalenderEvent();
            Rom rom = new Rom();
            ke.setEpost(brukerb.getEpost());
            ke.setEierNavn(brukerb.getFornavn()+" "+brukerb.getEtternavn());
            ke.setRom(rom.getRomNavn());
            ke.setType(brukerb.getTilgangsniva());
            int fra = formFinnRom.getFraTid()/100;
            int til = formFinnRom.getVarighet();

            if (!sjekkDatoTilgang(brukerb, formFinnRom.getFraDato(), fra)){
                model.addAttribute("feilMeldingSokRom", "feilmelding.feilMeldingSokRom" );
                model.addAttribute("feilISok", true );
                return "FinnRom";
            }

            if(brukerb.getTilgangsniva()<1){
                //tilDato = fromFinnRom.getFraDato();
            }
            ke.setStartTid(new Timestamp(formFinnRom.getFraDato().getTime()+fra*3600000+1000));
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
            rom.setInnhold(innhold);
            if(brukerb.getTilgangsniva()>0){
                if(formFinnRom.getRomtype().equalsIgnoreCase("Forelesningssal")){
                rom.setType(3);
                }else if(formFinnRom.getRomtype().equalsIgnoreCase("Klasserom")){
                    rom.setType(2);
                }else if(formFinnRom.getRomtype().equalsIgnoreCase("Grupperom")){
                    rom.setType(1);
                }
            }else{
                rom.setType(1);
            }
            List<Fag> listeFag = service.getAlleFag();
            for (Fag fag : listeFag) {
                formFinnRom.addFagListe(fag.getFagID());
            }
            List<Rom> liste = service.getRom(rom, ke, (formFinnRom.getStorrelse()>0), (formFinnRom.getSitteplasser()>0));
            sess.setAttribute("asd", ke);
            model.addAttribute("liste", liste);
            model.addAttribute("formFinnRom", formFinnRom);
            model.addAttribute("bruker", brukerb);
            model.addAttribute("event", new KalenderEvent());
            sess.setAttribute("fFR", formFinnRom);
            return "FinnRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("BookRom")
    public String bookRom(@ModelAttribute("event")KalenderEvent event, HttpSession sess, Model model, HttpServletRequest req){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            KalenderEvent asd = (KalenderEvent)sess.getAttribute("asd");
        sess.removeAttribute("asd");
        FormFinnRom fFR = (FormFinnRom) sess.getAttribute("fFR");
        sess.removeAttribute("fFR");
        String rom = event.getRom();
        String[] tab = rom.split(" ");
        event = asd;
        event.setRom(tab[1]);
        String type = fFR.getType();
        System.out.println(type);
        if(type.equalsIgnoreCase("Ikke lag hendelse")){
            if(service.erRomLedig(event)){
                if(service.leggTilBooking(event)){
                    returnerMinSide(model, brukerb);
                    return "MinSide";
                }
            }
        }else if(type.equalsIgnoreCase("Privat")){
            event.setPrivat(true);
            event.setType(2);
        }else if(type.equalsIgnoreCase("Forelesning")){
            event.setPrivat(false);
            event.setType(0);
        }else if(type.equalsIgnoreCase("MÃ¸te")){
            event.setPrivat(false);
            event.setType(1);
        }
        System.out.println(event.getType());
        event.setTittel(fFR.getTittel());
        event.setNotat(fFR.getNotat());
        System.out.println(fFR.getFag());
        if(!fFR.getFag().equalsIgnoreCase("Velg fag")){
            event.setFag(fFR.getFag());
        }else{
            event.setFag(null);
        }
        event.setTilhorerEvent(1);
        if(service.erRomLedig(event)){
            if(service.leggTilBooking(event)){
                RomBestilling rb = service.getRomBooking(event);
                event.setBestillingsID(rb.getBestillingsID());
                if(service.leggTilEvent(event)){
                    returnerMinSide(model, brukerb);
                    return "MinSide";
                }
            }
        }
        model.addAttribute("formFinnRom", new FormFinnRom());
        model.addAttribute("event", new KalenderEvent());
        model.addAttribute("bruker", brukerb);
        return "FinnRom";
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
        model.addAttribute("event", new KalenderEvent());
        model.addAttribute("reservasjonsliste", eventListe);
        List<KalenderEvent> kalenderEventListe = service.getKalenderEventEier(brukerb);
        model.addAttribute("kalenderEventListe", kalenderEventListe);
        model.addAttribute("resultat", new SlettAbonnementValg());
        model.addAttribute("bruker", brukerb);
    }
    // Velg rom siden:
    @RequestMapping("VelgRomSok")
    public String velgRomSoke(@ModelAttribute FormVelgRom formVelgRom, HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
        KalenderEvent ke = new KalenderEvent();
        int fra = formVelgRom.getFraTid()/100;
        int til = formVelgRom.getVarighet();
        if(!sjekkDatoTilgang(brukerb, formVelgRom.getFraDato(), fra)) {
            model.addAttribute("opptatt", true );
            model.addAttribute("feilMeldingReservereRom", "feilmelding.feilMeldingSokRom");
            return "VelgRom";
        }
        ke.setStartTid(new Timestamp(formVelgRom.getFraDato().getTime()+fra*3600000+1000));
        ke.setSluttTid(new Timestamp(formVelgRom.getFraDato().getTime()+(fra+til)*3600000));
        Rom r = new Rom();
        r.setType(brukerb.getTilgangsniva()+1);
        r.setInnhold(new ArrayList<String>(0));
        System.out.println(ke.getStartTid()+" "+ke.getSluttTid()+" "+r.getType());
        List<Rom> liste = service.getRomSVG(r, ke); 
        model.addAttribute("liste", liste);
        return "VelgRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    @RequestMapping("VelgRomRed")
    public String velgRomRed(@ModelAttribute FormVelgRom formVelgRom,HttpSession sess, Model model){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        model.addAttribute("bruker", bruker);
        if(bruker != null && bruker.isInnlogget()){
            System.out.println(bruker.getTilgangsniva());
            if(bruker.getTilgangsniva()>1){
                FormRedRom formRedRom = new FormRedRom();
                try{
                    Rom rom = new Rom();
                    System.out.println(formVelgRom.getRomId().split(" ")[0]);
                    rom.setRomID(formVelgRom.getRomId().split(" ")[0]);
                    rom = service.getRom(rom); 
                    formRedRom.setRomId(rom.getRomID());
                    formRedRom.setRomNavn(rom.getRomNavn());
                    formRedRom.setRomType(rom.getType());
                    formRedRom.setAntSittePl(rom.getAntStolplasser());
                    formRedRom.setRomStr(rom.getStorrelse());
                    formRedRom.setAntProsjektorer(0);
                    formRedRom.setAntSkjermer(0);
                    formRedRom.setAntTavler(0);
                    List<String> innhold = service.getAlleInnholdRom(rom);
                    for (String string : innhold) {
                        String[] tab = string.split(" ");
                        if(tab[0].equalsIgnoreCase("Skjerm")){
                            formRedRom.setAntSkjermer(Integer.parseInt(tab[1]));
                        }else if(tab[0].equalsIgnoreCase("Tavle")){
                            formRedRom.setAntSkjermer(Integer.parseInt(tab[1]));
                        }else if(tab[0].equalsIgnoreCase("Prosjektor")){
                            formRedRom.setAntSkjermer(Integer.parseInt(tab[1]));
                        }
                    }
                }catch(Exception e){
                    System.out.println(e);
                    return "VelgRom";
                }
                model.addAttribute("formRedRom", formRedRom);
                sess.setAttribute("romID", formVelgRom.getRomId().split(" ")[0]);
                return "VelgRomRed";
            }else{
                return "VelgRom";
            }
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
     @RequestMapping("EndreRom")
    public String velgRomRed(@ModelAttribute("formRedRom") FormRedRom formRedRom, @ModelAttribute FormVelgRom formVelgRom, Model model, HttpSession sess){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            Rom rom = new Rom();
            String romID = (String)sess.getAttribute("romID");
            sess.removeAttribute("romID");
            rom.setRomID(romID);
            formRedRom.setRomId(romID);
            rom.setRomNavn(formRedRom.getRomNavn());
            rom.setStorrelse(formRedRom.getRomStr());
            rom.setAntStolplasser(formRedRom.getAntSittePl());
            String[] innhold = {"Skjerm", ""+formRedRom.getAntSkjermer(), "Tavle", ""+formRedRom.getAntTavler(),"Prosjektor",""+formRedRom.getAntTavler()};
            try{
                service.oppdaterRom(rom);
                service.oppdaterInnholdRom(rom.getRomID(), innhold);

            }catch(Exception e){       
                model.addAttribute("redRomForm", formRedRom);
                model.addAttribute("melding", "feilmelding.oppdaterRom");
                return "VelgRomRed";
            }
            model.addAttribute("redRomForm", new FormVelgRom());
            return "VelgRom";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
 
    }
    
    @RequestMapping("VelgRomReserver")
    public String velgRomReserver(@ModelAttribute FormVelgRom formVelgRom,HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            model.addAttribute("bruker", brukerb);
            String [] romID =  formVelgRom.getRomId().split(" ");
            System.out.println(romID.length+ "er lengden ");
            if (romID.length==2){
                model.addAttribute("opptatt", true );
                return "VelgRom";
            }
            Rom rom = new Rom();
            rom.setRomID(romID[0]);
            KalenderEvent ke = new KalenderEvent();
            ke.setRom(formVelgRom.getRomId());
            int fra = formVelgRom.getFraTid()/100;
            int til = formVelgRom.getVarighet();
            if(!sjekkDatoTilgang(brukerb, formVelgRom.getFraDato(), fra)) {
                model.addAttribute("feilMeldingReservereRom", "feilmelding.feilMeldingReservereRom");
                return "VelgRom";
            }
            ke.setStartTid(new Timestamp(formVelgRom.getFraDato().getTime()+fra*3600000+1000));
            ke.setSluttTid(new Timestamp(formVelgRom.getFraDato().getTime()+(fra+til)*3600000));
            ke.setEpost(brukerb.getEpost());
            ke.setTilhorerEvent(0);
            try{
                if (service.erRomLedig(ke)){
                    System.out.println("La vi til noe?" );
                    service.leggTilBooking(ke);
                }else{
                    model.addAttribute("feilMeldingReservereRom", "feilmelding.feilMeldingReservereRom");
                    return "VelgRom";
                }            
            }catch(Exception e){
                model.addAttribute("feilMeldingReservereRom", "feilmelding.feilMeldingReservereRom");
                return "VelgRom";
            }
            return "Forside";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    } 
   
    @RequestMapping("SlettBooking")
    public String slettBooking(@ModelAttribute("event")KalenderEvent ke, HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            String[] info = ke.getRom().split(" ");
            String rom = info[1];
            String[] startDato = info[3].split("-");
            String[] startTid = info[4].split(":");
            String[] sluttDato = info[6].split("-");
            String[] sluttTid = info[7].split(":");
            ke.setEpost(brukerb.getEpost());
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
                returnerMinSide(model, brukerb);
                return "MinSide";
            }
            System.out.println("skal legge til feilmelding");
            model.addAttribute("melding", "feilmelding.slettBooking");
            returnerMinSide(model, brukerb);
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    private boolean sjekkDatoTilgang(BrukerB bruker, Date fraDato, int fraTid) {
        Calendar rightNow = Calendar.getInstance();
        Calendar gregorian = new GregorianCalendar();
        gregorian.setTime(rightNow.getTime());
        gregorian.add(Calendar.DATE, 14);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY) + 1;
        Date date = rightNow.getTime();
        if(fraDato.getYear()==(date.getYear())) {
            if(fraDato.getMonth() == (date.getMonth())) {
                if(fraDato.getDay() == (date.getDay())) {
                    if(hour > fraTid) {
                        return false;
                    }
                }
            }
        }
        
        if(bruker.getTilgangsniva() == 0) {
            if(fraDato.after(gregorian.getTime())) {
                return false;
            }
        }

        return true;
    }
    
    @RequestMapping("BekreftBooking")
    public String bekreftBooking(@ModelAttribute("event")KalenderEvent ke, HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            String[] info = ke.getRom().split(" ");
            String rom = info[1];
            String[] startDato = info[3].split("-");
            String[] startTid = info[4].split(":");
            String[] sluttDato = info[6].split("-");
            String[] sluttTid = info[7].split(":");
            ke.setEpost(brukerb.getEpost());
            ke.setRom(rom);
            ke.setStartTid(new Timestamp(Integer.parseInt(startDato[0])-1900,Integer.parseInt(startDato[1])-1,Integer.parseInt(startDato[2]),
                    Integer.parseInt(startTid[0]), Integer.parseInt(startTid[1]), Integer.parseInt(startTid[2].substring(0,2)), 0));
            ke.setSluttTid(new Timestamp(Integer.parseInt(sluttDato[0])-1900,Integer.parseInt(sluttDato[1])-1,Integer.parseInt(sluttDato[2]),
                    Integer.parseInt(sluttTid[0]), Integer.parseInt(sluttTid[1]), Integer.parseInt(sluttTid[2].substring(0,2)), 0));
            //Bekreft oppmøte for rom. 
            returnerMinSide(model, brukerb);
            return "MinSide";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
