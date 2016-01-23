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
public class testSokeSide {
        
        
       WebDriver driver = new FirefoxDriver();
       //WebDriver driver = new ChromeDriver();
        
    
    
    @Test
    public void SokeSide() throws Throwable {   
        
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(1000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(1000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("sokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("ansattSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("studentSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("fagSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("romSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("klasseSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("searchButtonSokeSide")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("searchFormSokeSide")).sendKeys("a");
        
        Thread.sleep(1000);
        driver.findElement(By.id("ansattSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("ansattSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("ansattSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("studentSokeSide")).click(); 
                
        Thread.sleep(1000);
        driver.findElement(By.id("studentSokeSide")).click(); 
                        
        Thread.sleep(1000);
        driver.findElement(By.id("studentSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("fagSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("fagSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("fagSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("romSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("romSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("romSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("klasseSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("klasseSokeSide")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("klasseSokeSide")).click();      
                
        Thread.sleep(1000);
        driver.findElement(By.id("klasseSokeSide")).click();
                   
        Thread.sleep(1000);    
    }
    
    public testSokeSide() {
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
