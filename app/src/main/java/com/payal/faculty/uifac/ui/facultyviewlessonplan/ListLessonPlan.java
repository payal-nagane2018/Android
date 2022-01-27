package com.payal.faculty.uifac.ui.facultyviewlessonplan;

public class ListLessonPlan {

    private int idi;
    private  String date;
    private  String time;

    public ListLessonPlan(int idi, String date, String time)
    {
        this.idi=idi;
        this.date=date;
        this.time=time;
    }
    public int getIdi()
    {
        return idi;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime(){
        return time;
    }
}
