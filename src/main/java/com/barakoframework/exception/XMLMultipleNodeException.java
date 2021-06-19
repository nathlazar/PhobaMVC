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
public class XMLMultipleNodeException extends Exception{
    
    public XMLMultipleNodeException(){
        
        super("Must be 1 node occurence only");
        
    }
    
}
