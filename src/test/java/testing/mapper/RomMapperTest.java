/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.mapper;

import mapper.RomMapper;
import beans.Rom;
import database.DBConnection;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Sindre
 */
public class RomMapperTest {
    
    DBConnection dBConnection;
    Rom rom;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
       dBConnection = mock(DBConnection.class);  
       rom = new Rom();
    }
    
    @Test
    public void tester_1 (){
       
        
    }
    
    @After
    public void tearDown() {
    }
    
     @AfterClass
    public static void tearDownClass() {
    }
    
}
