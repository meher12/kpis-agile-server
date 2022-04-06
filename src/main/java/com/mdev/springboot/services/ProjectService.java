package com.mdev.springboot.services;

import java.text.ParseException;
import java.util.ArrayList;

import com.mdev.springboot.utils.PairArrays;

public interface ProjectService {

    ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone, ArrayList<String> moresp);

    ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints, ArrayList<String> tabFromdb);
    
    PairArrays listTaskByStatus(String projetref);
    
    PairArrays efficacityByStartDateTask() throws ParseException;
    

}
