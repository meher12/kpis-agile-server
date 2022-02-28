package com.mdev.springboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bug")
public class Bug extends Task{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length = 5)
    private int numbre;

    public Bug(Long id, int numbre) {
        super();
        this.id = id;
        this.numbre = numbre;
    }

    public Long getId() {
        return id;
    }

    public int getNumbre() {
        return numbre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumbre(int numbre) {
        this.numbre = numbre;
    }
    
}