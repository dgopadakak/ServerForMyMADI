package Faculty;

public class Exam
{
    String nameOfExam;
    String nameOfTeacher;
    int auditory;
    String date;
    String time;
    int peopleInAuditory;
    int isAbstractAvailable;
    String comment;

    public Exam(String nameOfExam, String nameOfTeacher, int auditory, String date, String time, int peopleInAuditory, int isAbstractAvailable, String comment)
    {
        this.nameOfExam = nameOfExam;
        this.nameOfTeacher = nameOfTeacher;
        this.auditory = auditory;
        this.date = date;
        this.time = time;
        this.peopleInAuditory = peopleInAuditory;
        this.isAbstractAvailable = isAbstractAvailable;
        this.comment = comment;
    }
}
