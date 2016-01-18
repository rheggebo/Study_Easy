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
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Ingvild
 */


public class Test1 {
    
    WebDriver driver = new FirefoxDriver();     
    
    /*
    
    @Test
    public void testLoggInn() throws Throwable {   
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(2000);
    } 
    
    @Test
    public void testFeilPassord() throws Throwable { 
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord1"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnGlemtPassord")).click(); 
        
        Thread.sleep(2000);
    
    }  */
    
    @Test
    public void testKalenderen() throws Throwable {
    
        driver.get("http://localhost:8084/Study_Easy/");
        
        Thread.sleep(2000);
        driver.findElement(By.id("epostInnlogging")).sendKeys("test1@aol.com");
        
        Thread.sleep(2000);
        driver.findElement(By.id("passordInnlogging")).sendKeys("passord"); 
        
        Thread.sleep(2000);
        driver.findElement(By.id("loggInnKnapp")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-next-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-next-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-prev-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-today-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-agendaWeek-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-agendaDay-button")).click(); 
        
        Thread.sleep(2000);
        driver.findElement(By.className("fc-month-button")).click(); 
        
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
    
    public Test1() {
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