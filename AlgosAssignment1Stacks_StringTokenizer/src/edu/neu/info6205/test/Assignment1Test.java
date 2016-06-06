/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info6205.test;

import edu.neu.info6205.src.Assignment1Solution;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author avinita
 */
public class Assignment1Test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Assignment1Solution soln = new Assignment1Solution();

        /**
         * LOAD INPUT FILE HERE
         */
        String inputFile = readFile("./res/PositiveScenarioInput.java");
        //String inputFile = readFile("./res/NegativeScenarioInput.java");
        
        if (inputFile.isEmpty()) {
            System.out.println("File is empty.Please check your input");
        } else if (soln.validateClass(inputFile)) {
            System.out.println("Its a valid Java Class File");
            printFileStats(inputFile);
        } else {
            System.out.println("Its not a valid Java Class File");
        }

    }

    public static void checkKeywords(String inputFile, HashMap h) {
        String s = "";

        String keywords[] = {"abstract", "case", "continue", "enum", "for", "instanceof", "new", "return", "switch",
            "transient", "assert", "catch", "default", "extends", "goto", "int", "package", "short", "synchronized",
            "try", "boolean", "char", "do", "final", "if", "interface", "private", "static", "this", "void", "break", "class",
            "double", "finally", "implements", "long", "protected", "strictfp", "throw", "volatile", "byte", "const", "else", "float", "import", "native", "public", "super", "throws", "while"};

        for (String value : keywords) {

            StringTokenizer st = new StringTokenizer(inputFile);
            while (st.hasMoreTokens()) {
                s = st.nextToken().trim();
                //System.out.println(s);
                if (s.startsWith("/*")) {
                    while (!(st.nextToken().trim().equals("*/"))) {
                        //System.out.println("next token is"+s);
                        //continue; 
                    }
                }
                if (s.equals(value)) {
                    if (!h.containsKey(s)) {
                        h.put(s, 1);
//                        s = st.nextToken().trim();
//                        if (!s.equals(value)) {
//                            // System.out.println("Id:" + s);
//                            if (!h.containsKey(s)) {
//                                if (!iden.containsKey(s)) {
//                                    iden.put(s, 0);
//                                }
//
//                            }
//                        }
                    } else {
                        h.put(s, (int) h.get(s) + 1);
                    }

                }
            }
        }
//        StringTokenizer st = new StringTokenizer(inputFile);
//        while (st.hasMoreTokens()) {
//            int count;
//            String p = st.nextToken().trim();
//            if (iden.containsKey(p)) {
//                count = (int) iden.get(p) + 1;
//
//                iden.put(p, count);
//
//            }
//        }
    }
    public static void findid(String inputFile,HashMap iden,HashMap h,HashMap var){
        String s;
        
        //StringTokenizer st=new StringTokenizer(inputFile);
        Scanner tokenize = new Scanner(inputFile);
        while(tokenize.hasNext()){
        s=tokenize.next();
        if(s.equals("/")&& tokenize.next().equals("*")){
            s=tokenize.next();
            
            while(s.equals("*")&&tokenize.next().equals("/")){
                
                continue;
            }
        }
        else{
        Pattern pattern = Pattern.compile("^[a-zA-Z$][_a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            
            if(!h.containsKey(s)){
                if(!var.containsKey(s)){
                    iden.put(s, 1);
                }
                else{
                    var.put(s, (int)var.get(s)+1);
                }
            }
        }
        }

        }
    }

    public static void checkVariable(String inputFile, HashMap var, HashMap h) {
        String str = inputFile;

        String[] tokens = str.split(" ");
        int count = 0;
        for (int i = 0; i < tokens.length; i++) {
            // System.out.println("HI"+tokens[i]);
            if (tokens[i].startsWith("/") && tokens[i + 1].equals("*")) {
                while (!(tokens[i].trim().equals("*") && tokens[i + 1].trim().equals("/"))) {
                }
            }
            if (tokens[i].equals("=")) {
                int p = i - 1;
                if (!h.containsKey(tokens[p])) {
                    if (!var.containsKey(tokens[p])) {
                        var.put(tokens[p], 1);
                    }
                }

            }
        }
       
    }

    private static void printFileStats(String inputFile) {
        HashMap<String, Integer> h, iden, var;
        h = new HashMap<String, Integer>();
        iden = new HashMap<String, Integer>();
        var = new HashMap<String, Integer>();
        checkKeywords(inputFile, h);

        System.out.println("List of keywords:");
        printMap(h);

        System.out.println("List of all the identifiers: ");
        checkVariable(inputFile, var, h);
        findid(inputFile, iden,h,var);
       // printMap(iden);
        printMap(var);

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * DO NOT CHANGE THIS
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static void printMap(HashMap h) {
        for (Iterator it = h.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            String key = entry.getKey().toString();
            Integer value = entry.getValue();
            System.out.println("Word : " + key + "\t Frequency: " + value);
        }
    }

    public static String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
    }
}




//    public static void checkIdens(String inputFile,HashMap h,HashMap var){
//        String s="";
//          String regex="([a-zA-Z_$][a-zA-Z\\d_$]*\\.)*[a-zA-Z_$][a-zA-Z\\d_$]*";
//          StringTokenizer st = new StringTokenizer(inputFile);
//            while (st.hasMoreTokens()) {
//                s = st.nextToken().trim();
//                //System.out.println(s);
//                if (s.startsWith("/*")) {
//
//                    while (!(st.nextToken().trim().equals("*/"))) {
//                        //System.out.println("next token is"+s);
//                        //continue; 
//                    }
//                }if(!h.containsKey(s)){
//                    if(s.startsWith(regex)){
//                        if(!var.containsKey(s)){
//                           var.put(s, 1);
//                        }
//                        else{
//                            var.put(s, (int)var.get(s)+1);
//                        }
//                    }
//                }
//            }
//    }
//    
//    public static void catchID(String inputFile,HashMap var){
//         String str=inputFile;
//        String[] tokens = str.split(" ");
//        for(int i=0;i<tokens.length;i++){
//            //System.out.println(tokens[i]);
//            if(tokens[i].equals("/*")){
//                 while(!(tokens[i].trim().equals("*/")))
//                         {
//                         }  
//            }
//            else if(var.containsKey(tokens[i])){
//                var.put(tokens[i],(int)var.get(tokens[i])+1);
//            }
//        }
//        
//        
//    }