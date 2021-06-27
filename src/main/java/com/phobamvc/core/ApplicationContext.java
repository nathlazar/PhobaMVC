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
