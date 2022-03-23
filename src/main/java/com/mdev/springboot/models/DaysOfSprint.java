package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "daysinsprint")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//
//public class DaysOfSprint  implements Serializable{
//    
//    /**
//     * 
//     */
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprintday_generator")
//    private Long id;
//    
//    @NotNull
//    @Column(name="arrayDays")
//    private List<String> arrayDays = new ArrayList<String>();
//    
//    @NotNull
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "sprint_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
//    private Sprint sprints;
//
//}
