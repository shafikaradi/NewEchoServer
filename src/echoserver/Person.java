/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

/**
 *
 * @author bsc
 */
public class Person {

  
    private String name;
    private int age;
    private int lengthOfName;
    byte [] bytes;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        bytes = new byte[4];
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public byte[] serilizeAge(){
        
       bytes[0] =  (byte) (age & 0xFF);
       bytes[1] =  (byte) (age & 0xFF >> 8);
       bytes[2] =  (byte) (age & 0xFF >> 16);
       bytes[3] =  (byte) (age & 0xFF >> 24);
       
       return bytes; 
    }
    
    public static byte[] dserilizeAge(int age){
        
       byte bytes[] = new byte[4];
       bytes[0] =  (byte) (age & 0xFF);
       bytes[1] =  (byte) ((age & 0xFF) >> 8);
       bytes[2] =  (byte) ((age & 0xFF) >> 16);
       bytes[3] =  (byte) ((age & 0xFF) >> 24);
       
       return bytes; 
    }

}
