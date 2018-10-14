/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import echoserver.ApplicationLayer;
import echoserver.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author bsc
 */
public class PersonTest {
    
    
    private ApplicationLayer app;
    private Person person;
    private Person peronsPrototype;
    public PersonTest() {
  
    }
    
    @BeforeClass
    public void setUpClass() {
         app = new ApplicationLayer();
         peronsPrototype = new Person("Shafiq",25);
         person = app.getPerson();
    }
    
    @AfterClass
    public void tearDownClass() {
        app = null;
        person = null;
        System.gc();
    }
    
    @Before
    public void setUp() {
      
     
       
    }
    
    @After
    public void tearDown() {
       
    }
    
  
      
      @Test
      public void TestPerson(){
          
          assertEquals(peronsPrototype.equals(person),true);
          
           
     }  
      
   
      


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

   
}
