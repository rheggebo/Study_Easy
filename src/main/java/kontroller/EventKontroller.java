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

/**
 *
 * @author Stein-Erik
 */
@Controller
public class EventKontroller {
    @Autowired
    Service service;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
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
}
