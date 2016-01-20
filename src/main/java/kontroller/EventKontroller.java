/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.BrukerB;
import beans.KalenderEvent;
import beans.Rom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public String finnRom(@ModelAttribute("rom") Rom rom, @RequestParam(value="skjerm", required=false)boolean skjerm, 
            @RequestParam(value="antSkjerm", required=false)String antSkjerm, @RequestParam(value="tavle", required=false)boolean tavle, 
            @RequestParam(value="antTavle", required=false)Integer antTavle, @RequestParam(value="sitteplass", required=false)boolean sitteplass, 
            @RequestParam(value="antSitteplass", required=false)Integer antSitteplass, @RequestParam(value="prosjektor", required=false)boolean prosjektor, 
            @RequestParam(value="antProsjektor", required=false)Integer antProsjektor, @RequestParam(value="storrelse", required=false)boolean storrelse, 
            @RequestParam(value="storrelseNum", required=false)Integer storrelseNum, @RequestParam("romtype")String romtype, @RequestParam("fraTid")String fraTid,
            @RequestParam("varighet")String varighet, @RequestParam("fraDato")Date fraDato, @RequestParam(value="tilDato",required=false)Date tilDato, Model model, HttpSession sess, HttpServletRequest req/*, 
            @RequestParam(value="notat", required=false)String notat, @RequestParam(value="tittel",required=false)String tittel, 
            @RequestParam(value="fag", required=false)String fag*/){
        KalenderEvent ke = new KalenderEvent();
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        ke.setEpost(brukerb.getEpost());
        ke.setEierNavn(brukerb.getFornavn()+" "+brukerb.getEtternavn());
        ke.setRom(rom.getRomNavn());
        ke.setType(brukerb.getTilgangsniva());
        int fra = Integer.parseInt(fraTid)+5;
        int til = Integer.parseInt(varighet);
        if(brukerb.getTilgangsniva()<1){
            tilDato = fraDato;
        }
        ke.setStartTid(new Timestamp(fraDato.getTime()+fra*3600000));
        ke.setSluttTid(new Timestamp(tilDato.getTime()+til*3600000));
        ArrayList<String> innhold = new ArrayList<String>();
        System.out.println(skjerm+""+tavle+sitteplass+prosjektor+storrelse);
        if(skjerm){
            innhold.add("skjerm "+antSkjerm);
        }
        if(tavle){
            innhold.add("tavle "+tavle);
        }
        if(sitteplass){
            rom.setAntStolplasser(antSitteplass);
        }
        if(prosjektor){
            innhold.add("prosjektor "+antProsjektor);
        }
        if(storrelse){
            rom.setStorrelse(storrelseNum);
        }
        System.out.println(romtype);
        if(romtype.equalsIgnoreCase("Forelesningssal")){
            rom.setType(3);
        }else if(romtype.equalsIgnoreCase("Moterom")){
            rom.setType(2);
        }else if(romtype.equalsIgnoreCase("Grupperom")){
            rom.setType(1);
        }
        rom.setInnhold(innhold);
        List<Rom> liste = service.getRom(rom, ke, storrelse, sitteplass);
        model.addAttribute("liste", liste);
        model.addAttribute("event", ke);
        model.addAttribute("fraDato", fraDato);
        sess.setAttribute("asd", ke);
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
            return "MinSide";
        }
        model.addAttribute("event", new KalenderEvent());
        return "FinnRom";
    }
    
    @RequestMapping("VelgRomSok")
    public String velgRom(@RequestParam("fraDato")Date fraDato, @RequestParam("fraTid")String fraTid, 
            @RequestParam("varighet")String varighet, HttpSession sess, Model model){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        KalenderEvent ke = new KalenderEvent();
        int fra = Integer.parseInt(fraTid)+5;
        int til = Integer.parseInt(varighet);
        ke.setStartTid(new Timestamp(fraDato.getTime()+fra*3600000));
        ke.setSluttTid(new Timestamp(fraDato.getTime()+til*3600000));
        ke.setType(bruker.getTilgangsniva()+1);
        List<Rom> liste = service.getRomSVG(ke);
        model.addAttribute("liste", liste);
        return "VelgRom";
    }
}
