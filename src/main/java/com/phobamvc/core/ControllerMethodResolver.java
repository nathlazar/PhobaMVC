/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Lazar-PC
 */
public class ControllerMethodResolver {
    
    private static int NO_PARAM=0;
    private static int PARAM_REQUEST=1;
    private static int PARAM_ERROR=2;
    private static int PARAM_EXCEED=3;

    public static void methodChecker(Hashtable<String,Object> hash,Enumeration<String> enumeration,String mapvalue,View view) throws NoSuchMapException, 
            IllegalAccessException, 
            IllegalArgumentException, 
            InvocationTargetException, 
            ParameterException, 
            ParameterExceedException{
        
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
                        
                        if(paramState==NO_PARAM){
                            
                            view.RETURNS.setObj(m.invoke(hash.get(k)));
                            flag=false;
                            ifMapExist=true;
                            break;
                            
                        }
                        else if(paramState==PARAM_REQUEST){
                            
                            view.RETURNS.setObj(m.invoke(hash.get(k),getParamRequest(view)));
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
            param.put(p.param(),f.get(view));
            
        }
        
        return param;
        
    }
    
}
