package Faculty;

import java.util.ArrayList;
import java.util.Objects;

public class GroupOperator
{
    private ArrayList<Group> groups = new ArrayList<>();

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

    public void delExam(int groupId, int examId)
    {
        groups.get(groupId).listOfExams.remove(examId);
    }

    public void editExam(int groupId, int examId, Exam newExam)
    {
        groups.get(groupId).listOfExams.set(examId, newExam);
    }

    public ArrayList<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups)
    {
        this.groups = groups;
    }
}
