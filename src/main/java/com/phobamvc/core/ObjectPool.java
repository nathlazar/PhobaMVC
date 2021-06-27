/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.core;

import java.util.Hashtable;

/**
 *
 * @author Lazar-PC
 */
public class ObjectPool {
    
    private Hashtable<String,Object> pool;
    
    public ObjectPool(){
        
        pool=new Hashtable<String,Object>();
        
    }
  
    protected synchronized void addObject(String key,Object obj){
      
        pool.put(key, obj);
      
    }
    
    protected synchronized Object getObject(String key){
        
        return pool.get(key);
        
    }
    
    protected synchronized Hashtable<String,Object> getPool(){
        
        return pool;
        
    }
    
    
}
