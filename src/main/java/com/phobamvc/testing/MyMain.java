/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phobamvc.testing;

import com.phobamvc.core.ApplicationContext;
import java.io.IOException;


/**
 *
 * @author Lazar-PC
 */
public class MyMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        // TODO code application logic here
        ApplicationContext app=new ApplicationContext("com.phobamvc");
        MyFrame myView=new MyFrame();
        myView.setVisible(true);
        
    }
    
}
