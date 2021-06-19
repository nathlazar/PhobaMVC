/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.ui;

import com.barakoframework.core.ControllerLoader;

/**
 *
 * @author Lazar-PC
 */
public interface View {
    
    public abstract void updateView();
    
    public default void request(String mapvalue){
 
        ControllerLoader c=new ControllerLoader(mapvalue,this);
        updateView();

    }
    
}
