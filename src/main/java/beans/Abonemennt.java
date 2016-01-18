/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Arne
 */
public class Abonemennt {
    
    private String eierId;
    private String abonererId;
    private int type;
    //0 for bruker-abonemennt, 1 for fag-abonemennt
    
    public int getType(){
        return type;
    }
    
    public String getEierid(){
        return eierId;
    }
    public String getAbonererId(){
        return abonererId;
    }
    public void setEierId(String eierId){
        this.eierId = eierId;
    }
    public void setAbonererId(String abonererId){
        this.abonererId = abonererId;
    }
    public void setType(int type){
        this.type = type;
    }
    
}
