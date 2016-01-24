/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import beans.Bruker;
import beans.BrukerB;
import beans.KalenderEvent;
import beans.RomBestilling;
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
import org.springframework.web.bind.annotation.RequestParam;
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
    private boolean[] vis = {true, true, true, true};

    
    @RequestMapping(value = "/events/getEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    String getEvents(HttpServletResponse response, HttpSession sess, Model model) {
            
        //kall til database for å finne relevant info.
        //ID, tittel, start, slutt, descr, rom, type, eiernavn, fag
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            List<KalenderEvent> events = service.getAlleEventsFraBruker(brukerb);
            String tittel = "";
            try{
                 tittel = events.get(0).getTittel();
            }catch(Exception e){

            }


            //0: forelesning, 1: øving, 2: privat ting, 3: romreservasjon
            String[] farger = {"#00BFFF", "#00FF7F", "#FFFF00", "#FFA500"};

            String jsonSend = "";

            for (KalenderEvent event : events){
                System.out.println("Event navn: " + event.getTittel() + " Event type: " + event.getType() + ". vis: " + vis[event.getType()]);
                if (vis[event.getType()]){
                    String start = "" + event.getStartTid();
                    String slutt = "" + event.getSluttTid();

                    String descr = "";
                    if (event.getFag() != null){
                        descr += "Fag: " + event.getFag() + "<br>";
                    }
                    if (event.getRom() != null){
                        descr += "Rom: <a href='" + event.getRom() + "'>" + event.getRom() + "</a><br>";
                    }
                    if (event.getNotat() != null){
                        descr += "Notat: " + event.getNotat();
                    }
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
            }
            List<RomBestilling> bestillinger = service.getAlleBestillingerFraBruker(brukerb);
            for (RomBestilling bestilling : bestillinger){
                if (vis[3]){
                    if (bestilling.getTilhorerEvent() == 0){

                        String start = "" + bestilling.getStartDato();
                        String slutt = "" + bestilling.getSluttDato();

                        String descr = "Rom: <a href='" + bestilling.getRomId() + "'>" + bestilling.getRomId() + "</a>";
                        descr += "<br>Fra: " + start + "<br>Til: " + slutt;
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("title", "Rombestilling");
                        map.put("start", start);
                        map.put("end", slutt);
                        map.put("color", farger[3]);
                        map.put("description", descr);

                        String json = new Gson().toJson(map);
                        if (!jsonSend.isEmpty()){
                            jsonSend += ", ";
                        }
                        jsonSend += json;
                    }
                    else{

                    }
                }

            }
            vis[0] = true;
            vis[1] = true;
            vis[2] = true;
            vis[3] = true;
            // Write JSON string.
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            return "[" + jsonSend + "]";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
   @RequestMapping(value="kalenderEventCheck")
    public String asdasda(HttpServletRequest request, HttpSession sess, Model model){
        /*req.setAttribute("checkbox1", forelesning);
        req.setAttribute("checkbox2", Oving);
        req.setAttribute("checkbox3", romreservasjon);
        req.setAttribute("checkbox4", privatHendelse);*/
        BrukerB brukerb = (BrukerB) sess.getAttribute("brukerBean");
        if(brukerb != null && brukerb.isInnlogget()){
            String [] checkboxes=request.getParameterValues("farger");
            for (int i = 0; i < checkboxes.length; i++){
                System.out.println(checkboxes[i]);
            }
            for (int i = 0; i < vis.length; i++){
                vis[i] = false;
            }
            for (int i = 0; i < checkboxes.length; i++){
                int parsed = Integer.parseInt(checkboxes[i]);
                vis[parsed] = true; 
            }


            return "Forside";
        }
        model.addAttribute("bruker", new Bruker());
        return "Innlogging";
    }
}
