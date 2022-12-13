package Faculty;

import java.util.ArrayList;

public class Group
{
    String name;
    ArrayList<Exam> listOfExams;

    public Group(String name, ArrayList<Exam> listOfExams)
    {
        this.name = name;
        this.listOfExams = listOfExams;
    }
}
