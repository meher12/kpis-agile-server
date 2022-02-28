package com.mdev.springboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "more_tasks")
public class MoreTask extends Task{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 5)
    private int estimation;

    
    public MoreTask(Long id, int estimation) {
        super();
        this.id = id;
        this.estimation = estimation;
    }

    public Long getId() {
        return id;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }
    
    

}