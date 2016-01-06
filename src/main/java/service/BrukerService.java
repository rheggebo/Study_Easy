/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Bruker;
import java.util.List;

/**
 *
 * @author Stein-Erik
 */
public interface BrukerService {
    
    public boolean sjekkPassord(String brukernavn, String passord);
    
    public Bruker hentBruker(String brukernavn, String email);
    
    public Bruker hentBruker(Bruker bruker);
    
    public boolean endreBruker(Bruker bruker);
    
    public boolean slettBruker(Bruker bruker);
    
    public boolean nyBruker(Bruker bruker);
    
    public boolean slettBrukere(List<Bruker> brukere);
    
    public boolean endreBrukere(List<Bruker> brukere);
    
    public List<Bruker> hentAlleBrukere();
}
