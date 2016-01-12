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
    
/*
    WebDriver driver = new FirefoxDriver(); 
    
    @Test
    public void test_at_man_kan_legge_varer_i_handlekurven_og_bestille() 
        throws Throwable { 
        driver.get("http://localhost:8084/Study_Easy/");

        Thread.sleep(2000); 
        driver.findElement(By.id("add-item")).click(); 
        
        Thread.sleep(2000); 
        driver.findElement(By.cssSelector("#go-to-cart")).click(); 
        
        Thread.sleep(2000); 
        driver.findElement(By.id("place-order")).click(); 
        
        
        assert(driver.findElement(By.id("order-confirmation")) != null);
        
        
        driver.close();
    } */
    
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
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}