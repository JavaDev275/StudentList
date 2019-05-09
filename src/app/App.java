package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception 
    {
 
        if(args[0].equals("a")) {
            System.out.println("Loading data ...");
            File f = new File("students.txt");
            FileReader r = new FileReader(f);
            BufferedReader b = new BufferedReader(r);        
            String D;
            String t = "";
            D = b.readLine();
            while(D != null) {
                t += D;
                D = b.readLine();
            }        
            String[] l = t.split(",");
            for(String j : l) System.out.println(j);
            b.close();
            System.out.println("Data loaded");
        } else if(args[0].equals("r")){      
            // Load the data  
            File f = new File("students.txt");
            FileReader r = new FileReader(f);
            BufferedReader b = new BufferedReader(r);        
            String d;
            String t = "";
            d = b.readLine();
            while(d != null) {
                t += d;
                d = b.readLine();
            }
            String[] l = t.split(",");
            Random x = new Random();
                int y = x.nextInt(l.length);
                    System.out.println(l[y]);
            b.close();
            System.out.println("Data loaded");

        } 
        
        else if(args[0].contains("+")){
            // Read
            File f = new File("src/students.txt");
                          
            String k = args[0].substring(1);
            FileReader c = new FileReader(f);
            BufferedReader br = new BufferedReader(c);
                        
            String d;
            String t = "";
            d = br.readLine();
            while(d != null) {
                t += d;                
                d = br.readLine();
            }
            
            // write
            FileWriter r = new FileWriter(f);
            BufferedWriter b = new BufferedWriter(r);  
            b.write(t + ", " + k);

            Date now = new Date();
            b.newLine(); b.append("List last updated on " + now.toString());
            b.close();
            br.close();        


        } else if(args[0].contains("?")){
            File f = new File("students.txt");                          
            String k = args[0].substring(1);
            FileReader c = new FileReader(f);
            BufferedReader br = new BufferedReader(c);            
            String d;
            String t = "";
            d = br.readLine();
            while(d != null) {
                t += d;                
                d = br.readLine();
            }            
            String[] i = t.split(", ");
            boolean done = false;
            for(int idx = 0; idx < i.length && !done; idx++){
                if(i[idx].trim().equals(k))                   
                    System.out.println("We found it!");
                    done = true;
                
            }
            br.close();

        } else if(args[0].contains("c")){
            System.out.println("Loading data ...");
            
            File f = new File("students.txt");                                      
            FileReader c = new FileReader(f);
            BufferedReader br = new BufferedReader(c);            
            String d;
            String t = "";
            d = br.readLine();
            while(d != null) {
                t += d;                
                d = br.readLine();
            }        

            char[] arr = t.toCharArray();
            int count = 0;
            boolean inWord = false;
            for(char m : arr){
                if(m > ' ' && m < 0177){
                    if(!inWord) {
                        count = count + 1;
                        inWord = true;                        
                    }
                } else inWord = false;
            }
            br.close();
            System.out.printf("%d words found", count);
        }
    }
}