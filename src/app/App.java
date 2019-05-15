package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Date;
import java.io.IOException;

public class App {

    /**
     * The Main method
    */
    public static void main(String[] args) throws Exception {
        
        // Check for valid arguments
        if(args == null || args.length != 1){
            return;
        }

        // Every operation requires us to load the student list
        String fileContent = loadData("students.txt");

        if(args[0].equals("a")) {
            String[] students = fileContent.split(",");
            for(String student : students) {
                System.out.println(student);
            }
        } else if(args[0].equals("r")){      
            
            // Load the data  
            String[] students = fileContent.split(",");
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);

        } else if(args[0].contains("+")){
            String newEntry = args[0].substring(1);
            updateContent(fileContent + ", " + newEntry, "students.txt");
        } else if(args[0].contains("?")){                      
            String searchTerm = args[0].substring(1);
            String[] students = fileContent.split(", ");
            boolean done = false;
            for(int idx = 0; idx < students.length && !done; idx++){
                if(students[idx].trim().equals(searchTerm))      
                    System.out.println("We found it!");
                    done = true;
            }
        } else if(args[0].contains("c")){
            char[] fileChars = fileContent.toCharArray();
            int count = 0;
            boolean inWord = false;
            for(char character : fileChars){
                if(character > ' ' && character < 0177){
                    if(!inWord) {
                        count = count + 1;
                        inWord = true;                        
                    }
                } else {
                    inWord = false;
                }
            }
            System.out.printf("%d words found", count);
        } else {
            return;
        }
    }

    /** 
     * Reads data from the given file
     */
    private static String loadData(String fileName) {
        
        // Variable used to organize code and allow buffered reader to close
        String content = " ";  

        // The try/catch block handles the possible error that may occur if 
        // there was an issue with accessing the file.        
        try {
            File file = new File("students.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);        
            String currentLine;            
            currentLine = bufferedReader.readLine();
            
            // Read in all of the lines from the file 
            while(currentLine!= null) {
                content += currentLine;
                currentLine = bufferedReader.readLine();
            }        
            bufferedReader.close();
        } catch (IOException exception){
            System.out.println(exception);
        } 
        return content;        
    }

    /**
     * Writes the given string of data to the file with the given file name.
     * This method also adds a timestamp to the end of the file.
     */ 
    private static void updateContent(String content, String fileName){
        Date now = new Date();
        String timestamp = String.format("List last updated %s", now);        
        try {
            File file = new File("students.txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);        
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.append(timestamp);            
            bufferedWriter.close();
        } catch (IOException exception){
            System.out.println(exception);
        }
    }
}