/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsc
 */
public class Protocol {
    
    
    private ProtocolDelegate delegate;
    private SocketChannel socket;
    public Protocol(){
        
    }
    
    public void writeToAppiclationLayer(){
        
        
        //byte [] packets = delegate.getPackets();
//        Person person = new Person();
//        
//        byte [] ageBytes = new byte[4];
//        byte [] nameLengthBytes = new byte[4];
//        byte [] serailizedName; 
//        System.arraycopy(packets, 0, ageBytes, 0, ageBytes.length);
//        person.deserializeAge(ageBytes);
//        
//        System.arraycopy(packets, 0, packets, 4, nameLengthBytes.length);
//        person.deserializeNameLength(ageBytes);
//        
//        serailizedName = new byte[person.getNameLength()];
//        System.arraycopy(packets, 0, serailizedName, 8, serailizedName.length);
        
        
        
        
      //  delegate.setPerson(person);
        
    }
    
    
    public void write(SocketChannel client) throws IOException{
        
          Person person = new Person();
          int total = -1;
          
          byte [] bytes = new byte[512];
          ByteBuffer buffer = ByteBuffer.allocate(512);
          total = client.read(buffer);
          buffer.flip();
          buffer.get(bytes);
             
          if(total == -1){
        
               delegate.setPerson(person);
               client.close();
              
          }else{
              
               byte [] ageBytes = new byte[4];
               byte [] nameLengthBytes = new byte[4];
               byte [] serailizedName; 
               
               
               
               System.arraycopy(bytes, 0, ageBytes, 0, ageBytes.length);
               person.deserializeAge(ageBytes);
               
               System.arraycopy(bytes, 4, nameLengthBytes, 0, nameLengthBytes.length);
               person.deserializeNameLength(nameLengthBytes);
        
               serailizedName = new byte[person.getNameLength()];
               
               System.arraycopy(bytes, 8, serailizedName, 0, serailizedName.length);
               person.setName(new String(serailizedName));
              
          }
    }
 
    
    public void setSocketChannel(SocketChannel socket){
        this.socket = socket;
       
        try {
            write(socket);
        } catch (IOException ex) {
            Logger.getLogger(Protocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDelegate(ProtocolDelegate delegate){ this.delegate = delegate;}
    
}
