package tn.altercall.utils;

import java.util.Date;

public class Efficacity {
    
    private Date startDate;
    private Date endDate;
    private float efficacity;
    
    
    public Efficacity() {
    }


    public Efficacity( Date endDate, float efficacity) {
        super();
        this.endDate = endDate;
        this.efficacity = efficacity;
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


    public float getEfficacity() {
        return efficacity;
    }


    public void setEfficacity(float efficacity) {
        this.efficacity = efficacity;
    }


  



}
