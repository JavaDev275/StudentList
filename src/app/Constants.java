package app;

public class Constants 
{
    /** Name of file containing list of students. */
    protected static final String STUDENT_LIST = "students.txt";
    
    /** Command line argument to show all students. */
    protected static final String SHOW_ALL = "-a";
    
    /** Command line argument to show a random student from the list. */
    protected static final String SHOW_RANDOM = "-r";
    
    /** Command line argument to display a count of the number of students in the list. */
    protected static final String SHOW_COUNT = "-c";

    /** Command line argument to find a student in the list. */
    protected static final String FIND_ENTRY = "?";

    /** Command line argument to add an entry to the list. */
    protected static final String ADD_ENTRY = "+";

    /** The delimiter that separates entries in the student list. */
    protected static final String STUDENT_ENTRY_DELIMITER = ", ";
}