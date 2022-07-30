/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.mycontrollers;

import com.phobamvc.annotations.Controller;
import com.phobamvc.annotations.Mapping;
import com.phobamvc.mymodels.Person;
import com.phobamvc.wrapper.ActionView;
import com.phobamvc.wrapper.ParamRequest;
import com.phobamvc.wrapper.ViewResult;

/**
 *
 * @author Lazar-PC
 */

@Controller
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
    
    @Mapping(map="testing")
    public ActionView testing(ParamRequest p){
        
        Person person=new Person((String)p.get("username"),(int)p.get("age"));
        javax.swing.JPanel panel=(javax.swing.JPanel)p.get("container");
        return new ViewResult("MyPanel",panel,person);
        
    }

}
