/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.core;

import java.io.IOException;
/**
 *
 * @author Lazar-PC
 */
public class ApplicationContext {
    
    protected static ControllerPool controllerPool;
    private static String applicationPath;
    private static String srcPath;
    private static String configLocation;
    
    public ApplicationContext(String loc) throws IOException{
        
        java.io.File file=new java.io.File(System.getProperty("user.dir"));
        java.io.File fileToBeChecked=new java.io.File(file.getCanonicalPath()+"\\pom.xml");
        
        if(fileToBeChecked.exists()){
            
            applicationPath=file.getCanonicalPath();
            
        }
        else{
            
            applicationPath=Parser.pathResolver(file.getCanonicalPath());
                
        }
        
        srcPath=applicationPath+"\\src\\main\\java";
        String s=loc.replace(".","\\");
        configLocation=srcPath+"\\"+s+"\\APPINF";
        CoreStarter coreStarter=new CoreStarter();  
        
    }
    
    protected static String getApplicationPath(){
     
        return applicationPath;
        
    }
    
    protected static String getSrcPath(){
        
        return srcPath;
        
    }
    
    protected static String getConfigLocation(){
        
        return configLocation;
        
    }
    
    
}
