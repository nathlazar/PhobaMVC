/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.exception;

/**
 *
 * @author Lazar-PC
 */
public class ParameterExceedException extends Exception{
    
    public ParameterExceedException(){
        
        super("The method's parameter exceeds");
        
    }
    
}
