/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.testing;

import com.phobamvc.ui.View;
import com.phobamvc.annotations.ParameterMap;
import com.phobamvc.ui.ViewAdapter;

/**
 *
 * @author Lazar-PC
 */

public class MyView implements ViewAdapter{
    
    @ParameterMap(param="age")
    public int myage;
    
    @ParameterMap(param="name")
    public String myname;
    
    public MyView(){
        
        myage=26;
        myname="Jonathan Lazar";
        //call controller method
        request("display",()->{
            
           System.out.println(RETURNS.getValue());
            
        });
       
    }
    
}
