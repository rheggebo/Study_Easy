/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.selenium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author erlend
 */
public class LeggTilBrukerTest {
    
    WebDriver driver = new FirefoxDriver();
     
    @Test
    public void SokeSide() throws Throwable {   
        
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("erlendksteinset@hotmail.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys(".kT?0*CZC_"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("minSide")).click(); 
   
        Thread.sleep(2000);
        driver.findElement(By.id("leggTilBruker")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.id("fornavn")).sendKeys("Ling"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("etternavn")).sendKeys("Ling"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("epost")).sendKeys("erlendksteinset@hotmail.com"); 
        
        Thread.sleep(2000);        
    }
    
        public LeggTilBrukerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        WebDriver driver = new ChromeDriver();
        driver.close();
    }
    
}
