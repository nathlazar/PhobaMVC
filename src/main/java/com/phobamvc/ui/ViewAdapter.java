/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.ui;

/**
 *
 * @author Lazar-PC
 */
public interface ViewAdapter extends View{
    
    public default void request(String map,UpdateView updateView){
        
        request(map);
        updateView.updateView();
        
    }  
    
    @Override
    public default void updateView(){
        ;
    }
    
}
