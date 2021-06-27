/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.core;

/**
 *
 * @author Lazar-PC
 */
public class Returnable {
    
    private Object obj;
    
    public Returnable(){
        
        
        
    }
    
    protected void setObj(Object obj){
        
        
        this.obj=obj;
        
        
    }
    
    public Object getValue(){
        
        
        return this.obj;
        
        
    }
    
    
}
