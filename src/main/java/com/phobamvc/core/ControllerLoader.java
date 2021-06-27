/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.core;

import java.util.Enumeration;
import java.util.Hashtable;
import com.phobamvc.ui.View;

/**
 *
 * @author Lazar-PC
 */
public class ControllerLoader {
    
    private Object obj;
    
    public ControllerLoader(String mapvalue,View view){
        
        Hashtable<String,Object> hash=ApplicationContext.controllerPool.getPool();
        Enumeration<String> enumeration=hash.keys();
        
        try{
            
            ControllerMethodResolver.methodChecker(hash,enumeration,mapvalue,view);
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            
        }

    }
    
    public Object getController(){
        
        return this.obj;
        
    }
    
}
