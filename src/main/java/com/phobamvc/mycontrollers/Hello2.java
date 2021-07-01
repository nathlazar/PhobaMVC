/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.mycontrollers;

import com.phobamvc.annotations.Controller;
import com.phobamvc.annotations.Mapping;
import com.phobamvc.wrapper.ParamRequest;

/**
 *
 * @author Lazar-PC
 */

@Controller
public class Hello2 {
    
    @Mapping(map="display")
    public String displayName(ParamRequest p){
        
        int i=(int)p.get("age");
        String s=p.get("name").toString();
        return "Hello this is you info \nName "+s+" "+"\nAge "+i;
        
    }
    
    @Mapping(map="greet")
    public String greetings(){
        
        return "A wonderful day";
        
    }
    
}
