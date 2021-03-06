package app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Date;
import java.io.IOException;
import java.util.Arrays;

public class App {

    /**
     * The Main Method
     */
    public static void main(String[] args) {
        
        // Check for valid arguments
        if(args == null || args.length != 1) {
            showUsage();
            return;
        }
 
        // Every operation requires us to load the student list
        String studentList = loadStudentList();

        if(args[0].equals(Constants.SHOW_ALL)) {                        
            String[] students = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            for(String student : students) {
                System.out.println(student);
            }
        } else if(args[0].equals(Constants.SHOW_RANDOM)) {      
            String[] students = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            
            // TODO: The sequence of random numbers generated by a single 
            // Random instance is supposed to be uniformly distributed. 
            // By creating a new Random instance every time this operation 
            // is invoked, we run a risk of generating identical random 
            // numbers. Either reseed Random or instantiate it 
            // when the program starts.
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);
        } else if(args[0].contains(Constants.ADD_ENTRY)) {                          
            String newEntry = args[0].substring(1);        

            // TODO: Handle duplicate student names
            updateStudentList(studentList + Constants.STUDENT_ENTRY_DELIMITER + newEntry);  
        } else if(args[0].startsWith(Constants.FIND_ENTRY)) {
            String[] students = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            String searchTerm = args[0].substring(1);

            // Print out whether the student was found to the user
            if(Arrays.asList(students).contains(searchTerm)){
                System.out.printf("Entry %s found", searchTerm);
            } 
            else {
                System.out.printf("Entry %s does not exist", searchTerm);
            }
        } else if(args[0].equals(Constants.SHOW_COUNT)) {
            String[] students = studentList.split(Constants.STUDENT_ENTRY_DELIMITER);
            System.out.printf("%d words found", students.length);
        } else {

            // Invalid arguments were provided, therefore we'll list the
            // valid arguments available to the user.
            showUsage();
        }
    }

    /**
     * Reads data from the set file provided in Constants.java
     */
    public static String loadStudentList() {

        // Variable used to organize code and allow buffered reader to close
        String bufferedStudentList = " ";  

        // The try...catch block handles the possible error that may occur if 
        // there was an issue with accessing the file.        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.STUDENT_LIST));
            bufferedStudentList = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException exception) {
            System.out.println(exception);
        } 
        return bufferedStudentList;        
    }

    /** 
     * Writes the given string of data to the file with the given file name.
     * This method also adds a timestamp to the end of the file.
     */ 
    public static void updateStudentList(String content) {
        String timestamp = String.format("List last updated %s", new Date());        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.STUDENT_LIST));        
            writer.write(content);
            writer.newLine();
            writer.append(timestamp);            
            writer.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }        
    }

    /**
     * Default print out statement describing usage for invalid input.
     */
    public static void showUsage() {
        System.out.printf("java app.App (-a | -r | -c | +WORD | ?WORD)");
    }
}
