/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Abonemennt;
import beans.Bruker;
import beans.BrukerB;
import beans.Rom;
import beans.Sok;
import beans.SokeValg;
import email.Email;
import java.io.IOException;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import verktøy.Funksjoner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.Param;
import service.Service;
import ui.FormVelgRom;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class SokeKontroller {
    
    @Autowired        
    private Service si;
    
    @RequestMapping("FinnRomSok")
    public void finnRomSok(@ModelAttribute("rom") Rom rom){
        
    }
     
    @RequestMapping(value="search")
    public String searchView(@ModelAttribute(value="soke")Sok sok, Model model, 
        HttpServletRequest request, HttpServletResponse response) {
        
        Funksjoner fu= new Funksjoner();      
        String [] checkboxes=request.getParameterValues("Spes");       
       
        model.addAttribute("liste",fu.getAlleSokeTreff(sok.getSokeord(), si, checkboxes));
        for(int i=0;i<fu.liste.size();i++){
           out.println("<td>" + fu.liste.get(i).toString() + "<td>");          
        }
        model.addAttribute("bruker", new BrukerB());
        model.addAttribute("resultat", new SokeValg());


        return "SokeSide";  
    }  
    @RequestMapping(value="abonnere")
    public String fetchData1(@ModelAttribute("resultat") SokeValg sv, HttpSession sess, HttpServletResponse response, Model model, HttpServletRequest request){ 
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");        
        String[] split = sv.getResultat().split(":");
        if("Abonner".equals(request.getParameter("knappTilAbonnement"))){
            if (split[0].equals("Ansatt") || split[0].equals("Student")){
                //person
                if (bruker.getEpost().equals(split[2].trim())){
                    model.addAttribute("melding", "feilmelding.duplikatAbonnement");
                    return "SokeSide";
                }
                try{
                    si.leggTilAbonemennt(new Abonemennt(bruker.getEpost(), split[2].trim(), 0));
                }
                catch(Exception e){
                    model.addAttribute("melding", "feilmelding.duplikatAbonnement");
                }
                return "SokeSide";
            }
            else if (split[0].equals("Fag")){
                String[] split2 = (split[1].trim()).split(" ");
                try{
                    si.leggTilAbonemennt(new Abonemennt(bruker.getEpost(), split2[0].trim(), 1));
                    
                }
                catch(Exception e){
                    model.addAttribute("melding", "feilmelding.duplikatAbonnement");
                }
            }
            else if (split[0].equals("Klasse")){
                String[] split2 = (split[1].trim()).split(",");

                String[] fagsplit = (split[2].trim()).split(" ");
                for (int i = 0; i < fagsplit.length; i++){
                    try{
                        si.leggTilAbonemennt(new Abonemennt(bruker.getEpost(), fagsplit[i], 1));
                    }
                    catch(Exception e){
                        System.out.println("heiho");
                        model.addAttribute("melding", "feilmelding.duplikatAbonnement");
                    }
                }
            }
        }
        return "SokeSide";             
        } 
    @RequestMapping(value="seKart")
    public String fetchData2(@ModelAttribute FormVelgRom formVelgRom, @ModelAttribute("resultat") SokeValg sv, HttpSession sess, HttpServletResponse response, Model model, HttpServletRequest request){ 
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");        
        String[] split = sv.getResultat().split(":");
        List<String> liste = Arrays.asList(split);
        model.addAttribute("liste", liste);
        return "VelgRom";
        } 
    
    }


    
