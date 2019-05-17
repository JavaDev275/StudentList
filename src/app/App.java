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
    public static void main(String[] args) throws Exception {
        
        // Check for valid arguments
        if(args == null || args.length != 1) {
            System.out.printf("java app.App (-a | -r | -c | +WORD | ?WORD)");
            return;
        }

        // Every operation requires us to load the student list
        String fileContent = loadData(Constants.STUDENT_LIST);

        if(args[0].equals(Constants.SHOW_ALL)) {
            String[] students = fileContent.split(Constants.STUDENT_ENTRY_DELIMITER);
            for(String student : students) {
                System.out.println(student);
            }
        } else if(args[0].equals(Constants.SHOW_RANDOM)) {      

            // Load the data  
            String[] students = fileContent.split(Constants.STUDENT_ENTRY_DELIMITER);
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);
        } else if(args[0].contains(Constants.ADD_ENTRY)){
            String newEntry = args[0].substring(1);

            // May have some issues later on with duplicate entries
            updateContent(fileContent + Constants.STUDENT_ENTRY_DELIMITER + newEntry, Constants.STUDENT_LIST);
        } else if(args[0].contains(Constants.FIND_ENTRY)) {                      
            String searchTerm = args[0].substring(1);
            String[] students = fileContent.split(Constants.STUDENT_ENTRY_DELIMITER);
            int indexLocation = -1;
            for(int idx = 0; idx < students.length; idx++) {
                if(students[idx].trim().equals(searchTerm)) {                    
                    indexLocation = idx;
                    break;
                }
            }

            // Print out the location and the student to the user
            if(indexLocation >= 0) {
                System.out.printf("Entry %s found at index %d", searchTerm, indexLocation);
            } else {
                System.out.printf("Entry %s does not exist", searchTerm);
            }
        } else if(args[0].contains(Constants.SHOW_COUNT)) {
            String[] students = fileContent.split(Constants.STUDENT_ENTRY_DELIMITER);
            System.out.printf("%d words found", students.length);
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.STUDENT_LIST));        
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
        String timestamp = String.format("List last updated %s", new Date());        
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_LIST));        
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.append(timestamp);            
            bufferedWriter.close();
        } catch (IOException exception){
            System.out.println(exception);
        }
    }
}