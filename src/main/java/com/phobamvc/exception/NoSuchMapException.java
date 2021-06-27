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
public class NoSuchMapException extends Exception{
    
    public NoSuchMapException(String mapvalue){
        
        super("\""+mapvalue+"\" does not exist");
        
        
    }
    
}
