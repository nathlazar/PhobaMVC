/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.mycontrollers;

import com.barakoframework.annotations.Mapping;

/**
 *
 * @author Lazar-PC
 */


public class Hello {
    
    @Mapping(map="greetings")
    public String greet(){

        System.out.println("Hi");
        return "Hello";
        
    }
    
    @Mapping(map="greet-curse")
    public void fuckingshitbullshit(){
        System.out.println("Hello Tangina mo");
    }
    
    public void fuckingshit(){
        
    }
    
}
