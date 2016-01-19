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
import java.io.IOException;
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        

        model.addAttribute("bruker", new BrukerB());

        return "SokeSide";  
    }  
    @RequestMapping(value="BrukerOversikt")
    public String fetchData1(@ModelAttribute("bruker") BrukerB b, HttpSession sess, HttpServletResponse response, HttpServletRequest request){ 
        BrukerB bruker = (BrukerB) sess.getAttribute("brukerBean");        
        String[] split = b.getEtternavn().split(":");
        if("Abonner".equals(request.getParameter("knappTilAbonnement"))){
            if (split[0].equals("Ansatt") || split[0].equals("Student")){
                //person
                si.leggTilAbonemennt(new Abonemennt(bruker.getEpost(), split[2].trim(), 0));
                return "SokeSide";
            }
                for (int i = 0; i < split.length; i++){
                    System.out.println("Plass " + i + ": " + split[i]);
                    }
            }else if("Se kart".equals(request.getParameter("knappTilKart"))){
                //sørg for at riktig etasje vises           
                return "VelgRom";
            }
        return "SokeSide";             
        } 
        
    }


    
