/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import com.google.gson.Gson;
import beans.Bruker;
import beans.BrukerB;
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
    
    @RequestMapping(value = "glemtPassord")
    public String glemtPassord(Model model){
        model.addAttribute("bruker", new Bruker());
        return "Glemsk";
    }
    
    @RequestMapping(value="kalenderTest")
    public String kalenderTest (Model model, HttpServletRequest request){
        return "kalenderTest";
    }
    @RequestMapping(value="sendNyttPassord")
    public String glemsk(@ModelAttribute("bruker") Bruker bruker, Model model, HttpServletRequest request, Errors errors){
        String sjekk = bruker.getEpost();
        Bruker temp;
        /*List<Bruker> tabell = service.getAlleBrukere();
        for (Bruker bruker1 : tabell) {
            if(bruker1.getEpost().equals(sjekk)){
                temp = service.hentBruker(sjekk);
                if(sendNyPass(temp)){
                    return "EmailRedirect";
                }else{
                    model.addAttribute("melding", "feilmelding.email");
                    return "Glemsk";
                }
            }
        }*/
        temp = service.hentBruker(sjekk);
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
    
@RequestMapping(value = "/vacation/getVacation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getVacation(HttpServletResponse response) {
        
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 111);
        map.put("title", "event1");
        map.put("start", "2016-1-4");
        map.put("url", "http://yahoo.com/");

        // Convert to JSON string.
        String json = new Gson().toJson(map);
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 121);
        map2.put("title", "event2");
        map2.put("start", "2016-01-15");
        
        String json2 = new Gson().toJson(map2);
        
        json +=", " + json2;

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return "[" + json + "]";
    }
    
    
    @RequestMapping("MinSide")
    public String minSide(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        if(brukerb != null && brukerb.isInnlogget()){
            return "MinSide";
        }
        return "Innlogging";
    }
    
    @RequestMapping("MinSideRed")
    public String minSideRed(HttpSession sess, Model model){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        model.addAttribute("bruker", testBruker);
        if(brukerb != null && brukerb.isInnlogget()){
            return "MinSideRed";
        }
        return "Innlogging";
    }
    
    @RequestMapping("VelgRom")
    public String velgRom(HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "VelgRom";
        }
        return "Innlogging";
    }
    
    @RequestMapping("Forside")
    public String forside(HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "Forside";
        }
        return "Innlogging";
    }
    
    @RequestMapping("FinnRom")
    public String finnRom(Model model, HttpSession sess){
        model.addAttribute("rom", new Rom());
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "FinnRom";
        }
        return "Innlogging";
    }
    
    @RequestMapping("FinnRomSok")
    public void finnRomSok(@ModelAttribute("rom") Rom rom){
        
    }
    
    @RequestMapping("EndrePassord")
    public String endrePassord(HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "";
        }
        return "Innlogging";
    }
    
    @RequestMapping("SokeSide")
    public String sokeSide(HttpSession sess){
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            return "SokeSide";
        }
        return "Innlogging";
    }
    
    @RequestMapping("loggUt")
    public String loggUt(HttpSession sess, Model model){
        sess.removeAttribute("brukerBean");
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