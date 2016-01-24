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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Ingvild
 */


public class ForsideTest {
    
    WebDriver driver = new FirefoxDriver();     
    
    @Test
    public void testKalenderen() throws Throwable {
    
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(1000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(1000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(1000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-next-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-next-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-prev-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-today-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-agendaWeek-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-agendaDay-button")).click(); 
        
        Thread.sleep(1000);
        driver.findElement(By.className("fc-month-button")).click(); 
        
        Thread.sleep(1000);
       
    }
    
    @Test
    public void testCheckBox() throws Throwable {
    
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("checkBoxForside1")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.id("checkBoxForside2")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.id("checkBoxForside3")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.id("checkBoxForside4")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.id("checkBoxForside1")).click();
        
        Thread.sleep(2000);
        driver.findElement(By.className("infoKnappForside")).click();
        
        Thread.sleep(5000);
        driver.findElement(By.className("lukk")).click();

    }
    
    @Test
    public void testNyHendelse() throws Throwable {
    
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(500);
        driver.findElement(By.id("forside")).click(); 
        
        Thread.sleep(500);
        driver.findElement(By.id("leggTilKnappForside")).click();
        
        Thread.sleep(500);
        driver.findElement(By.id("tittel")).sendKeys("asdf");
                
        Thread.sleep(500);
        driver.findElement(By.id("startdato")).sendKeys("11112019");
        
        Thread.sleep(500);
        driver.findElement(By.id("starttid")).sendKeys("1111");
        
        Thread.sleep(500);
        driver.findElement(By.id("sluttdato")).sendKeys("11112019");
        
        Thread.sleep(1000);
        driver.findElement(By.id("slutttid")).sendKeys("2222");

        Thread.sleep(4000);
        driver.findElement(By.id("leggtilPH")).click(); 
        
        Thread.sleep(2000);
    }
    
    
    
    /*
        Thread.sleep(2000); 
        driver.findElement(By.id("add-item")).click(); 
        
        Thread.sleep(2000); 
        driver.findElement(By.cssSelector("#go-to-cart")).click(); 
        
        Thread.sleep(2000); 
        driver.findElement(By.id("place-order")).click(); 
        
        
        assert(driver.findElement(By.id("order-confirmation")) != null);
    */
    
    public ForsideTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}