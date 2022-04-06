package com.mdev.springboot.utils;

import java.util.Date;

public class Efficacity {
    private Date dateEnd;
    private float efficacity;
    
    
    public Efficacity() {
        super();
    }


    public Efficacity(Date dateEnd, float efficacity) {
        super();
        this.dateEnd = dateEnd;
        this.efficacity = efficacity;
    }


    public Date getDateEnd() {
        return dateEnd;
    }


    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    public float getEfficacity() {
        return efficacity;
    }


    public void setEfficacity(float efficacity) {
        this.efficacity = efficacity;
    }



}
