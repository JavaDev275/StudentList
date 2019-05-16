package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Check for valid arguments
        if(args == null || args.length != 1) {
            System.out.printf("java app.App (-a | -r | -c | +WORD | ?WORD)");
            return;
        }

        if(args[0].equals("a")) {
            File file = new File("students.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);        
            String currentLine;
            String fileContent = "";
            currentLine = bufferedReader.readLine();
            
            // Read in all of the lines from the file 
            while(currentLine!= null) {
                fileContent += currentLine;
                currentLine = bufferedReader.readLine();
            }        
            String[] students = fileContent.split(",");
            for(String student : students) {
                System.out.println(student);
            }
            bufferedReader.close();
        } else if(args[0].equals("r")) {      
            
            // Load the data  
            File file = new File("students.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);        
            String currentLine;
            String fileContent = "";
            currentLine = bufferedReader.readLine();
            while(currentLine != null) {
                fileContent += currentLine;
                currentLine = bufferedReader.readLine();
            }
            String[] students = fileContent.split(",");
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);
            bufferedReader.close();

        } 
        else if(args[0].contains("+")) {
            
            // Read
            File file = new File("src/students.txt");
            String newEntry = args[0].substring(1);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String currentLine;
            String fileContent = "";
            currentLine = bufferedReader.readLine();
            while(currentLine != null) {
                fileContent += currentLine;                
                currentLine = bufferedReader.readLine();
            }

            // Write
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);  
            bufferedWriter.write(fileContent + ", " + newEntry);
            Date now = new Date();
            bufferedWriter.newLine(); 
            bufferedWriter.append("List last updated on " + now.toString());
            bufferedWriter.close();
            bufferedReader.close();        
        } 
        else if(args[0].contains("?")) {
            File file = new File("students.txt");                          
            String searchTerm = args[0].substring(1);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);            
            String currentLine;
            String fileContent = "";
            currentLine = bufferedReader.readLine();
            while(currentLine != null) {
                fileContent += currentLine;                
                currentLine = bufferedReader.readLine();
            }            
            String[] students = fileContent.split(", ");
            boolean done = false;
            for(int idx = 0; idx < students.length && !done; idx++) {
                if(students[idx].trim().equals(searchTerm))      
                    System.out.println("We found it!");
                    done = true;
            }
            bufferedReader.close();
        } 
        else if(args[0].contains("c")) {
            File file = new File("students.txt");                                      
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);            
            String currentLine;
            String fileContent = "";
            currentLine = bufferedReader.readLine();
            while(currentLine != null) {
                fileContent += currentLine;                
                currentLine = bufferedReader.readLine();
            }        
            char[] fileChars = fileContent.toCharArray();
            int count = 0;
            boolean inWord = false;
            for(char character : fileChars) {
                if(character > ' ' && character < 0177) {
                    if(!inWord) {
                        count = count + 1;
                        inWord = true;                        
                    }
                } else {
                    inWord = false;
                }
            }
            System.out.printf("%d words found", count);
            bufferedReader.close();
        } else {
            System.out.printf("java app.App (-a | -r | -c | +WORD | ?WORD)");
            return;
        }
    }
}