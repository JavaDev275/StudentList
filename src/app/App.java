package app;

import java.util.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception 
    {
        // Check for valid arguments
        if(args == null || args.length != 1){
            showUsage();
            return;
        }
 
        // Every operation requires us to load the student list
        String studentList = loadStudentList();

        if(args[0].equals(Constants.ShowAll)) 
        {                        
            String[] students = studentList.split(Constants.StudentEntryDelimiter);
            for(String student : students) System.out.println(student);

        } 
        else if(args[0].equals(Constants.ShowRandom))
        {      
            String[] students = studentList.split(Constants.StudentEntryDelimiter);
            
            // TODO: The sequence of random numbers generated by a single 
            // Random instance is supposed to be uniformly distributed. 
            // By creating a new Random instance every time this operation 
            // is invoked, we run a risk of generating identical random 
            // numbers. Either reseed Random or instantiate it 
            // when the program starts.
            Random rand = new Random();
            int randomIndex = rand.nextInt(students.length);
            System.out.println(students[randomIndex]);

        }         
        else if(args[0].contains(Constants.AddEntry))
        {                          
            String newEntry = args[0].substring(1);        

            // TODO: Handle duplicate student names
            updateStudentList(studentList + Constants.StudentEntryDelimiter + newEntry);  
        } 
        else if(args[0].startsWith(Constants.FindEntry))
        {
            String[] students = studentList.split(Constants.StudentEntryDelimiter);
            String searchTerm = args[0].substring(1);

            if(Arrays.asList(students).contains(searchTerm))
            {
                System.out.printf("Entry %s found", searchTerm);
            } 
            else 
            {
                System.out.printf("Entry %s does not exist", searchTerm);
            }

        } 
        else if(args[0].equals(Constants.ShowCount)) 
        {
            String[] students = studentList.split(Constants.StudentEntryDelimiter);
            System.out.printf("%d words found", students.length);
        }
        else 
        {
            // Invalid arguments were provided, therefore we'll list the
            // valid arguments available to the user.
            showUsage();
        }
    }

    public static String loadStudentList(){

        // Variable used to organize code and allow buffered reader to close
        String bufferedStudentList = " ";  

        // The try...catch block handles the possible error that may occur if 
        // there was an issue with accessing the file.        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.StudentList));
            bufferedStudentList = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e){
            System.out.println(e);
        } 

        return bufferedStudentList;        
    }

    public static void updateStudentList(String content){
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

    public static void showUsage(){
        System.out.printf("java app.App (-a | -r | -c | +WORD | ?WORD)");
    }
}