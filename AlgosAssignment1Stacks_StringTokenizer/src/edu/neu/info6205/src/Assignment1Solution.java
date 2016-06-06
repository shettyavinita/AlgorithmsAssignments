package edu.neu.info6205.src;

import edu.neu.info6205.stacks.Stacks;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author avinita
 */
public class Assignment1Solution {

    public void removeComments(String inputfile) {
    }

    public boolean validateClass(String inputfile) {
        String inputFile = inputfile;
       
        Stacks stacks = new Stacks(inputFile.length());
        //System.out.println(inputFile.length());
        /*
        *
        *TO-DO please implement the code here
         */

        boolean x = true;
    
        StringTokenizer st = new StringTokenizer(inputFile," {",true);
        try {
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
             
                //to exclude multiline comments
                
                if (s.startsWith("/*")) {
                    s = st.nextToken();
                    //System.out.println("st is " + st.nextToken());
                  
                    while (!(st.nextToken().trim().equals("*/"))) {
                        //System.out.println("next token is" + s);
                        continue;
                    }
                    //to catch all the { braces and push them in the stack
                } else if (s.equals("{")) {
                    try {
                        stacks.push(s);
//                        count++;
//                        System.out.println("push" + count);
                    } catch (Exception e) {
                        System.out.println("Stack is full");
                    }
                } else if (s.contains("}")) {
                    if (stacks.isEmpty()) {
                        x = false;
                        break;
                    }
                    if (stacks.peek().compareTo("{") == 0) {
                        stacks.pop();
//                        popc++;
//                        System.out.println("pop" + popc);
                    }
                }
            }
        } catch (Exception e) {
           
            x=false;
        
        }

      
        if (x && stacks.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
