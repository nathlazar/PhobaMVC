/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.testing;

import com.barakoframework.ui.View;
import com.barakoframework.annotations.ParameterMap;

/**
 *
 * @author Lazar-PC
 */

public class MyView implements View{
    
    @ParameterMap(param="age")
    public int myage;
    
    @ParameterMap(param="name")
    public String myname;
    
    public MyView(){
        
        myage=26;
        myname="Jonathan Lazar";
        request("display"); //call controller method
        
        
    }
    
    @Override
    public void updateView(){ //triggered when calling request method
        
        System.out.println("Success");
        
        
    }
    
    
}
