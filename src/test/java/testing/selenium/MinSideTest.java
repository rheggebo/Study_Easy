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
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author erlend
 */
public class MinSideTest {
    
     WebDriver driver = new FirefoxDriver();
     
     @Test
    public void SokeSide() throws Throwable {   
        
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(1000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("erlendksteinset@hotmail.com");
        
        Thread.sleep(1000);
        driver.findElement(By.id("passordInnlogging")).sendKeys(".kT?0*CZC_"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("minSide")).click(); 
   
        Thread.sleep(1000);
        driver.findElement(By.id("minInfo")).click();
                
        Thread.sleep(1000);
        driver.findElement(By.id("endrePassord")).click();
          
        Thread.sleep(1000);
        driver.findElement(By.id("gPassord")).sendKeys(".kT?0*CZC_"); 
       
        Thread.sleep(1000);
        driver.findElement(By.id("nPassord")).sendKeys(".kT?0*CZC_"); 
       
        Thread.sleep(1000);
        driver.findElement(By.id("gnPassord")).sendKeys(".kT?0*CZC_"); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("endreGammeltPassord")).click();
        
        //Thread.sleep(1000);
        //driver.findElement(By.id("endreOpplysninger")).click();
        
        Thread.sleep(1000);
        
    }
    
    public MinSideTest() {
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
        driver.close();
    }
}
