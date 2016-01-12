/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Rom;
import beans.Sok;
import java.util.ArrayList;
import verktøy.Funksjoner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class SokeKontroller {
    @RequestMapping("FinnRomSok")
    public void finnRomSok(@ModelAttribute("rom") Rom rom){
        
    }
    @RequestMapping("search")
    public String sok(@ModelAttribute("sok") Sok sok){
        Funksjoner funk=new Funksjoner();
        String s=sok.getSokeord();
        funk.getAlleSokeTreff(s);
        return "SokeSide"; 
        
    }
    
}
