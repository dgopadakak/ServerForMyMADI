package Faculty;

import java.util.ArrayList;
import java.util.Objects;

public class GroupOperator
{
    private final ArrayList<Group> groups = new ArrayList<>();

    public void addExam(String groupName, Exam exam)
    {
        boolean isNewGroupNeeded = true;
        for (Group group : groups)
        {
            if (Objects.equals(group.name, groupName))
            {
                isNewGroupNeeded = false;
                group.listOfExams.add(exam);
                break;
            }
        }
        if (isNewGroupNeeded)
        {
            ArrayList<Exam> tempArrayList = new ArrayList<>();
            tempArrayList.add(exam);
            groups.add(new Group(groupName, tempArrayList));
        }
    }
}
