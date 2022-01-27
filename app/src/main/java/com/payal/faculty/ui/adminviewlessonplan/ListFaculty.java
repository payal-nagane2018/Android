package com.payal.faculty.ui.adminviewlessonplan;


public class ListFaculty
{
    private  int uid;
    private String name;
    private String department;
    private  String year;
    private byte []images;

    public ListFaculty(int uid, String name, String department, String year, byte []images)
    {
        this.uid=uid;
        this.name=name;
        this.department=department;
        this.year=year;
        this.images=images;
    }
    public int getUId() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public byte[] getImages() {
        return images;
    }

    public String getYear() {
        return year;
    }
}
