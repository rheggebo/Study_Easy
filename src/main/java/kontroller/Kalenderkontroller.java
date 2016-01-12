/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.BrukerB;
import beans.KalenderEvent;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.Service;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class Kalenderkontroller {
    @Autowired
    private Service service;

    
    @RequestMapping(value = "/events/getEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    String getEvents(HttpServletResponse response, HttpSession sess) {
            
        //kall til database for å finne relevant info.
        //ID, tittel, start, slutt, descr, rom, type, eiernavn, fag
        BrukerB brukerb = (BrukerB)sess.getAttribute("brukerBean");
        List<KalenderEvent> events = service.getAlleEventsFraBruker(brukerb);
        
        String tittel = events.get(0).getTittel();
        System.out.println(tittel);
        
        String[] farger = {"#FFA500", "#00FF7F", "#00BFFF", "#FFFF00"};
        
        String jsonSend = "";
        
        for (KalenderEvent event : events){
            System.out.println(event.getTittel() + " " + event.getType());
            System.out.println(event.getStartTid());
            String start = "" + event.getStartTid();
            String slutt = "" + event.getSluttTid();
            String descr = "Rom: " + event.getRom() + "<br>Notat: " + event.getNotat();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", event.getId());
            map.put("title", event.getTittel());
            map.put("start", start);
            map.put("end", slutt);
            map.put("description", descr);
            map.put("color", farger[event.getType()]);
            String json = new Gson().toJson(map);
            if (!jsonSend.isEmpty()){
                jsonSend += ", ";
            }
            jsonSend += json;
        }

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return "[" + jsonSend + "]";
    }
}
