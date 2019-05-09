package app;

public class Constants 
{
    /// Name of file containing list of students.
    protected static final String StudentList = "students.txt";
    
    /** Command line argument to show all students. */
    protected static final String ShowAll = "-a";
    
    // Command line argument to show a random student from the list.
    protected static final String ShowRandom = "-r";
    
    // Command line argument to display a count of the number of students in the list.
    protected static final String ShowCount = "-c";

    // Command line argument to find a student in the list.
    protected static final String FindEntry = "?";

    /* Command line argument to add an entry to the list. */
    protected static final String AddEntry = "+";

    // The delimiter that separates entries in the student list.
    protected static final String StudentEntryDelimiter = ", ";
}