package com.mdev.springboot.utils;

import java.util.Date;

public class TasksBugs {
 
    private Date startDate;
    private Date endDate;
    private int bugstask;
    private int taskwithoutBug;
    
    
    public TasksBugs() {
    }

    

    public TasksBugs(Date endDate, int bugstask, int taskwithoutBug) {
        super();
        this.endDate = endDate;
        this.bugstask = bugstask;
        this.taskwithoutBug = taskwithoutBug;
    }



    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public int getBugstask() {
        return bugstask;
    }


    public void setBugstask(int bugstask) {
        this.bugstask = bugstask;
    }


    public int getTaskwithoutBug() {
        return taskwithoutBug;
    }


    public void setTaskwithoutBug(int taskwithoutBug) {
        this.taskwithoutBug = taskwithoutBug;
    }
    
    
   
    
    
}
