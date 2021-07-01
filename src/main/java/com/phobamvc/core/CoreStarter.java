/*
 * Copyright (c) 2021, Jonathan Lazar
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.phobamvc.core;

import com.phobamvc.annotations.Controller;
import java.io.File;
import java.lang.annotation.Annotation;

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
                    Annotation anno=c.getDeclaredAnnotation(Controller.class);
                    
                    if(anno!=null){
                        
                        ApplicationContext.controllerPool.addObject(className,c.getDeclaredConstructor().newInstance());
                        
                    }
                    
                }
                catch(Exception e){
                    
                    e.printStackTrace();
                    
                }
                     
            }
            
        }
        
        
    }
    
}
