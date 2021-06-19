/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barakoframework.core;

import java.io.File;

/**
 *
 * @author Lazar-PC
 */
public class CoreStarter {
    
    public CoreStarter(){
       
        controllerPoolStarter();
        
    }
    
    private void controllerPoolStarter() {
        
        ApplicationContext.controllerPool=new ControllerPool();
        for(String s1:Parser.parseRoutes()){
            
            File file=new File(ApplicationContext.getSrcPath()+"\\"+Parser.getPackageFileFormat(s1));
            String[] fileList=file.list();
            
            for(String s2:fileList){
                
                String className=s2.substring(0,s2.indexOf("."));
                
                try{
                    
                    Class c=Class.forName(s1+"."+className);
                    ApplicationContext.controllerPool.addObject(className,c.getDeclaredConstructor().newInstance());
                    
                }
                catch(Exception e){
                    
                    e.printStackTrace();
                    
                }
                     
            }
            
        }
        
        
    }
    
}
