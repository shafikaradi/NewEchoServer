/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bsc
 */
public class NetworkLayer{
    
    private Selector selector;
    private ServerSocketChannel serverChannel;
   // private final ThreadPool pool;
    private int port;
    private static final Set <Integer> portSet = new HashSet<>();
    private byte [] packets;
    private List<SocketChannel> socketList;
    
   
    
    public NetworkLayer(int port){
        
        tryToCheckPort(port);
        this.tryToOpenSelector();
        this.tryToOpenChannel();
        this.socketList = new ArrayList<SocketChannel>();
        //pool = new ThreadPool(3);
       
      
    }
    
    private void tryToCheckPort(int port){
        try {
            checkPort(port);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(NetworkLayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void checkPort(int port) throws Exception{
        
        if(portSet.contains(port)){
            
            System.out.println("Port "+port+" is already in use");
            throw new Exception(String.format("Port %d is already in use", port));
         
        }else{
            
            addPortToSet(port);
        }
            
    }
    
    private void addPortToSet(int port){
        portSet.add(port);
        setPort(port);
    }
    
    private void setPort(int port){
        this.port = port;
    }
    
    
 
    
    private void tryToOpenSelector(){
         try {
           openSelector();
        } catch (IOException ex) {
             System.err.println(ex.getCause());
        }
    }
    
    private void openSelector() throws IOException{
        selector = Selector.open();
    }
    
    private void tryToOpenChannel(){
           try {
            openChannel();
        } catch (IOException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
        }
    }
    
    private void openChannel() throws IOException{
        serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost",port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    
    
    public void startToListen() throws  IOException{
        
        System.out.println("System is starting to listen on port "+serverChannel.getLocalAddress());
        
        
        
       
                
        while(true){
          
           selector.select();
           Set<SelectionKey> selectedKeys = selector.selectedKeys();
           Iterator<SelectionKey> keys = selectedKeys.iterator();
            
            
            while(keys.hasNext()){
              SelectionKey  key = (SelectionKey)keys.next();
                
               
                if(key.isAcceptable() && key.isValid()){
                   // pool.execute(()->{ 
                     tryToReceiveRequest();
                    
                    //});
                }
               
              
                
                  if(key.isReadable() && key.isValid()){
                      // pool.execute(()->{
                        tryToListenToClient(key);
                      
                       //});
                  }
                
               
               
//                if(key.isWritable()){
//                    
//                
//                 }
                
              
                keys.remove();
                
                
                
            }
            
        }
        
    }
     
     
     private void registerClient() throws IOException{
         
         SocketChannel client = serverChannel.accept();
         client.configureBlocking(false);
         client.register(selector, SelectionKey.OP_READ);
         
         
        
     }
     
    private void tryToReceiveRequest(){
         
        try {
            registerClient();
        } catch (IOException ex) {
           System.out.println(ex.getCause());
        }
         
     }
      
      private void listenToClient(SelectionKey key) throws IOException{
       
      
          SocketChannel client = (SocketChannel) key.channel();
          socketList.add(client);
             System.out.print("mw "+socketList.size());
         key.cancel();
          
      }
      
   
      
      private void tryToListenToClient(SelectionKey key){
          
        try {
            listenToClient(key);
        } catch (IOException ex) {
           System.err.println(ex.getCause());
        }
      }

    
    public byte [] packets() {
       return packets;
    }
    
   
    
    @Override
    public void finalize(){
        
        portSet.remove(port);
        System.out.println(String.format("Port %d is now available",port));
       
    }
    
    
    public ArrayList getSockets(){
       
        return (ArrayList) socketList;
    }
    
      
   
 
}



