/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.exception;

/**
 *
 * @author Lazar-PC
 */
public class ParameterException extends Exception{
    
    public ParameterException(Class c){
        
        super(c.toString()+" is not valid parameter type for controller's method");
        
    }
    
}
