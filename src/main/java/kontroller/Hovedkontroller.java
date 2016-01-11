/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Bruker;
import beans.BrukerB;
import beans.Klasse;
import beans.Passord;
import email.Email;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Service;
import verktøy.PasswordHasher;
import verktøy.Passordgenerator;

/**
 * 
 * @author Stein-Erik
 */
@Controller
public class Hovedkontroller {
    
    @Autowired
    private Service service;
    private Passordgenerator generator = new Passordgenerator();
    private Bruker testBruker = new Bruker();
    
    @RequestMapping(value = "/*")
    public String start(Model model, HttpSession sess){
        testBruker.setNavn("Stein-Erik Bjørnnes");
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
            if(service.sjekkPassord(bruker.getEpost(), bruker.getPassord())){
                BrukerB brukerBean = new BrukerB(service.hentBruker(bruker));
                brukerBean.setInnlogget(true);
                sess.setAttribute("brukerBean", brukerBean);
                return "Forside";
            }    
        }
        model.addAttribute("melding", "feilmelding.login");
        bruker.setPassord("");
        return "Innlogging";
    }
    
    @RequestMapping(value = "glemtPassord")
    public String glemtPassord(Model model){
        model.addAttribute("bruker", new Bruker());
        return "Glemsk";
    }
    
    @RequestMapping(value="kalenderTest")
    public String kalenderTest (Model model, HttpServletRequest request){
        return "fullcalendar/demos/basic_views";
    }
    @RequestMapping(value="sendNyttPassord")
    public String glemsk(@ModelAttribute("bruker") Bruker bruker, Model model, HttpServletRequest request, Errors errors){
        String sjekk = bruker.getEpost();
        Bruker temp = null;
        try{
            temp = service.hentBruker(sjekk);
        }catch (Exception e){
            
        }
        
        if(temp != null){
            if(sendNyPass(temp, errors)){
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
    
    
    @RequestMapping("MinSide")
    public String minSide(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        return "MinSide";
    }
    
    @RequestMapping("MinSideRed")
    public String minSideRed(HttpSession sess, Model model){
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        return "MinSideRed";
    }
    
    @RequestMapping("VelgRom")
    public String velgRom(){
        return "VelgRom";
    }
    
    @RequestMapping("Forside")
    public String forside(){
        return "Forside";
    }
    
    @RequestMapping("FinnRom")
    public String finnRom(){
        return "FinnRom";
    }
    @RequestMapping("SokeSide")
    public String sokeSide(){
        return "SokeSide";
    }
    
    @RequestMapping("loggUt")
    public String loggUt(HttpSession sess, Model model){
        sess.removeAttribute("brukerbean");
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
    
    /*private String genererPassord(){
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?0123456789";
        /*char[] nyttPass = new char[8];
        Random rng = new Random();
        for (int i = 0; i < nyttPass.length; i++) {
            nyttPass[i] = alfabet.charAt(rng.nextInt(alfabet.length()));
        }
        String nyttPassord = new String(nyttPass);
        if(nyttPassord.toLowerCase().equals(nyttPassord) || nyttPassord.toUpperCase().equals(nyttPassord)){
            nyttPassord = genererPassord();
        }
        String nyttPassord = RandomStringUtils.random( 15, characters );
        System.out.println(nyttPassord);
        return nyttPassord;
    }*/
    
    private String genererPassord(Errors errors){
        String nyttPassord = generator.genererPassord();
        Passord pass = new Passord();
        pass.setPassord(nyttPassord);
        pass.validate(pass, errors);
        if(errors.hasErrors()){
            System.out.println(errors.getErrorCount()+ nyttPassord);
            nyttPassord = genererPassord(errors);
        }
        return nyttPassord;
    }
    
    private Boolean sendNyPass(Bruker temp, Errors errors){
        String mottaker = temp.getEpost();
        String tema = "Nytt passord for bruker: "+temp.getEpost();
        String nyttPassord = genererPassord(errors);
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
    
    
    
    
}
