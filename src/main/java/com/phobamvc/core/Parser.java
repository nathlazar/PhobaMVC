/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
