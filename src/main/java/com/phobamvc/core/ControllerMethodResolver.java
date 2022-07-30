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

import com.phobamvc.annotations.Mapping;
import com.phobamvc.exception.NoSuchMapException;
import com.phobamvc.exception.ParameterExceedException;
import com.phobamvc.exception.ParameterException;
import com.phobamvc.ui.View;
import com.phobamvc.wrapper.ParamRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;
import java.util.Hashtable;
import com.phobamvc.annotations.ParameterMap;
import com.phobamvc.wrapper.ActionView;
import com.phobamvc.wrapper.Content;
import com.phobamvc.wrapper.ViewResult;

/**
 *
 * @author Lazar-PC
 */
public class ControllerMethodResolver {
    
    private static int NO_PARAM=0;
    private static int PARAM_REQUEST=1;
    private static int PARAM_ERROR=2;
    private static int PARAM_EXCEED=3;
    private static int VIEW_RESULT=4;
    private static int CONTENT=5;
    private static int GENERAL=6;
    

    public static void methodChecker(Hashtable<String,Object> hash,Enumeration<String> enumeration,String mapvalue,View view) throws NoSuchMapException, 
            IllegalAccessException, 
            IllegalArgumentException, 
            InvocationTargetException, 
            ParameterException, 
            ParameterExceedException,
            ClassNotFoundException,
            NoSuchMethodException,
            InstantiationException{
        
        boolean flag=true;
        boolean ifMapExist=false;
        
        while(enumeration.hasMoreElements()&&flag) {

            String k = enumeration.nextElement();
            Method[] method=hash.get(k).getClass().getDeclaredMethods();
            for(Method m:method){
                
                Mapping map=m.getDeclaredAnnotation(Mapping.class);
                
                if(map!=null){
                    
                    if(map.map().equals(mapvalue)){
                        
                        Parameter[] p=m.getParameters();
                        int paramState=checkMethodParameters(p);
                        
                        if(paramState==NO_PARAM||paramState==PARAM_REQUEST){
                            
                            ActionView actionView;
                            
                            if(paramState==NO_PARAM){
                                
                                 actionView=(ViewResult)m.invoke(hash.get(k));
                                
                            }
                            else{
                             
                                actionView=(ViewResult)m.invoke(hash.get(k),getParamRequest(view));   
                                
                            }
                            
                            if(checkActionViewType(actionView)==VIEW_RESULT){
                                
                                ViewResult viewResult=(ViewResult)actionView;
                                View redirView=(View)(Class.forName("com.phobamvc.testing."+viewResult.getViewName()).getDeclaredConstructor().newInstance());
                                redirView.MODEL.setObj(viewResult.getModel());
                                redirView.VIEW_CONTAINER.setObj(viewResult.getContainer());
                                redirView.initView();
                                
                                
                                
                            }
                            else if(checkActionViewType(actionView)==CONTENT){
                                
                                ;
                                
                            }
                            else{
                             
                                ;   
                                
                            }
                            
                            flag=false;
                            ifMapExist=true;
                            break;
                            
                        }
                        else if(paramState==PARAM_ERROR){
                            
                            throw new ParameterException(p[0].getType());
                            
                        }
                        else{
                            
                            throw new ParameterExceedException();
                            
                        }
                        
                    }
                    
                }
                
            }
            
	}
        
        if(!ifMapExist){
            
            throw new NoSuchMapException(mapvalue);
            
        }
    }
    
    public static void methodRunner(){
        
    }
    
    private static int checkActionViewType(ActionView actionView){
    
        if(actionView instanceof ViewResult){
            
            return VIEW_RESULT;
            
        }
        else if(actionView instanceof Content){
            
            return CONTENT;
            
        }
        else{
         
            return GENERAL;
            
        }
    
    }
    
    private static int checkMethodParameters(Parameter[] p){
        
        if(p.length==NO_PARAM){
            
            return NO_PARAM;
            
        }
        else if(p.length==1){
            
            if(p[0].getType()==ParamRequest.class){
                
                return PARAM_REQUEST;
                
            }
            else{
                
                return PARAM_ERROR;
                
            }
            
        }
        else{
            
            return PARAM_EXCEED;
            
        }
        
        
    }
    
    private static ParamRequest getParamRequest(View view) throws IllegalArgumentException, IllegalAccessException{
        
        ParamRequest param=new ParamRequest();
        Field[] field=view.getClass().getDeclaredFields();
       
        
        for(Field f:field){
            
            Annotation  anno=f.getDeclaredAnnotation(ParameterMap.class);
            ParameterMap p=(ParameterMap)anno;
            //System.out.println(p.param()+" "+f.get(view));
            try{
                
                param.put(p.param(),f.get(view));
                
            }
            catch(NullPointerException e){
                
                e.printStackTrace();
                
            }
            
            
        }
        
        return param;
        
    }
    
}
