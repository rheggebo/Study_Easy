/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Rom;
import beans.Sok;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.util.ArrayList;
import verktøy.Funksjoner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  /*
    @RequestMapping("search") 
    public void KontrollerListe(@ModelAttribute("sok") Sok sok) throws IOException{
        // 
       //Here I want to invoke gotoRegister
        Funksjoner.getInstance().getListe();
    }
    */
    @RequestMapping("Search")
    public String sok(@ModelAttribute ("sok") Sok sok ){
       /**Funksjoner f=new Funksjoner();
       String s=sok.getSokeord();
       ArrayList list=new ArrayList();
       for(int i=0;i<f.getAlleSokeTreff(s).size();i++){
           list.add(f.getAlleSokeTreff(s).get(i));
       }
       //sok.getSokeord().toString();
       return list;*/
        
        Funksjoner f=new Funksjoner();
        String s=sok.getSokeord();
        ArrayList list=new ArrayList();
        for(int i=0;i<f.getListe().size();i++){
           list.add(f.getListe().get(i));
       }
        for(int i=0;i<list.size();i++){
            
        }
       //sok.getSokeord().toString();
      // return list;
        return "SokeSide";
    }
    /**Sok s=new Sok();
        if(session.getAttribute("sokeord")!=null){
            s=(Sok)session.getAttribute("sokeord");
        }
        Funksjoner f=new Funksjoner();
        ArrayList<Object> ls=new ArrayList();
        ls=f.getAlleSokeTreff(s); 
   */
}
