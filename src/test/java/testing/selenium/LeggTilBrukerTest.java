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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author erlend
 */
public class LeggTilBrukerTest {
    
    WebDriver driver = new FirefoxDriver();
     
    @Test
    public void SokeSide() throws Throwable {   
        
        Actions action = new Actions(driver);
        
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
        driver.findElement(By.id("leggTilBruker")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.id("fornavn")).sendKeys("Ling"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("etternavn")).sendKeys("Ling"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("epost")).sendKeys("erlendksteinset@hotmail.com"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("tilgangnivaa")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.name("timeplansansvarligOption")).click();
        
        Thread.sleep(2000);
        WebElement element1=driver.findElement(By.id("timeplansansvarligOption"));
        action.doubleClick(element1).perform();
        
        Thread.sleep(2000);
        WebElement element2=driver.findElement(By.id("leggTil"));
        action.doubleClick(element2).perform();
                        
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
        driver.close();
    }
    
}
