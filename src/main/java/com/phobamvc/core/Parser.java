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

import com.phobamvc.exception.XMLMultipleNodeException;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lazar-PC
 */
public class Parser {
    
   protected static String getDisplayName(){
        
        String displayName;
        
        try{
            
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse(new File(ApplicationContext.getConfigLocation()+"\\phoba.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodeList=doc.getDocumentElement().getElementsByTagName("displayname");
            checkSingleNode(nodeList);
            Node node=nodeList.item(0);
            return node.getTextContent();
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            return null;
            
        }
        
    }
   
    protected static void checkSingleNode(NodeList nodeList)throws XMLMultipleNodeException{
        
        if(!(nodeList.getLength()==1)){
           
            throw new XMLMultipleNodeException();
            
        }
        
    }
    
    public static String pathResolver(String path){
        
        path=path.replace("\\","/");
        String[] s=path.split("/");
        path="";
        
        for(int i=0;i<(s.length-1);i++){
            
            if(i==s.length-2){
                
                path+=s[i];
                
            }
            else{
                
                path+=s[i]+"\\";
                
            }
            
        }
       
        return path;
        
    }
    
    protected static ArrayList<String> parseRoutes(){
        
        ArrayList<String> list=new ArrayList<String>();
        
        try{
            
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse(new File(ApplicationContext.getConfigLocation()+"\\"+Parser.getDisplayName()+"-routes.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodeList=doc.getDocumentElement().getElementsByTagName("route:controllers");
            
            for(int i=0;i<nodeList.getLength();i++){
                
                Node node=nodeList.item(i);
                list.add(((Element)node).getAttribute("base-package"));
                
            }
            
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            
        }
        
        return list;

    }
    
    protected static String getPackageFileFormat(String str){
        
        return str.replace(".","\\");
        
    }
    
}
