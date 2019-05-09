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
    /*
     * The Main method
    */
    public static void main(String[] args) throws Exception 
    {
        // Check for valid arguments
        if(args == null || args.length != 1){
            return;
        }

        String fileContent = loadData(Constants.StudentList);

        if(args[0].equals(Constants.ShowAll)) 
        {
            String[] students = fileContent.split(Constants.StudentEntryDelimiter);
            for(String student : students) System.out.println(student);
        } 
        else if(args[0].equals(Constants.ShowRandom))
        {      
            // Load the data  
            String[] students = fileContent.split(Constants.StudentEntryDelimiter);
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);
        } 
        else if(args[0].contains(Constants.AddEntry))
        {
            String newEntry = args[0].substring(1);

            // May have some issues later on with duplicate entries
            updateContent(fileContent + Constants.StudentEntryDelimiter + newEntry, Constants.StudentList);
        } 
        else if(args[0].contains(Constants.FindEntry))
        {                      
            String searchTerm = args[0].substring(1);
            String[] students = fileContent.split(Constants.StudentEntryDelimiter);
            boolean done = false;
            for(int idx = 0; idx < students.length && !done; idx++)
            {
                if(students[idx].trim().equals(searchTerm)) 
                {                    
                    System.out.println("We found it!");
                    done = true;
                }
            }
        } 
        else if(args[0].contains(Constants.ShowCount))
        {
            char[] fileChars = fileContent.toCharArray();
            int count = 0;
            boolean inWord = false;
            for(char character : fileChars)
            {
                if(character > ' ' && character < 0177)
                {
                    if(!inWord) 
                    {
                        count = count + 1;
                        inWord = true;                        
                    }
                } else inWord = false;
            }
            System.out.printf("%d words found", count);
        } 
        else 
        {
            return;
        }

        
    }

    /* 
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
            while(currentLine!= null) 
            {
                content += currentLine;
                currentLine = bufferedReader.readLine();
            }        

            bufferedReader.close();
        } catch (IOException exception){
            System.out.println(exception);
        } 

        return content;        

    }

    /* 
     * Writes the given string of data to the file with the given file name.
     * This method also adds a timestamp to the end of the file.
     */ 
    private static void updateContent(String content, String fileName){
        String timestamp = String.format("List last updated %s", new Date());
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.StudentList));        
            writer.write(content);
            writer.newLine();
            writer.append(timestamp);            
            writer.close();
        } catch (IOException exception){
            System.out.println(exception);
        }

    }
}