/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konfig;

import beans.KalenderEvent;
import beans.RomBestilling;
import static java.lang.Thread.sleep;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import kontroller.Hovedkontroller;
import org.springframework.beans.factory.annotation.Autowired;
import service.Service;

/**
 *
 * @author Stein-Erik
 */
public class Test {
    @Autowired
    private Service service;
            
    @PostConstruct
    public void init(){
        final KalenderEvent ke = new KalenderEvent();
        ke.setEpost("steinerikbjornnes@gmail.com");
        Date dato = Calendar.getInstance().getTime();
        final Timestamp now = new Timestamp(dato.getTime());
        ke.setStartTid(now);
        final int periode = 1000;
        final int ms10Min = 10*60*1000;
        final int ms15Min = 15*60*1000;
        Thread asd = new Thread(){
            public void run(){
                try{
                    ke.setStartTid(new Timestamp(now.getTime()+ms15Min));
                    List<RomBestilling> eventListe = service.getReserverteRom(ke);
                    for (RomBestilling romBestilling : eventListe) {
                        System.out.println(now.getTime());
                        System.out.println(ke.getStartTid().getTime());
                        System.out.println(now.getTime()-ke.getStartTid().getTime());
                        System.out.println(ms10Min);
                        if(now.getTime()-ke.getStartTid().getTime()>ms10Min && !romBestilling.isSjekketInn()){
                            KalenderEvent t = new KalenderEvent();
                            t.setEpost(ke.getEpost());
                            t.setStartTid(romBestilling.getStartDato());
                            t.setSluttTid(romBestilling.getSluttDato());
                            t.setRom(romBestilling.getRomId());
                            //service.slettBooking(ke);
                            //Legg til anmerkning
                        }
                    }
                    now.setTime(now.getTime()+periode);
                    sleep(periode);
                }catch (InterruptedException ex){
                    Logger.getLogger(Hovedkontroller.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        };
        asd.run();
    }
    
}
