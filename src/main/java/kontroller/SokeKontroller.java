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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
        HttpServletRequest request) throws Exception {
        String[] select = new String[5];
        String str = "Testtreng";
        try {
            select=request.getParameterValues("Spes");
        } catch (Exception e) {
            str = "select=request.getParameterValues(\"Spes\") catcher exception";
        }
        /*for (Object select1 : select) {
            if (select1.equals("Ansatt")) {
                
            } 
        }*/
            
        Funksjoner fu= new Funksjoner();
        model.addAttribute("liste",fu.getAlleSokeTreff(sok.getSokeord(),si, select, str));
        return "SokeSide";
            
            }
	}
      
    /**
   */

