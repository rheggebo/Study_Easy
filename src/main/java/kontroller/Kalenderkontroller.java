/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroller;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Stein-Erik
 */
@Controller
public class Kalenderkontroller {
        @RequestMapping(value="kalenderTest")
    public String kalenderTest (Model model, HttpServletRequest request){
        return "kalenderTest";
    }

    
@RequestMapping(value = "/events/getEvents", method = RequestMethod.GET)
    public
    @ResponseBody
    String getVacation(HttpServletResponse response) {
        
        //kall til database for å finne relevant info.
        //ID, tittel, start, slutt, descr, rom, type.
        
        
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
        map2.put("start", "2016-01-15-12:00:00");
        map2.put("description", "Hallaballa. <a href='http://google.com'>link</a>");
        
        String json2 = new Gson().toJson(map2);
        
        json +=", " + json2;

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return "[" + json + "]";
    }
}
