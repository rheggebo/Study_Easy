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
        HttpServletRequest request, HttpServletResponse response) {
        
        Funksjoner fu= new Funksjoner();      
        String [] checkboxes=request.getParameterValues("Spes");       
       
        model.addAttribute("liste",fu.getAlleSokeTreff(sok.getSokeord(), si, checkboxes));
        for(int i=0;i<fu.liste.size();i++){
           out.println("<td>" + fu.liste.get(i).toString() + "<td>");           
        }
        return "SokeSide";  
    }   
}
    