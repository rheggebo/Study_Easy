/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Bruker;
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
        HttpServletRequest request, HttpServletResponse response, @RequestParam(value="checkbox_1", required=false)boolean Ansatt,
        @RequestParam(value="checkbox_2", required=false)boolean Student,
        @RequestParam(value="checkbox_3", required=false)boolean Rom,
        @RequestParam(value="checkbox_4", required=false)boolean Klasse,
        @RequestParam(value="checkbox_5", required=false)boolean Fag) {
        
        Funksjoner fu= new Funksjoner();     
        String funker="DEt skjer ingenting!";
        String checkedAnsatt=request.getParameter("Ansatt");
        String checkedStudent=request.getParameter("Student");      
        String checkedFag=request.getParameter("Fag");
        String checkedRom=request.getParameter("Rom");
        String checkedKlasse=request.getParameter("Klasse");
        
      
        if("Ansatt".equals(checkedAnsatt)){
            //checkbox is selected
            out.println( "Ansatt er huket av!!");
        }
        
      
        
        
       model.addAttribute("liste",fu.getAlleSokeTreff(sok.getSokeord(), si, funker));
       for(int i=0;i<fu.liste.size();i++){
           out.println("<td>" + fu.liste.get(i).toString() + "<td>");           
        }
        return "SokeSide";  
    }
      /*
        (@RequestParam(value="checkbox_1", required=false)boolean forelesning,
                @RequestParam(value="checkbox_2", required=false)boolean Oving,
            @RequestParam(value="checkbox_3", required=false)boolean romreservasjon,
            @RequestParam(value="checkbox_4", required=false)boolean privatHendelse*/
          
   
}
    
   /** @RequestMapping(value="finnromdata")
    public String finnromdata(@ModelAttribute(value="finnrom") Rom rom, HttpServletRequest request, HttpServletResponse response){
       String romtype=request.getParameter("romtype");
       String antStolplasser=request.getParameter("num-stoler");
       String str=request.getParameter("størrelse");       
       String dato=request.getParameter("date");
       String[] checked=request.getParameter("")
       int antStoler=Integer.parseInt(antStolplasser);
       int st=Integer.parseInt(str);
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
       String d = format.format(new Date(dato)); 
       
       List alleRom=si.getAlleRom();
       for(int i=0;i<alleRom.size();i++){
       /*    if(si.getRomFraType(romtype)&&si.getRomFraStoerrelse(st)){
                for(iterer liste){
                out.println();
                }
           }
       }*/
       
        /**
        return "FinnRom";
            }**/

      
    /**
     * String[] select = new String[5];
        String str = "Testtreng";
        try {
            select=request.getParameterValues("Spes");
        } catch (Exception e) {
            str = "select=request.getParameterValues(\"Spes\") catcher exception";
        }
   */

