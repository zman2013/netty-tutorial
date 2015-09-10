package org.jboss.netty.example.time;

import java.util.Date;


public class UnixTime {

    private final int value;
    
    public UnixTime(int value){
        this.value = value;
    }
    
    public UnixTime(long value){
        this.value = (int) value;
    }
    
    public int getValue(){
        return value;
    }
    
    public String toString(){
        return new Date(value * 1000L).toString();
    }
}
