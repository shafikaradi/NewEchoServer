/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        ThreadPool pool = new ThreadPool(2);
        
          ApplicationLayer app = new ApplicationLayer();
          
          pool.execute(()->{
              app.listenToNetwork();
          });
          
          pool.execute(()->{
              app.listening();
          });
          
          
          
    



         


       
        
        
                   
            
      

       

           
     
        
    }

   
    
}
