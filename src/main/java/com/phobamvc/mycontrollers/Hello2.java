/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.mycontrollers;

import com.phobamvc.annotations.Mapping;
import com.phobamvc.wrapper.ParamRequest;

/**
 *
 * @author Lazar-PC
 */
public class Hello2 {
    
    @Mapping(map="display")
    public String displayName(ParamRequest p){
        
        int i=Integer.parseInt(p.get("age").toString());
        String s=p.get("name").toString();
        System.out.println("Name "+s+" "+"\nAge "+i);
        return "Hello";
        
    }
    
    @Mapping(map="curse")
    public String cursing(){
        
        System.out.println("Fuck you gago");
        return "Hello";
       
        
    }
    
}
