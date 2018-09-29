/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;

/**
 *
 * @author bsc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      
            NetworkLayer layer = new NetworkLayer(5000);
            
        try {
            layer.startToListen();
        } catch (IOException ex) {
            System.err.println(ex.getCause());
            ex.printStackTrace();
            
        }
           
     
        
    }

   
    
}
